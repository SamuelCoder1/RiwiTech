package com.riwi.RiwiTech.application.mappers;

import com.riwi.RiwiTech.application.dtos.request.TaskRequest;
import com.riwi.RiwiTech.application.dtos.response.TaskResponse;
import com.riwi.RiwiTech.domain.entities.Task;
import org.mapstruct.factory.Mappers;

public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    public Task toEntity(TaskRequest taskRequest);
    public TaskResponse toResponseDto(Task task);
}
