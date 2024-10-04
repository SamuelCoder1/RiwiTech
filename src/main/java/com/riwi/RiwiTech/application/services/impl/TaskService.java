package com.riwi.RiwiTech.application.services.impl;


import com.riwi.RiwiTech.application.dtos.request.TaskRequest;
import com.riwi.RiwiTech.application.dtos.response.TaskResponse;
import com.riwi.RiwiTech.application.mappers.TaskMapper;
import com.riwi.RiwiTech.domain.entities.Task;
import com.riwi.RiwiTech.domain.ports.service.ITaskService;
import com.riwi.RiwiTech.infrastructure.persistence.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)){
            throw  new EntityNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponse> readAll() {
        List<Task> task = taskRepository.findAll();
        return task.stream()
                .map(TaskMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse readById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task no found"));
        return TaskMapper.INSTANCE.toResponseDto(task);
    }

    @Override
    public TaskResponse readByName(String name) {
        Optional<Task> taskOptional = taskRepository.findByName(name);
        // Verificamos si la tarea existe
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            // Convertir la tarea a TaskResponse usando el mapper
            return TaskMapper.INSTANCE.toResponseDto(task);
        }
        // Lanzar excepción si no se encuentra la tarea
        throw new EntityNotFoundException("Task not found with name: " + name);
    }



    @Override
    public TaskRequest create(TaskRequest taskRequest) {
        // Convertir el TaskRequest a una entidad
        var taskEntity = TaskMapper.INSTANCE.toEntity(taskRequest);

        // Guardar la entidad en el repositorio
        taskRepository.save(taskEntity);

        // Retornar el TaskRequest original sin modificaciones
        return taskRequest;
    }


    @Override
    public Task update(TaskRequest taskRequest, Long id) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        // Verificamos si la tarea existe
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            // Usar el mapper para actualizar la tarea
            Task updatedTask = TaskMapper.INSTANCE.toEntity(taskRequest);
            // Actualizar solo los campos necesarios (o puedes mapear directamente si es apropiado)
            existingTask.setDescription(updatedTask.getDescription());
            // Aquí puedes establecer otros campos que necesites
            return taskRepository.save(existingTask); // Guardar y retornar la tarea actualizada
        }
        // Lanzar excepción si no se encuentra la tarea
        throw new EntityNotFoundException("Task not found");
    }
}
