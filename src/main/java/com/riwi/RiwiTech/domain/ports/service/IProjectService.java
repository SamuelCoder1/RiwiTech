package com.riwi.RiwiTech.domain.ports.service;

import com.riwi.RiwiTech.application.dtos.request.ProjectRequest;
import com.riwi.RiwiTech.application.dtos.response.ProjectResponse;
import com.riwi.RiwiTech.application.services.generic.*;
import com.riwi.RiwiTech.domain.entities.Project;

public interface IProjectService extends

        Create<ProjectRequest,ProjectRequest>,
        Update<ProjectRequest,Project, Long>,
        Delete<Long>,
        ReadAll<ProjectResponse>,
        ReadById<ProjectResponse, Long>,
        ReadByName<ProjectResponse, String>{
}
