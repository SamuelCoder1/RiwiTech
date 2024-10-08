package com.riwi.RiwiTech.domain.ports.service;

import com.riwi.RiwiTech.application.dtos.requests.ProjectRequest;
import com.riwi.RiwiTech.application.dtos.responses.ProjectResponse;
import com.riwi.RiwiTech.application.services.generic.*;
import com.riwi.RiwiTech.domain.entities.Project;

public interface IProjectService extends
        Create<ProjectResponse, ProjectRequest>,
        Update<ProjectRequest, Project, Long>,
        Delete<Long>,
        ReadAll<ProjectResponse>,
        ReadById<ProjectResponse, Long>,
        ReadByName<ProjectResponse, String> {
}

