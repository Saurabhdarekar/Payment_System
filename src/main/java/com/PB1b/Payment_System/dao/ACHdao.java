package com.PB1b.Payment_System.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.dto.Master_Biller;
import com.PB1b.Payment_System.dto.Registered_Billers;
import com.PB1b.Payment_System.repo.BillsRepo;
import com.PB1b.Payment_System.repo.MasterBillersRepo;
import com.PB1b.Payment_System.repo.RegisteredBillersRepo;

import java.util.Optional;

@Repository
public class ACHdao {

	@Autowired
	MasterBillersRepo MBrepo; 
	@Autowired
	RegisteredBillersRepo RBrepo;
    @Autowired
	BillsRepo Brepo;
	
	public List<Master_Biller> FindAllMasterBillers() {
		return MBrepo.findAll();
	}
	
	public List<Registered_Billers> FindAllRegisteredBillers(){
		return RBrepo.findAll();
	}
	
	public Registered_Billers SaveRegisteredBiller(Registered_Billers rb) {
		return RBrepo.save(rb);
	}
	
	public Registered_Billers findRegisteredBillerById(int id) {
		Optional<Registered_Billers> rb = RBrepo.findById(id);
		if(rb.isPresent()) {
			return rb.get();
		}
		else {
			return null;
		}
	}
	
	public boolean DeleteRegisteredBiller(int id) {
		Registered_Billers rb = findRegisteredBillerById(id);
		if(rb != null) {
			RBrepo.delete(rb);
			return true;
		}
		else {
			return true;
		}
	}
	/*
	//For unpaid bills
	public List<Bills> FindUsersAllBills(int Consumer_Account_No) {
		return Brepo.UserBills(Consumer_Account_No);
	}
	*/
	// paid
	public List<Bills> FindUsersAllBillsPaid(int Consumer_Account_No) {
		return Brepo.UserBillsPaid(Consumer_Account_No);
	}
	public List<Bills> FindUsersAllBillsUnPaid(int Consumer_Account_No) {
		return Brepo.UserBillsUnPaid(Consumer_Account_No);
	}
}
