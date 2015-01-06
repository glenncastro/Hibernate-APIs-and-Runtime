package com.castro.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.castro.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		
		// TRANSIENT Object
		UserDetails user = new UserDetails();
		user.setUserName("Test User");
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build()); 

		Session session = factory.openSession();
		session.beginTransaction();
		
		// PERSISTENT Object - Hibernate tracks
		session.save(user);
		
		// after the session.save()
		user.setUserName("Updated User");
		user.setUserName("Updated User Again");
		
		session.getTransaction().commit();
		
		session.close();
		
		// DETACHED Object
		user.setUserName("Updated User After Session Close");
		
	}

}
