package com.PB1b.Payment_System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.repo.BillsRepo;


@Repository
public class Bills_dao {

	@Autowired
	BillsRepo repo;
	
	public Bills saveBills(Bills bills) {
		return repo.save(bills);
	}
	
	public List<Bills> findAllBills(){
		return repo.findAll();
	}
	
	public Bills findBillsById(int id) {
		Optional<Bills> bills = repo.findById(id);
		if(bills.isPresent()) {
			return bills.get();
		}
		else {
			return null;
		}
	}
	
	public String deleteBills(int id) {
		Bills bills = findBillsById(id);
		if(bills != null) {
			repo.delete(bills);
			return "bill deleted";
		}
		else {
			return "no user with specified id";
		}
	}
	
	public Bills updateBills(Bills bills) {
		return repo.save(bills);
	}
	
}
