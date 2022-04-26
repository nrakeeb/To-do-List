package com.qa.ToDoList.service;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.ToDoList.domain.User;
import com.qa.ToDoList.repo.UserRepo;






@Service
public class UserService {
	
	private UserRepo repo; 

	// generate a constructor using all fields
	public UserService(UserRepo repo) {
		super();
		this.repo = repo;
	}
	
	// get by all
	
	public List<User> getAll() {
		return repo.findAll();
	}
	
	// get by ID
	
	public User getById (long id) {
		return repo.findById(id).get(); // .get() will either get the User (if exists) Or Throw NoSuchElemtException
			
		}

	// create user
	
		public User create(User user) {
			return repo.saveAndFlush(user);
		}

}

