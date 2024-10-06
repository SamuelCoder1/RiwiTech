package com.riwi.RiwiTech.controllers;

import com.riwi.RiwiTech.application.dtos.requests.ProjectRequest;
import com.riwi.RiwiTech.application.dtos.responses.ProjectResponse;
import com.riwi.RiwiTech.application.exceptions.GenericNotFoundExceptions;
import com.riwi.RiwiTech.domain.entities.Project;
import com.riwi.RiwiTech.domain.ports.service.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Operation(summary = "Create a new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/create")
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest projectRequest) {
        ProjectResponse createdProject = projectService.create(projectRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> readAll() {
        List<ProjectResponse> projects = projectService.readAll();
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Get project by ID", description = "Retrieves a project by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @GetMapping("/readById/{id}")
    public ResponseEntity<ProjectResponse> readById(@PathVariable Long id) {
        try {
            ProjectResponse projectResponse = projectService.readById(id)
                    .orElseThrow(() -> new GenericNotFoundExceptions("Project not found with id: " + id));
            return ResponseEntity.ok(projectResponse);
        } catch (GenericNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Update an existing project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody ProjectRequest projectRequest) {
        Project updatedProject = projectService.update(projectRequest, id);
        return ResponseEntity.ok(updatedProject);
    }

    @Operation(summary = "Delete a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get project by name", description = "Retrieves a project by its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @GetMapping("/readByName/{name}")
    public ResponseEntity<ProjectResponse> readByName(@PathVariable String name) {
        try {
            ProjectResponse projectResponse = projectService.readByName(name);
            return ResponseEntity.ok(projectResponse);
        } catch (GenericNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
