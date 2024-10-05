package com.riwi.RiwiTech.application.dtos.responses;


import com.riwi.RiwiTech.application.dtos.requests.UserRequestOnlyUsername;
import com.riwi.RiwiTech.domain.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectResponse {

    private String name;

    private Set<TaskResponse> tasks;
}
