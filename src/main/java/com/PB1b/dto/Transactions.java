package com.PB1b.dto;

import java.sql.Date;
public class Transactions {
	//int SequenceID;
	int Transaction_id;
	Date DateTime;
	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}
	int Amount;
	int DebitCredit;
	String Description;
	int Bill_Reference_No;
	public int getTransaction_id() {
		return Transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		Transaction_id = transaction_id;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public int getDebitCredit() {
		return DebitCredit;
	}
	public void setDebitCredit(int debitCredit) {
		DebitCredit = debitCredit;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getBill_Reference_No() {
		return Bill_Reference_No;
	}
	public void setBill_Reference_No(int bill_Reference_No) {
		Bill_Reference_No = bill_Reference_No;
	} 
}
