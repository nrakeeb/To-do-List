package com.qa.ToDoList.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import com.qa.ToDoList.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;




@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testschema.sql",
		"classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")

public class UserControllerIntergrationTest {

	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
		
		
@Test
		
		// testing response of create 
		void createTest() throws Exception {
			User entry = new User("Make a doctors appointment");
			String entryAsJSON = this.mapper.writeValueAsString(entry);
			
			
			mvc.perform(post("/Notes/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(entryAsJSON));
		}
		
		
		@Test
		// testing response of get all
		public void getAllTest() throws Exception {
			User entry = new User("Make a doctors appointment");
			List<User> output = new ArrayList<>();
			output.add(entry);
			String outputAsJSON = this.mapper.writeValueAsString(output);
			
			mvc.perform(get("/Notes/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputAsJSON));
		}
		
		
		// testing response of get by ID
		@Test
		public void getByIdTest() throws Exception {
			User entry = new User("Make a doctors appointment");
			String entryAsJSON = this.mapper.writeValueAsString(entry);
			
			
			mvc.perform(get("/Notes/getById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(entryAsJSON));
		}
		
		// testing response to update entry
		
		@Test
		public void updateTest() throws Exception {
			User outputUpdate = new User(1L,"Make a doctors appointment");
			String outputAsJSON = mapper.writeValueAsString(outputUpdate);
			
			User result = new User(1L,"Make a doctors appointment for monday morining");
			String resultAsJSON = mapper.writeValueAsString(result);
			
			
			mvc.perform(put("/Notes/update/1").contentType(MediaType.APPLICATION_JSON).content(outputAsJSON))
			.andExpect(status().isCreated()).andExpect(content().json(resultAsJSON));
				
		}
		
		// testing response to delete
		
		@Test
		public void deleteTest() throws Exception {
		
			mvc.perform(delete("/Notes/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		}
		
		// testing response if id does not exist when deleting entry
		
		@Test
		public void deleteFailTest() throws Exception {
		
			mvc.perform(delete("/Notes/delete/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
		}
	
}
