package com.riwi.RiwiTech.application.dtos.request;

import com.riwi.RiwiTech.domain.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequest {
    private String description;

    private Project project;
}
