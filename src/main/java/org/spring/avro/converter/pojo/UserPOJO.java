package org.spring.avro.converter.pojo;

import java.io.Serializable;

public class UserPOJO implements Serializable{

	private static final long serialVersionUID = -5190700245827446401L;
	
	private String firstName;
	private String lastName;
	private String emailId;
	
	public UserPOJO(){}
	public UserPOJO(String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
