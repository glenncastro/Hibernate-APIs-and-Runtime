package com.castro.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.castro.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build()); 

		Session session = factory.openSession();
		session.beginTransaction();
		
		// Version 1 - Using Projections
		/*
		Criteria criteria = session.createCriteria(UserDetails.class)
				.setProjection(Projections.property("userName"))
				//.setProjection(Projections.max("userId"));
				//.setProjection(Projections.count("userId"));
				.addOrder(Order.desc("userId"));
		*/
		
		UserDetails exampleUser = new UserDetails();
		exampleUser.setUserId(5); // ignored the PK or when value is null
		exampleUser.setUserName("User 0%");
		
		Example example = Example.create(exampleUser).enableLike(); //.excludeProperty("userName");
		Criteria criteria = session.createCriteria(UserDetails.class)
				.add(example);
		
		
		List<UserDetails> users = (List<UserDetails>) criteria.list();
		
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails user: users) {
			System.out.println(user.getUserName());
		}
	}
}
