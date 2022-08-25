package com.PB1b.Payment_System.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class accounts {
	
	@Id
	int Account_No;
	double Amount;
	double Current_Balance;
	String Name;
	String Email_Address;
	public int getAccount_No() {
		return Account_No;
	}
	public void setAccount_No(int account_No) {
		Account_No = account_No;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail_Address() {
		return Email_Address;
	}
	public void setEmail_Address(String email_Address) {
		Email_Address = email_Address;
	}
	public double getCurrent_Balance() {
		return Current_Balance;
	}
	public void setCurrent_Balance(double current_Balance) {
		Current_Balance = current_Balance;
	}
	
}
