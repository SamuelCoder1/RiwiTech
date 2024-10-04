package com.riwi.RiwiTech.domain.ports.service;

import com.riwi.RiwiTech.application.dtos.request.TaskRequest;
import com.riwi.RiwiTech.application.dtos.response.TaskResponse;
import com.riwi.RiwiTech.application.services.generic.*;
import com.riwi.RiwiTech.domain.entities.Task;
import org.springframework.http.ResponseEntity;

public interface ITaskService extends

        Create<TaskRequest,TaskRequest>,
        Update<TaskRequest, Task,Long>,
        Delete<Long>,
        ReadAll<TaskResponse>,
        ReadById<TaskResponse, Long>,
        ReadByName<TaskResponse, String>{
}
