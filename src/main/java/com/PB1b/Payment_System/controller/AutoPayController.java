package com.PB1b.Payment_System.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.PB1b.Payment_System.billpayment.PayBill;
import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.dto.Registered_Billers;
import com.PB1b.Payment_System.repo.BillsRepo;
import com.PB1b.Payment_System.service.AutoPayService;

@RestController
public class AutoPayController {
	
	@Autowired
	AutoPayService autoPayService;
	
	@Autowired
	PayBill pay_bill;
	
	@Autowired
	BillsRepo bill_repo;

	
	@PostMapping("/enableauto")
	public String enableAutoPay(@RequestBody Map<String, String> body) {
		autoPayService.enableAutoPay(body.get("biller_code"), body.get("consumer_number"));
		return "Enabled Auto Pay feature";
	}
	
	@PostMapping("/disableauto")
	public String disableAutoPay(@RequestBody Map<String, String> body) {
		autoPayService.disableAutoPay(body.get("biller_code"), body.get("consumer_number"));
		return "Disabled Auto Pay feature";
	}
	
	@PostMapping("/editautolimit")
	public Registered_Billers editAutoPayLimit(@RequestBody Map<String, String> body) {
		return autoPayService.editAutoPayLimit(body.get("biller_code"), body.get("consumer_number"), body.get("new_limit"));
	}
	
	@PostMapping("/paybill/{bill_id}")
	public Bills payBillManually(@PathVariable int bill_id) {
		Bills bill = bill_repo.findById(bill_id).get();
		return pay_bill.payBill(bill);
	}

	
}
