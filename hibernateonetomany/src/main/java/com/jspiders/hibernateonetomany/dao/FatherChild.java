package com.jspiders.hibernateonetomany.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernateonetomany.dto.Child;
import com.jspiders.hibernateonetomany.dto.Father;


public class FatherChild {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void  openConnection() {
		
		factory=Persistence.createEntityManagerFactory("OneToMany");
		
		manager=factory.createEntityManager();
		
		transaction=manager.getTransaction();
		
	}
	
	private static void closeConnection() {
		
		if (factory !=null) {
			factory.close();
			
		}
		if (manager !=null) {
			manager.close();
			
		}
		if (transaction.isActive()) {
			transaction.rollback();
			
		}
	}
	
	public static void main(String[] args) {
		
		openConnection();
		transaction.begin();
		
		Child child1=new Child();
		child1.setId(1);
		child1.setName("Sonu");
		child1.setAge(23);
		manager.persist(child1);
		
		Child child2=new Child();
		child2.setId(2);
		child2.setName("Priya");
		child2.setAge(22);
		manager.persist(child2);
		
		List<Child>children=Arrays.asList(child1,child2);
		
		Father father=new Father();
		father.setId(1);
		father.setName("Arun");
		father.setAge(45);
		father.setChildren(children);
		
		manager.persist(father);
		
		transaction.commit();
		closeConnection();
		
	}

}
