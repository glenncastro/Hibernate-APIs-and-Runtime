package com.castro.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@NamedQuery(name="UserDetails.byId", query="from UserDetails where userId = ?")
@NamedNativeQuery(name="UserDetails.byName", query="select * from USER_DETAILS where username = ?", resultClass=UserDetails.class)
@Table(name="USER_DETAILS")
@SelectBeforeUpdate
public class UserDetails {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String userName;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
