package com.riwi.RiwiTech.application.services.impl;

import com.riwi.RiwiTech.application.dtos.request.ProjectRequest;
import com.riwi.RiwiTech.application.dtos.response.ProjectResponse;
import com.riwi.RiwiTech.application.mappers.ProjectMapper;
import com.riwi.RiwiTech.application.mappers.TaskMapper;
import com.riwi.RiwiTech.domain.entities.Project;
import com.riwi.RiwiTech.domain.entities.Task;
import com.riwi.RiwiTech.domain.ports.service.IProjectService;
import com.riwi.RiwiTech.infrastructure.persistence.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;

import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;



    @Override
    public void delete(Long id) {
        if (!projectRepository.existsById(id)){
            throw new EntityNotFoundException("Project not found");
        }
        projectRepository.deleteById(id);

    }

    @Override
    public List<ProjectResponse> readAll() {
        List<Project> project = projectRepository.findAll();
        return project.stream()
                .map(ProjectMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse readById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Project not found"));
        return ProjectMapper.INSTANCE.toResponseDto(project);
    }

    @Override
    public ProjectResponse readByName(String name) {
        Optional<Project> projectOptional = projectRepository.findByName(name);
        if (projectOptional.isPresent()){
            Project project = projectOptional.get();
            return  ProjectMapper.INSTANCE.toResponseDto(project);
        }
        throw  new EntityNotFoundException("Project not found");
    }


    @Override
    public ProjectRequest create(ProjectRequest projectRequest) {
        Project project = ProjectMapper.INSTANCE.toEntity(projectRequest);
        projectRepository.save(project);
        return projectRequest;
    }


    @Override
    public Project update(ProjectRequest projectRequest, Long id) {
        Optional<Project> existingProjectOpt = projectRepository.findById(id);
        // Verificamos si la tarea existe
        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();
            // Usar el mapper para actualizar la tarea
            Task updatedProject = TaskMapper.INSTANCE.toEntity(projectRequest);
            // Actualizar solo los campos necesarios (o puedes mapear directamente si es apropiado)
            existingProject.setDescription(updatedTask.getDescription());
            // Aquí puedes establecer otros campos que necesites
            return projectRepository.save(existingTask); // Guardar y retornar la tarea actualizada
        }
        // Lanzar excepción si no se encuentra la tarea
        throw new EntityNotFoundException("Task not found");
    }
}
