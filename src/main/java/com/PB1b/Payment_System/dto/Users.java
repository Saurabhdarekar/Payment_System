package com.PB1b.Payment_System.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
 @Id
 private String username;
 private String pass;
 private String Role_Id;
 private int Account_No;
 


public String getRole_Id() {
	return Role_Id;
}
public void setRole_Id(String role_Id) {
	Role_Id = role_Id;
}
public int getAccount_No() {
	return Account_No;
}
public void setAccount_No(int account_No) {
	Account_No = account_No;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
 
 
}
