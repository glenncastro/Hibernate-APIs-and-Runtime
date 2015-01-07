package com.castro.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build()); 

		Session session = factory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from UserDetails where userId > 5");
		@SuppressWarnings("rawtypes")
		List users = query.list();
		
		session.getTransaction().commit();
		session.close();
		
		System.out.printf("Size of list result = %s", users.size());
	}

}
