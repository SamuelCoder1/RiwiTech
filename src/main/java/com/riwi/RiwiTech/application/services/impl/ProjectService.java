package com.riwi.RiwiTech.application.services.impl;

import com.riwi.RiwiTech.application.dtos.mappers.ProjectMapper;
import com.riwi.RiwiTech.application.dtos.requests.ProjectRequest;
import com.riwi.RiwiTech.application.dtos.responses.ProjectResponse;
import com.riwi.RiwiTech.application.dtos.mappers.TaskMapper;
import com.riwi.RiwiTech.domain.entities.Project;
import com.riwi.RiwiTech.domain.entities.Task;
import com.riwi.RiwiTech.domain.ports.service.IProjectService;
import com.riwi.RiwiTech.infrastructure.persistence.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Project not found");
        }
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectResponse> readAll() {
        return projectRepository.findAll().stream()
                .map(ProjectMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectResponse> readById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return Optional.of(ProjectMapper.INSTANCE.toResponseDto(project));
    }

    @Override
    public ProjectResponse create(ProjectRequest projectRequest) {
        Project project = ProjectMapper.INSTANCE.toEntity(projectRequest);

        if (projectRequest.getTasks() != null) {
            Set<Task> tasks = projectRequest.getTasks().stream()
                    .map(TaskMapper.INSTANCE::toEntity) // Mapea cada TaskRequest a Task
                    .collect(Collectors.toSet());

            project.setTasks(tasks);
            tasks.forEach(task -> task.setProject(project)); // Establece la relación inversa
        }

        projectRepository.save(project);
        return ProjectMapper.INSTANCE.toResponseDto(project);
    }
  
    @Override
    public Project update(ProjectRequest projectRequest, Long id) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        existingProject.setName(projectRequest.getName());

        if (projectRequest.getTasks() != null) {
            Set<Task> tasks = projectRequest.getTasks().stream()
                    .map(TaskMapper.INSTANCE::toEntity)
                    .collect(Collectors.toSet());

            existingProject.setTasks(tasks);
            tasks.forEach(task -> task.setProject(existingProject));
        }

        projectRepository.save(existingProject);
        return existingProject; // Retorna el proyecto actualizado
    }

    @Override
    public ProjectResponse readByName(String name) {
        return projectRepository.findByName(name)
                .map(ProjectMapper.INSTANCE::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with name: " + name));
    }
}
