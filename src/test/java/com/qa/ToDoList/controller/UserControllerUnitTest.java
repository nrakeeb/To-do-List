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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.ToDoList.domain.User;
import com.qa.ToDoList.service.UserService;


@WebMvcTest
public class UserControllerUnitTest {

		@Autowired
		private MockMvc mvc;
		
		@Autowired
		private ObjectMapper mapper;
		
		@MockBean
		private UserService service;
		
		@Test //marks a method as a test
		public void createTest() throws Exception {
			User entry = new User("I need a holiday");
			String entryAsJSON = mapper.writeValueAsString(entry);

			User result = new User(2L, "I need a holiday");
			String resultAsJSON = mapper.writeValueAsString(result);
			
			Mockito.when(this.service.create(entry)).thenReturn(result);


			mvc.perform(post("/Notes/create").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON))
					.andExpect(status().isCreated()).andExpect(content().json(resultAsJSON));

		}	

		@Test
		public void getAllTest() throws Exception {
			User user = new User(1L, "Make a doctors appointment");
			List<User> output = new ArrayList<>();
			output.add(user);
			String outputAsJSON = mapper.writeValueAsString(output);
			
			Mockito.when(this.service.getAll()).thenReturn(output);


			mvc.perform(get("/Notes/getAll").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(content().json(outputAsJSON));
			
		}
		//
		@Test
		public void getByIdTest () throws Exception {
			User outputID = new User(1L, "Make a doctors appointment");
			String outputIDAsJSON = mapper.writeValueAsString(outputID);
			
			Mockito.when(this.service.getById(1L)).thenReturn(outputID);
			
			mvc.perform(get("/Notes/getById/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(outputIDAsJSON));
					
		}

		@Test
		public void updateTest() throws Exception {
			User outputUpdate = new User("I need a holiday");
			String outputAsJSON = mapper.writeValueAsString(outputUpdate);
			
			User result = new User(1L, "I need a holiday");
			String resultAsJSON = mapper.writeValueAsString(result);
			
			Mockito.when(this.service.update(1L, outputUpdate)).thenReturn(result);
			
			mvc.perform(put("/Notes/update/1").contentType(MediaType.APPLICATION_JSON).content(outputAsJSON))
			.andExpect(status().isCreated()).andExpect(content().json(resultAsJSON));
				
		}

		@Test
		public void deleteTest() throws Exception {
			Mockito.when(this.service.delete(1L)).thenReturn(true);
		
			mvc.perform(delete("/Notes/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		}
		
		@Test
		public void deleteFailTest() throws Exception {
			Mockito.when(this.service.delete(2L)).thenReturn(false);
		
			mvc.perform(delete("/Notes/delete/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
		}
		
		
}
