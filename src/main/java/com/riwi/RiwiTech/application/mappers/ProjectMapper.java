package com.riwi.RiwiTech.application.mappers;

import com.riwi.RiwiTech.application.dtos.request.ProjectRequest;
import com.riwi.RiwiTech.application.dtos.response.ProjectResponse;
import com.riwi.RiwiTech.domain.entities.Project;
import org.mapstruct.factory.Mappers;

public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    public Project toEntity(ProjectRequest projectRequest);

    public ProjectResponse toResponseDto(Project project);
}
