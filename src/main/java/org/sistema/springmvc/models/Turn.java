package org.sistema.springmvc.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Represents a turn.
 * 
 * @author Eugenia Pérez Martínez
 *
 */
@Entity
public class Turn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String question;

	private Date date = new Date();
	
	private Integer priority;

    @ManyToOne
    @JoinColumn(name="userId")
	private User user = new User();

	public Turn() {
	}

	/**
	 * @param id
	 * @param question
	 * @param date
	 * @param priority
	 * @param user
	 */
	public Turn(Long id, String question, Date date, Integer priority, User user) {
		this.id = id;
		this.question = question;
		this.date = date;
		this.priority = priority;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Turn [id=" + id + ", question=" + question + ", date=" + date + ", priority=" + priority + ", user="
				+ user + "]";
	}



	
}
