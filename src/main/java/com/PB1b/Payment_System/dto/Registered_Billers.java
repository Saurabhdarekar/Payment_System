package com.PB1b.Payment_System.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Registered_Billers {
	@Id
	int Reg_Id;
	int Bill_Id;
	String Biller_Ref_code ;
	String Consumer_No;
	int Account_No;
	int Auto_Pay;
	double Auto_Pay_Limit;
	
	public String getBiller_Ref_code() {
		return Biller_Ref_code;
	}
	public void setBiller_Ref_code(String biller_Ref_code) {
		Biller_Ref_code = biller_Ref_code;
	}
	public String getConsumer_No() {
		return Consumer_No;
	}
	public void setConsumer_No(String consumer_No) {
		Consumer_No = consumer_No;
	}
	public int getAccount_No() {
		return Account_No;
	}
	public void setAccount_No(int account_No) {
		Account_No = account_No;
	}
	
	public int getReg_Id() {
		return Reg_Id;
	}
	public void setReg_Id(int reg_Id) {
		Reg_Id = reg_Id;
	}
	public int getBill_Id() {
		return Bill_Id;
	}
	public void setBill_Id(int bill_Id) {
		Bill_Id = bill_Id;
	}
	public int getAuto_Pay() {
		return Auto_Pay;
	}
	public void setAuto_Pay(int auto_Pay) {
		Auto_Pay = auto_Pay;
	}
	public double getAuto_Pay_Limit() {
		return Auto_Pay_Limit;
	}
	public void setAuto_Pay_Limit(double auto_Pay_Limit) {
		Auto_Pay_Limit = auto_Pay_Limit;
	}
	public static boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
}
