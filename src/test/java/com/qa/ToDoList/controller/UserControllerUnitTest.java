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
		
		// testing response of create 
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
		// testing response of get all
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
		
		
		// testing response of get by ID
		@Test
		public void getByIdTest() throws Exception {
			User entry = new User("Food List", "Pasta\\nBread\\nApples\\nChicken\\nMilk ", "£40");
			String entryAsJSON = this.mapper.writeValueAsString(entry);
			
			Mockito.when(this.service.getById(1L)).thenReturn(entry);
			
			mvc.perform(get("/Notes/getById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(entryAsJSON));
		}
		
		// testing response to update entry
		
		@Test
		public void updateTest() throws Exception {
			User outputUpdate = new User(1L,"Food List", "Pasta\\nBread\\nApples\\nChicken\\nMilk ", "£40");
			String outputAsJSON = mapper.writeValueAsString(outputUpdate);
			
			User result = new User(1L,"Food List", "Rice\\nWater\\nBanana\\nChicken\\nMilk ", "£50");
			String resultAsJSON = mapper.writeValueAsString(result);
			
			Mockito.when(this.service.update(1L, outputUpdate)).thenReturn(result);
			
			mvc.perform(put("/Notes/update/1").contentType(MediaType.APPLICATION_JSON).content(outputAsJSON))
			.andExpect(status().isCreated()).andExpect(content().json(resultAsJSON));
				
		}
		
		// testing response to delete
		
		@Test
		public void deleteTest() throws Exception {
			Mockito.when(this.service.delete(1L)).thenReturn(true);
		
			mvc.perform(delete("/user/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		}
		
		// testing response if id does not exist when deleting entry
		
		@Test
		public void deleteFailTest() throws Exception {
			Mockito.when(this.service.delete(2L)).thenReturn(false);
		
			mvc.perform(delete("/Notes/delete/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
		}
}
