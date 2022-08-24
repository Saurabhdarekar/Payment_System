package com.PB1b.Payment_System.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Registered_Billers {
	@Id
	int RegId;
	String Biller_Ref_code ;
	String Consumer_No;
	int Account_No;
	int AutoPay;
	int PayLimit;
	public int getRegId() {
		return RegId;
	}
	public void setRegId(int regId) {
		RegId = regId;
	}
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
	public int getAutoPay() {
		return AutoPay;
	}
	public void setAutoPay(int autoPay) {
		AutoPay = autoPay;
	}
	public int getPayLimit() {
		return PayLimit;
	}
	public void setPayLimit(int limit) {
		PayLimit = limit;
	}
	public static boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
}
