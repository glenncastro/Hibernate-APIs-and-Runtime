package com.castro.hibernate;

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
		
		// A scenario where the changes to the user object takes time, e.g. User Input in the UI
		Session session = factory.openSession();
		session.beginTransaction();
		UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
		session.getTransaction().commit();
		session.close();
		
		user.setUserName("Updated Username after session close");
		
		session = factory.openSession();
		session.beginTransaction();
		session.update(user);
		//user.setUserName("Change after update");
		session.getTransaction().commit();
		session.close();
		
	}
}
