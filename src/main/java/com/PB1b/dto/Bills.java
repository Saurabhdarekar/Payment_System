package com.PB1b.dto;

public class Bills {
	int Bill_Id;
	String Biller_Code;
	String Consumer_No;
	int Amount;
	String Due_Date;
	int Bill_Status;
	int Consumer_Account_No;
	public int getBill_Id() {
		return Bill_Id;
	}
	public void setBill_Id(int bill_Id) {
		Bill_Id = bill_Id;
	}
	public String getBiller_Code() {
		return Biller_Code;
	}
	public void setBiller_Code(String biller_Code) {
		Biller_Code = biller_Code;
	}
	public String getConsumer_No() {
		return Consumer_No;
	}
	public void setConsumer_No(String consumer_No) {
		Consumer_No = consumer_No;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public String getDue_Date() {
		return Due_Date;
	}
	public void setDue_Date(String due_Date) {
		Due_Date = due_Date;
	}
	public int getBill_Status() {
		return Bill_Status;
	}
	public void setBill_Status(int bill_Status) {
		Bill_Status = bill_Status;
	}
	public int getConsumer_Account_No() {
		return Consumer_Account_No;
	}
	public void setConsumer_Account_No(int consumer_Account_No) {
		Consumer_Account_No = consumer_Account_No;
	}
	
}
