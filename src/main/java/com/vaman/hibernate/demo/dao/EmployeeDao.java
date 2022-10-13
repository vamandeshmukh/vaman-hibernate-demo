package com.vaman.hibernate.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeDao {

	private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("abc");
	private final EntityManager manager = factory.createEntityManager();

	public EmployeeDao() {
		super();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void beginTransaction() {

		manager.getTransaction().begin();
	}

	public void commitTransaction() {

		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}

}
