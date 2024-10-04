package com.riwi.RiwiTech.application.dtos.response;


import com.riwi.RiwiTech.domain.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponse {

    private String description;

    private Project project;
}
