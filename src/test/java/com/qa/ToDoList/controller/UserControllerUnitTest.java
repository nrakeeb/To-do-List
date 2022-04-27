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
		
		@Test
		void createTest() throws Exception {
			User entry = new User("Food List", "Pasta\\nBread\\nApples\\nChicken\\nMilk ", "£40");
			String entryAsJSON = this.mapper.writeValueAsString(entry);
			
			Mockito.when(this.service.create(entry)).thenReturn(entry);
			
			mvc.perform(post("/Notes/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(entryAsJSON));
		}
		
		
		@Test
		public void getAllTest() throws Exception {
			User entry = new User("Food List", "Pasta\\nBread\\nApples\\nChicken\\nMilk ", "£40");
			List<User> output = new ArrayList<>();
			output.add(entry);
			String outputAsJSON = this.mapper.writeValueAsString(output);
			
			Mockito.when(this.service.getAll()).thenReturn(output);
			
			mvc.perform(get("/Notes/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputAsJSON));
		}
	
}
