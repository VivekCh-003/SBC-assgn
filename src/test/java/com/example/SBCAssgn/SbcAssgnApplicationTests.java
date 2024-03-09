package com.example.SBCAssgn;

import com.example.SBCAssgn.Controller.ProjectController;
import com.example.SBCAssgn.Entity.Project;
import com.example.SBCAssgn.Service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SbcAssgnApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@InjectMocks
	private ProjectController projectController;

	@MockBean
	private ProjectService projectService;

	@Test
	public void testGetAllProjects() throws Exception {

		Project project1 = new Project("Project 1", "Description 1", LocalDate.now(),LocalDate.now().plusDays(7));
		Project project2 = new Project("Project 2", "Description 2", LocalDate.now(),LocalDate.now().plusDays(7));
		List<Project> projects = Arrays.asList(project1, project2);
		when(projectService.findAll()).thenReturn(projects);

		mockMvc.perform(MockMvcRequestBuilders.get("/projects")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Project 1"))
				.andExpect(jsonPath("$[1].name").value("Project 2"));
	}

	@Test
	public void testPostProject() throws Exception {

		Project project = new Project("New Project", "Description", LocalDate.now(),LocalDate.now().plusDays(7));
		when(projectService.save(any(Project.class))).thenReturn(project);


		mockMvc.perform(MockMvcRequestBuilders.post("/project")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"name\": \"New Project\", \"description\": \"Description\" }"))
				.andExpect(status().isCreated())
				.andExpect(content().string("ok"));

		Mockito.verify(projectService, times(1)).save(any(Project.class));
	}

	@Test
	public void testGetProjectById() throws Exception {

		int projectId = 1;
		Project project = new Project("Test Project", "Test Description", LocalDate.now(),LocalDate.now().plusDays(7));
		Mockito.when(projectService.findById(projectId)).thenReturn(project);


		mockMvc.perform(MockMvcRequestBuilders.get("/project/{id}", projectId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Project"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test Description"));
	}



	@Test
	public void testUpdateProjectById() throws Exception {

		Project existingProject = new Project();
		existingProject.setId(1);
		existingProject.setName("Existing Project");
		existingProject.setDescription("Existing Project Description");
		existingProject.setStartDate(LocalDate.now());
		existingProject.setEndDate(LocalDate.now().plusDays(7));


		when(projectService.updatedProject(1, existingProject)).thenReturn(existingProject);


		String projectJson = objectMapper.writeValueAsString(existingProject);


		mockMvc.perform(put("/updateProject/{id}", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.content(projectJson))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteProjectById() throws Exception {
		doNothing().when(projectService).deleteById(1);


		mockMvc.perform(delete("/deleteProject/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().string("Deleted Successfully"));

		verify(projectService, times(1)).deleteById(1);
	}


}
