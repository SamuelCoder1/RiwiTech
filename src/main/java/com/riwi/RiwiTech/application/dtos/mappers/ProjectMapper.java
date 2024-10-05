package com.riwi.RiwiTech.application.dtos.mappers;

import com.riwi.RiwiTech.application.dtos.requests.ProjectRequest;
import com.riwi.RiwiTech.application.dtos.responses.ProjectResponse;
import com.riwi.RiwiTech.domain.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project toEntity(ProjectRequest projectRequest);
    ProjectResponse toResponseDto(Project project);
}
