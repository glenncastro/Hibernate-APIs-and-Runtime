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
		
		// Version 1 - @NamedQuery
		/*
		Query query = session.getNamedQuery("UserDetails.byId");
		query.setInteger(0, 2); // hardcoded value for testing
		*/
		
		// Version 2 - @NamedNativeQuery
		Query query = session.getNamedQuery("UserDetails.byName");
		query.setString(0, "User 10");
		
		List<UserDetails> users = (List<UserDetails>)query.list();
		
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails user: users) {
			System.out.println(user.getUserName());
		}
	}
}
