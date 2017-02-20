package org.sistema.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sistema.springmvc.dao.MisbehaviorDAO;
import org.sistema.springmvc.models.Misbehavior;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hibernate implementation for a TurnDAO.
 * 
 * @author David Larrea.
 *
 */

public class HibernateMisbehaviorDAO implements MisbehaviorDAO {

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
	public Long insert(Misbehavior misbehavior) {
		Long id = (Long) getSession().save(misbehavior);
		misbehavior.setId(id);

		return id;
	}
	

	@Transactional(readOnly = true)
	public Misbehavior selectById(Long id) {
		Misbehavior misbehavior = (Misbehavior) getSession().get(Misbehavior.class, id);

		return misbehavior;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Misbehavior> selectAll() {
		List<Misbehavior> misbehaviors = null;
		misbehaviors = getSession().createQuery("from Misbehavior").list();
		return misbehaviors;
	}
	
	@Transactional(readOnly = true)
	public List<Misbehavior> selectByName(String title) {

		List<Misbehavior> misbehaviors = null;

		Query query = getSession().createQuery("from Misbehavior m where m.title LIKE :tit");
		query.setParameter("tit", "%" + title + "%");

		// We get a generic object list
		misbehaviors = query.list();

		return misbehaviors;
	}
	

	@Transactional
	public void delete(Long id) {
		Misbehavior misbehavior = (Misbehavior) getSession().load(Misbehavior.class, id);
		getSession().delete(misbehavior);
	}

	
	@Transactional
	public void update(Misbehavior misbehavior) {
		getSession().merge(misbehavior);
	}
	
	@Transactional
	public void deleteAll(){
		String stringQuery = "DELETE FROM Misbehavior";
		Query query = getSession().createQuery(stringQuery);
		query.executeUpdate();
	}
}
