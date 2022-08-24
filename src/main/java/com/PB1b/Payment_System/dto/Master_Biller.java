package com.PB1b.Payment_System.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Master_Biller {
	@Id
	String BillerCode;
	String Name;
	public String getBillerCode() {
		return BillerCode;
	}
	public void setBillerCode(String billerCode) {
		BillerCode = billerCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
}
