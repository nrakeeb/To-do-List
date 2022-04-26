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
	
	
	// Creates a column called "Title"
		@Column(nullable = false)
		private String Title;
		
		// Creates a column called "Comments"
		@Column(nullable = false)
		private String Comments;
		
		// Creates a column called "Price"
		@Column(nullable = false)
		private String Price;
		
		
		//  Constructors 
		//Default Constructor
		public User() {}
		
		// used for creating/ inserting without ID
		public User(String Title, String Comments, String Price) {
			super();
			this.Title = Title;
			this.Comments = Comments;
			this.Price = Price;
		}
		
		//Used for reading/selecting (and testing)
		public User (long id, String Title, String Comments, String Price) {
			super();
			this.id = id;
			this.Title = Title;
			this.Comments = Comments;
			this.Price = Price;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getComments() {
			return Comments;
		}

		public void setComments(String comments) {
			Comments = comments;
		}

		public String getPrice() {
			return Price;
		}

		public void setPrice(String price) {
			Price = price;
		}

		
		@Override
		public String toString() {
			return "User [id=" + id + ", Title=" + Title + ", Comments=" + Comments + ", Price=" + Price + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(Comments, Price, Title, id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			return Objects.equals(Comments, other.Comments) && Objects.equals(Price, other.Price)
					&& Objects.equals(Title, other.Title) && id == other.id;
		}
		
		
	
}
