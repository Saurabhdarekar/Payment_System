package com.PB1b.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PB1b.dto.Bills;
import com.PB1b.dto.Registered_Billers;
import com.PB1b.repo.BillsRepo;
import com.PB1b.repo.RegisteredBillersRepo;


@Repository
public class AutoPayDao {
	
	@Autowired
	RegisteredBillersRepo reg_repo;
	
	@Autowired
	BillsRepo bill_repo;
	
	public void enableAutoPay(int biller_code, int consumer_number) {
		Registered_Billers reg_biller = reg_repo.getRegBiller(biller_code, consumer_number);
		reg_biller.setAutoPay(1);
		reg_repo.save(reg_biller);
	}
	
	public void disableAutoPay(int biller_code, int consumer_number) {
		Registered_Billers reg_biller = reg_repo.getRegBiller(biller_code, consumer_number);
		reg_biller.setAutoPay(0);
		reg_repo.save(reg_biller);
	}
	
	public List<Bills> getDayBills(){
		LocalDate current_date = LocalDate.now(ZoneId.of("Asia/Calcutta")).plusDays(2);
		return bill_repo.getFilteredBills(current_date.toString());
	}
	
	public double getPayLimit(String biller_code, String consumer_number) {
		return bill_repo.getPayLimit(biller_code, consumer_number);
	}
	
	public Registered_Billers editAutoPayLimit(double biller_code, double consumer_number, double new_limit) {
		Registered_Billers reg_biller = reg_repo.getRegBiller((int)biller_code, (int)consumer_number);
		reg_biller.setLimit((int)new_limit);
		reg_repo.save(reg_biller);
		return reg_biller;
	}
	
	public void changeBillStatus(Bills bill) {
		bill.setBill_Status(1);
		bill_repo.save(bill);
	}
	
}
