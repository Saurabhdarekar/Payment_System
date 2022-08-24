package com.PB1b.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.PB1b.dto.RegisteredBiller;
import com.PB1b.service.ACHService;
import com.PB1b.dto.MasterBillers;
import com.PB1b.dto.Bills;

@RestController
public class ACHController {

	@Autowired
	ACHService service;
	//View Billers available
	@GetMapping("/ViewMasterBillers")
	public List<MasterBillers> ViewMasterBillers(){
		return service.FindAllMasterBillers();
	}
	
	@GetMapping("/ViewRegBillers")
	public List<RegisteredBiller> ViewRegBillers(){
		return service.FindAllRegisteredBillers();
	}
	
	@PostMapping("/RegBiller")
	public String RegisterBiller(@RequestBody RegisteredBiller obj) {
		String status;
		status = "Biller Register";
		service.SaveRegisteredBiller(obj);	
		return status;
	}
	
	@DeleteMapping("DelRegBiller")
	public String DeleteRegBiller(@RequestBody RegisteredBiller rbiller) {
		boolean flag = service.DeleteRegisteredBiller(obj.id);
		if(flag) {
			return "Biller deleted Successfully";
		}else
		{
			return "Biller not deleted Successfully";
		}
	}
	
	@GetMapping("ViewBills")
	public List<Bills> ViewBills() {
		
	}
	
	@PostMapping("PayBill")
	public String PayBill(@RequestBody Bills bill) {
		
	}
	
	@GetMapping("/GetPaidBills/{Biller}")
	public String GetPaidBills(@PathVariable String Biller) {
		
	}
	
	
}
