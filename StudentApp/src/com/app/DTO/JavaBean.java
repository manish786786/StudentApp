package com.app.DTO;

import java.io.Serializable;

public class JavaBean implements Serializable{

	private int regno;
	private String firstname;
	private String middleName;
	private String lastName;
	private String gFirstName;
	private String gMiddleName;
	private String gLastName;
	private String isAdmin;
	private String password;
	
	
	
	public int getRegno() {
		return regno;
	}
	public void setRegno(int regno) {
		this.regno = regno;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getgFirstName() {
		return gFirstName;
	}
	public void setgFirstName(String gFirstName) {
		this.gFirstName = gFirstName;
	}
	public String getgMiddleName() {
		return gMiddleName;
	}
	public void setgMiddleName(String gMiddleName) {
		this.gMiddleName = gMiddleName;
	}
	public String getgLastName() {
		return gLastName;
	}
	public void setgLastName(String gLastName) {
		this.gLastName = gLastName;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
