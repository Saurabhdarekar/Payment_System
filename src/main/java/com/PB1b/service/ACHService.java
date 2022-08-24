package com.PB1b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PB1b.dao.ACHdao;
import com.PB1b.dto.Bills;
import com.PB1b.dto.Master_Biller;
import com.PB1b.dto.Registered_Billers;

@Service
public class ACHService {
	
	@Autowired
	ACHdao dao;
	
	public List<Master_Biller> FindAllMasterBillers() {
		return dao.FindAllMasterBillers();
	}
	
	public List<Registered_Billers> FindAllRegisteredBillers(){
		return dao.FindAllRegisteredBillers();
	}
	
	public Registered_Billers SaveRegisteredBiller(Registered_Billers rb) {
		return dao.SaveRegisteredBiller(rb);
	}
	/*
	public Registered_Billers findRegisteredBillerById(int id) {
		return dao.findRegisteredBillerById(id);
	}
	
	public boolean DeleteRegisteredBiller(int id) {
		return dao.DeleteRegisteredBiller(id);
	}*/
	public List<Bills> FindUsersAllBills(int Consumer_Account_No) {
		return dao.FindUsersAllBills(Consumer_Account_No);
	}
}
