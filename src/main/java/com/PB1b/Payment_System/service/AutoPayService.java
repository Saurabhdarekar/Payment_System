package com.PB1b.Payment_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PB1b.Payment_System.billpayment.PayBill;
import com.PB1b.Payment_System.dao.AutoPayDao;
import com.PB1b.Payment_System.dto.accounts;
import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.dto.Registered_Billers;
import com.PB1b.Payment_System.repo.AccountRepo;

@Service
public class AutoPayService {

	
	@Autowired
	AutoPayDao autoPayDao;

	
	public void enableAutoPay(int biller_code, int consumer_code) {
		autoPayDao.enableAutoPay(biller_code, consumer_code);
	}
	
	public void disableAutoPay(int biller_code, int consumer_code) {
		autoPayDao.disableAutoPay(biller_code, consumer_code);
	}
	
	public List<Bills> getDayBills(){
		return autoPayDao.getDayBills();
	}
	
	public double getPayLimit(String biller_code, String consumer_number) {
		return autoPayDao.getPayLimit(biller_code, consumer_number);
	}
	
	public Registered_Billers editAutoPayLimit(double biller_code, double consumer_number, double new_limit) {
		return autoPayDao.editAutoPayLimit(biller_code, consumer_number, new_limit);
	}
	
	public void changeBillStatus(Bills bill) {
		autoPayDao.changeBillStatus(bill);
	}
	
	public double getAccountBalance(Bills bill) {
		return autoPayDao.getAccountBalance(bill);
	}
	
	public accounts getBillerAccount(int bill_id) {
		return autoPayDao.getBillerAccount(bill_id);
	}
}
 