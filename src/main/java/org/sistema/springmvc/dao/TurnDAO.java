package org.sistema.springmvc.dao;

import java.util.List;

import org.sistema.springmvc.models.Turn;

/**
 * Interface for a TurnDAO.
 * 
 * @author Eugenia Pérez Martínez.
 *
 */
public interface TurnDAO {

	/**
	 * This is the method to be used to create a record in the Student table.
	 * 
	 * @param turn
	 *            turn to be saved.
	 * 
	 * @return the ID of the saved turn.
	 */
	public Long insert(Turn turn);

	/**
	 * This is the method to be used to list down a record from the Student
	 * table corresponding to a passed student id.
	 * 
	 * @param id
	 * 
	 * @return the turn found
	 */
	public Turn selectById(Long id);

	/**
	 * This is the method to be used to list down all the records from the
	 * Student table.
	 * 
	 * @return the list of turns.
	 */
	public List<Turn> selectAll();

	/**
	 * This is the method to be used to delete a record from the Student table
	 * corresponding to a passed student id.
	 */
	public void delete(Long id);

	/**
	 * This is the method to be used to update a record into the Student table.
	 */
	public void update(Turn turn);
}
