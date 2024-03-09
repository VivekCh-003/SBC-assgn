package com.example.SBCAssgn.Service;

import com.example.SBCAssgn.Controller.ProjectNotFoundException;
import com.example.SBCAssgn.Entity.Project;
import com.example.SBCAssgn.Repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public List<Project> findAll() {
        return projectRepo.findAll();
    }

    @Override
    public Project save(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public Project updatedProject(int id, Project project) {
        Project existingProject = projectRepo.findById(id).orElseThrow(null);

        if(existingProject != null){
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setStartDate(project.getStartDate());
            existingProject.setEndDate(project.getEndDate());
        }

        assert existingProject != null;
        return projectRepo.save(existingProject);
    }

    @Override
    public void deleteById(int id) {
       if(!projectRepo.existsById(id)){
           throw new ProjectNotFoundException("Project not found with id: " + id);
       }
       projectRepo.deleteById(id);
    }

    @Override
    public Project findById(int id) {
        Project project = projectRepo.findById(id).orElseThrow(() ->
                new ProjectNotFoundException("Project not found with id: " + id));


        return project;
    }
}
