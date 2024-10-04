package com.riwi.RiwiTech.application.dtos.request;

import com.riwi.RiwiTech.domain.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProjectRequest {

        private String name;

        private List<Task> tasks;

        private List<UserRequestOnlyUsername> users;
}
