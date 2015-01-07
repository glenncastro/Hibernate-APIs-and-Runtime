package com.castro.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.castro.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build()); 

		Session session = factory.openSession();
		session.beginTransaction();
		
		// Version 1
		/*
		Query query = session.createQuery("from UserDetails");
		
		// Pagination
		query.setFirstResult(5);
		query.setMaxResults(4);
		
		List<UserDetails> users = query.list();
		*/
		
		// Version 2
		Query query = session.createQuery("select userName from UserDetails");
		// pagination
		query.setFirstResult(5);
		query.setMaxResults(4);
		
		List<String> users = (List<String>)query.list();
		
		session.getTransaction().commit();
		session.close();
		
		// Version 1
		/*
		for (UserDetails user: users) {
			System.out.println(user.getUserName());
		}
		*/
		
		// Version 2
		for (String user: users) {
			System.out.println(user);
		}
	}

}
