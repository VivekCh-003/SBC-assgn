package com.example.SBCAssgn.Service;

import com.example.SBCAssgn.Entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    Project save(Project project);

    Project updatedProject(int id, Project project);

    void deleteById(int id);

    Project findById(int id);
}
