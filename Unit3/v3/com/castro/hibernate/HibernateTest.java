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
		
		// Using Query Cache
		Query query = session.createQuery("from UserDetails user where user.userId = 1");
		query.setCacheable(true);
		List users = query.list();
		
		session.getTransaction().commit();
		session.close();
		
		Session session2 = factory.openSession();
		session2.beginTransaction();
		
		Query query2 = session2.createQuery("from UserDetails user where user.userId = 1");
		query2.setCacheable(true);
		users = query2.list(); 
		
		session2.getTransaction().commit();
		session2.close();
	}

}
