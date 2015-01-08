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
		
		// SQL injection example
		/*
		String minUserId = " 5 or 1=1";
		Query query = session.createQuery("from UserDetails where userId >" + minUserId);
		*/
		
		String minUserId = "5";
		String userName = "User 10";
		
		// Parameter substitution using ? and :
		
		// Version 1 - using ?
		/*
		Query query = session.createQuery("from UserDetails where userId > ? and userName = ?");
		query.setInteger(0, Integer.parseInt(minUserId));
		query.setString(1, userName);
		*/
		
		// Version 2 - using :
		Query query = session.createQuery("from UserDetails where userId > :userId and userName = :userName");
		query.setInteger("userId", Integer.parseInt(minUserId));
		query.setString("userName", userName);
		
		List<UserDetails> users = (List<UserDetails>)query.list();
		
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails user: users) {
			System.out.println(user.getUserName());
		}
	}
}
