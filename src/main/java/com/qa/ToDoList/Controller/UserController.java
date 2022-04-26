package com.qa.ToDoList.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ToDoList.domain.User;



@RestController
@CrossOrigin
@RequestMapping("/Notes")
public class UserController {

	//GET
	@GetMapping("/getAll") //localhost:8080/getAll
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<List<User>>(service.getAll(),HttpStatus.OK);
	}
	
	//GET by ID (get one User)
		@GetMapping("/getById/{id}")//localhost:8080/getById/id
		public ResponseEntity<User> getById (@PathVariable long id) {
			return new ResponseEntity<User>(service.getById(id),HttpStatus.OK);
		}
	
	
	//POST
		@PostMapping("/create") //localhost:8080/create
		public ResponseEntity <User> create(@RequestBody User user ) {
			return new ResponseEntity<User>(service.create(user), HttpStatus.CREATED);
		}
	
	//PUT
	
	
	
	
	//DELETE
	
}
