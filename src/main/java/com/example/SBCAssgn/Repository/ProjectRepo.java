package com.example.SBCAssgn.Repository;

import com.example.SBCAssgn.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Integer> {
}
