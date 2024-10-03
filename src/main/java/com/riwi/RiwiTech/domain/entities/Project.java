package com.riwi.RiwiTech.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.Set;

@Entity
@Table(name = "projects")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Task> tasks;


    @ManyToMany(mappedBy = "projects")
    private Set<User> users;
}