package com.qa.ToDoList.service;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.ToDoList.domain.User;
import com.qa.ToDoList.repo.UserRepo;

import exceptions.NoteCannotBeUpdated;
import exceptions.NoteNotFoundException;




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
		return repo.findById(id).orElseThrow(NoteNotFoundException::new); // .get() will either get the User (if exists) Or Throw NoSuchElemtException
			
		}

	// create user
	
		public User create(User user) {
			return repo.saveAndFlush(user);
		}
		
		
		// update a entries
		public User update(long id, User user) {
			User existing = repo.findById(id).orElseThrow(NoteCannotBeUpdated::new); // get the Existing User
			existing.setTitle(user.getTitle()); // Change Existing user's title to new user's title.
			existing.setComments(user.getComments()); // Change Existing user's comments to new user's comments.
			existing.setPrice(user.getPrice()); // Change Existing user's price to new user's price.
			return repo.saveAndFlush(existing);
		}
		
		// delete user
		public boolean delete(long id) {
			repo.deleteById(id);
			return !repo.existsById(id);
		}

}

