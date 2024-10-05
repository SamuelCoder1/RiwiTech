package com.riwi.RiwiTech.application.dtos.requests;

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
public class ProjectRequest {

        private String name;

        private Set<TaskRequest> tasks;


}
