package com.PB1b.Payment_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.PB1b.Payment_System.dao.Bills_dao;
import com.PB1b.Payment_System.dto.Bills;

@Service
public class BillsService {

	@Autowired
	Bills_dao dao;
	
	
	public Bills saveBills(Bills bills) {
		return dao.saveBills(bills);
	}
	public List<Bills> findAllBills(){
		return dao.findAllBills();
	}
	public Bills findBillsById(int id) {
		return dao.findBillsById(id);
	}
	public String deleteBills(int id) {
		return dao.deleteBills(id);
	}
	public Bills updateBills(Bills bills) {
		return dao.updateBills(bills);
	}
	
}
