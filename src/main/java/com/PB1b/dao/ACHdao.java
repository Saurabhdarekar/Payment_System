package com.PB1b.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.PB1b.dto.MasterBillers;
import com.PB1b.dto.RegisteredBiller;
import com.PB1b.repo.MasterBillersRepo;
import com.PB1b.repo.RegisteredBillersRepo;
import com.org.springbootuser.dto.User;


@Repository
public class ACHdao {

	@Autowired
	MasterBillersRepo MBrepo; 
	RegisteredBillersRepo RBrepo;
	
	public List<MasterBillers> FindAllMasterBillers() {
		return MBrepo.findAll();
	}
	
	public List<RegisteredBiller> FindAllRegisteredBillers(){
		return RBrepo.findAll();
	}
	
	public RegisteredBiller SaveRegisteredBiller(RegisteredBiller rb) {
		return RBrepo.save(rb);
	}
	
	public RegisteredBiller findRegisteredBillerById(int id) {
		Optional<RegisteredBiller> rb = RBrepo.findById(id);
		if(RegisteredBiller.isPresent()) {
			return RegisteredBiller.get();
		}
		else {
			return null;
		}
	}
	
	public boolean DeleteRegisteredBiller(int id) {
		RegisteredBiller rb = findRegisteredBillerById(id);
		if(rb != null) {
			RBrepo.delete(rb);
			return true;
		}
		else {
			return true;
		}
	}
	
	public List<MasterBillers> FindAllBills() {
		return MBrepo.findAll();
	}
}
