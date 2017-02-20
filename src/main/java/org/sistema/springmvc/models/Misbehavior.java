package org.sistema.springmvc.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents a Misbehaviour.
 * 
 * @author David Larrea
 *
 */
@Entity
public class Misbehavior {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters long")
	@Pattern(regexp = "[A-Za-z0-9]+", message = "Must contain only chars and numbers")
	private String title;

	@Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters long")
	private String description;
	
	@NotNull(message="Please, select date")
	@Past(message="Only the past is valid")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;

	@ManyToOne
    @JoinColumn(name="userId")
	private User user = new User();
	
	public Misbehavior() {
	}

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param date
	 * @param user
	 */
	public Misbehavior(Long id, String title, String description, Date date, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Misbehavior [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date
				+ ", user=" + user + "]";
	}
	
}
