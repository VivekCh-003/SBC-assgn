package com.example.SBCAssgn.Controller;

import com.example.SBCAssgn.Entity.Project;
import com.example.SBCAssgn.Service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// SWAGGER ENDPOINT - http://localhost:8081/swagger-ui/index.html
// H2-DATABSE CONSOLE ENDPOINT - http://localhost:8081/h2-console

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
    }

    @GetMapping("/projects")
    private ResponseEntity<List<Project>> findAll(){
        List<Project> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/project/{id}")
    private ResponseEntity<Project> getById(@PathVariable int id){
            Project project = projectService.findById(id);
            return ResponseEntity.ok(project);
    }

    @PostMapping("/project")
    private ResponseEntity<String> save(@Valid @RequestBody Project project){
        Project newProject = projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }

    @PutMapping("/updateProject/{id}")
    public ResponseEntity<Project> update(@PathVariable int id, @RequestBody Project project) {
        Project updatedProject = projectService.updatedProject(id, project);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            // If project is not found, return 404 Not Found status
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        try{
            projectService.deleteById(id);
            return ResponseEntity.ok("Deleted Successfully");
        }catch (ProjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project not found with id: " + id);
        }
    }

}
