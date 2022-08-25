package com.PB1b.Payment_System.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PB1b.Payment_System.dto.accounts;
import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.dto.Registered_Billers;
import com.PB1b.Payment_System.repo.AccountRepo;
import com.PB1b.Payment_System.repo.BillsRepo;
import com.PB1b.Payment_System.repo.RegisteredBillersRepo;


@Repository
public class AutoPayDao {
	
	@Autowired
	RegisteredBillersRepo reg_repo;
	
	@Autowired
	BillsRepo bill_repo;
	
	@Autowired
	AccountRepo accountRepo;
	
	public void enableAutoPay(String biller_code, String consumer_number) {
		Registered_Billers reg_biller = reg_repo.getRegBiller(biller_code, consumer_number);
		reg_biller.setAuto_Pay(1);
		reg_repo.save(reg_biller);
	}
	
	public void disableAutoPay(String biller_code, String consumer_number) {
		Registered_Billers reg_biller = reg_repo.getRegBiller(biller_code, consumer_number);
		reg_biller.setAuto_Pay(0);
		reg_repo.save(reg_biller);
	}
	
	public List<Bills> getDayBills(){
		Date current_date = java.sql.Date.valueOf(LocalDate.now(ZoneId.of("Asia/Calcutta")).plusDays(2));
		return bill_repo.getFilteredBills(current_date);
	}
	
	public double getPayLimit(String biller_code, String consumer_number) {
		return bill_repo.getAuto_Pay_Limit(biller_code, consumer_number);
	}
	
	public Registered_Billers editAutoPayLimit(String biller_code, String consumer_number, String new_limit) {
		Registered_Billers reg_biller = reg_repo.getRegBiller(biller_code, consumer_number);
		reg_biller.setAuto_Pay_Limit(Double.parseDouble(new_limit));
		reg_repo.save(reg_biller);
		return reg_biller;
	}
	
	public void changeBillStatus(Bills bill) {
		bill.setBill_Status(1);
		bill_repo.save(bill);
	}
	
	
	public double getAccountBalance(Bills bill) {
		return bill_repo.getAccountBalance(bill.getConsumer_Account_No());
	}
	
	public accounts getBillerAccount(int bill_id) {
		return accountRepo.getBillerAccount(bill_id);
	}
	
}
