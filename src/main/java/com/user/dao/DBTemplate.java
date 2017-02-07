package com.user.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class DBTemplate {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public <T extends Serializable> T saveEntity(T clz) {
		getSession().persist(clz);
		return clz;
	}
	
	public <T extends Serializable> void updateEntity(T clz) {
        getSession().update(clz);
    }
	
	public <T extends Serializable> T getEntity(Class<T> clz, String id) {
		return (T) getSession().get(clz, id);
	}

}