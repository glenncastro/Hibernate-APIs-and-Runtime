package com.castro.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.castro.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build()); 

		Session session = factory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(UserDetails.class);
		
		// Version 1 - using like, between, gt, etc.
		/*
		criteria.add(Restrictions.like("userName", "User 0%"))
			//.add(Restrictions.gt("userId", 5));
			.add(Restrictions.between("userId", 5, 9));
		*/
		
		// Version 2 - Restrictions.or
		criteria.add(Restrictions.or(
				Restrictions.between("userId", 0, 3),
				Restrictions.between("userId", 7, 10)));
		
		List<UserDetails> users = (List<UserDetails>) criteria.list();
		
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails user: users) {
			System.out.println(user.getUserName());
		}
	}
}
