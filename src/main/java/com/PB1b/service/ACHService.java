package com.PB1b.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.PB1b.dao.ACHdao;
import com.PB1b.dto.MasterBillers;
import com.PB1b.dto.RegisteredBiller;

public class ACHService {
	
	@Autowired
	ACHdao dao;
	
	public List<MasterBillers> FindAllMasterBillers() {
		return dao.FindAllMasterBillers();
	}
	
	public List<RegisteredBiller> FindAllRegisteredBillers(){
		return dao.FindAllRegisteredBillers();
	}
	
	public RegisteredBiller SaveRegisteredBiller(RegisteredBiller rb) {
		return dao.SaveRegisteredBiller(rb);
	}
	
	public RegisteredBiller findRegisteredBillerById(int id) {
		return dao.findRegisteredBillerById(id);
	}
	
	public boolean DeleteRegisteredBiller(int id) {
		return dao.DeleteRegisteredBiller(id);
	}
}
