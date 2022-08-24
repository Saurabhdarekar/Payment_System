package com.PB1b.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PB1b.dto.Master_Biller;
import com.PB1b.dto.Registered_Billers;
import com.PB1b.repo.MasterBillersRepo;
import com.PB1b.repo.RegisteredBillersRepo;


@Repository
public class ACHdao {

	@Autowired
	MasterBillersRepo MBrepo; 
	RegisteredBillersRepo RBrepo;
	
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
		if(Registered_Billers.isPresent()) {
			//return Registered_Billers;
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
	
	public List<Master_Biller> FindAllBills() {
		return MBrepo.findAll();
	}
}
