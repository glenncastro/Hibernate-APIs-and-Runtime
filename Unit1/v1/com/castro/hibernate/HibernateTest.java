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

		Session session = factory.openSession();
		session.beginTransaction();
		
		// CREATE 10 Users
		/*for (int i=0; i<10; i++) {
			UserDetails user = new UserDetails();
			user.setUserName("User " + i);
			session.save(user);
		}*/
		
		// READ (needed for UPDATE and DELETE)
		UserDetails user = (UserDetails) session.get(UserDetails.class, 5);
		
		// UPDATE
		user.setUserName("Updated User");
		session.update(user);
		
		// DELETE
		//session.delete(user);
		
		session.getTransaction().commit();
		session.close();
		
		// READ - Accessing user outside the session
		//System.out.printf("User name pulled up is %s", user.getUserName());
	}

}
