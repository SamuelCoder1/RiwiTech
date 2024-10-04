package com.riwi.RiwiTech.application.dtos.response;


import com.riwi.RiwiTech.application.dtos.request.UserRequestOnlyUsername;
import com.riwi.RiwiTech.domain.entities.Task;
import com.riwi.RiwiTech.domain.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    private List<Task> tasks;

    private List<UserRequestOnlyUsername> users;
}
