package org.sistema.springmvc.dao;

import java.util.List;

import org.sistema.springmvc.models.Misbehavior;;

/**
 * Interface for a TurnDAO.
 * 
 * @author Eugenia Pérez Martínez.
 *
 */
public interface MisbehaviorDAO {

	/**
	 * This is the method to be used to create a record in the Student table.
	 * 
	 * @param turn
	 *            turn to be saved.
	 * 
	 * @return the ID of the saved turn.
	 */
	public Long insert(Misbehavior misbehavior);

	/**
	 * This is the method to be used to list down a record from the Student
	 * table corresponding to a passed student id.
	 * 
	 * @param id
	 * 
	 * @return the turn found
	 */
	public Misbehavior selectById(Long id);

	/**
	 * This is the method to be used to list down all the records from the
	 * Student table.
	 * 
	 * @return the list of turns.
	 */
	public List<Misbehavior> selectAll();
	
	public List<Misbehavior> selectByName(String title);

	/**
	 * This is the method to be used to delete a record from the Student table
	 * corresponding to a passed student id.
	 */
	public void delete(Long id);

	public void deleteAll();
	
	/**
	 * This is the method to be used to update a record into the Student table.
	 */
	public void update(Misbehavior misbehavior);
}
