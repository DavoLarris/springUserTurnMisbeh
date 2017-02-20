package org.sistema.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sistema.springmvc.dao.TurnDAO;
import org.sistema.springmvc.models.Turn;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hibernate implementation for a TurnDAO.
 * 
 * @author Eugenia Pérez Martínez.
 *
 */

public class HibernateTurnDAO implements TurnDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * gives hibernate3 Session
	 * 
	 * @return current hibernate Session
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	

	@Transactional
	public Long insert(Turn turn) {
		Long id = (Long) getSession().save(turn);
		turn.setId(id);

		return id;
	}
	

	@Transactional(readOnly = true)
	public Turn selectById(Long id) {
		Turn turn = (Turn) getSession().get(Turn.class, id);

		return turn;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Turn> selectAll() {
		List<Turn> turns = null;
		turns = getSession().createQuery("from Turn").list();
		return turns;
	}
	

	@Transactional
	public void delete(Long id) {
		Turn turn = (Turn) getSession().load(Turn.class, id);
		getSession().delete(turn);
	}

	
	@Transactional
	public void update(Turn turn) {
		getSession().merge(turn);
	}
}
