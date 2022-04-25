package com.qa.ToDoList.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {

	@Id // This makes the column a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // This make this column auto-increment
	private long id;
	
	
}
