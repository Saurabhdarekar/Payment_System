package com.PB1b.Payment_System.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.dto.Master_Biller;
import com.PB1b.Payment_System.dto.Registered_Billers;
import com.PB1b.Payment_System.service.ACHService;

@RestController
public class ACHController {

	@Autowired
	ACHService service;
	//View Billers available
	
	@Autowired
    private JavaMailSender javaMailSender;
    // Sending a simple Email
    
	public void sendEmail(String Email, String Subject, String Text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(Email);

        msg.setSubject(Subject);
        msg.setText(Text);

        javaMailSender.send(msg);

    }	
	
	@GetMapping("/message")
	public void message(){
		//Email e = new Email();
		sendEmail("barclayscapstone@gmail.com", "hi", "hi");

		System.out.println("I m invoked");
	}
	
	@GetMapping("/ViewMasterBillers")
	public List<Master_Biller> ViewMasterBillers(){
		return service.FindAllMasterBillers();
	}
	
	@GetMapping("/ViewRegBillers")
	public List<Registered_Billers> ViewRegBillers(){
		return service.FindAllRegisteredBillers();
	}
	
	@PostMapping("/RegBiller")
	public String RegisterBiller(@RequestBody Registered_Billers regbiller) { //Registered_Billers regbiller
		String status;
		status = "Biller Register";
		//System.out.println(name);
		System.out.println(regbiller.getConsumer_No());
		service.SaveRegisteredBiller(regbiller);	
		return status;
	}
	
	@GetMapping("/temp/{id}")
	public Registered_Billers findRegisteredBillerById(@PathVariable int id) {
		return service.findRegisteredBillerById(id);
	}
	
	@DeleteMapping("DelRegBiller/{id}")
	public String DeleteRegBiller(@PathVariable int id) {
		System.out.println(id);
		boolean flag = service.DeleteRegisteredBiller(id);
		if(flag) {
			return "Biller deleted Successfully";
		}else
		{
			return "Biller not deleted Successfully";
		}
	}
	
	@GetMapping("ViewBills/{id}")
	public List<Bills> ViewBills(@PathVariable int id) {
		
		int Consumer_Account_No = id;//bill.getConsumer_Account_No();
		return service.FindUsersAllBillsPaid(Consumer_Account_No);
	}
	
	@GetMapping("/transactions/export")
	public void exportToCSV(HttpServletResponse response) throws IOException, PaymentsException {
		response.setContentType("text/csv");


		userService.listall(response);


	}
	/*
	@PostMapping("PayBill")
	public String PayBill(@RequestBody Bills bill) {
		
	}
	
	@GetMapping("/GetPaidBills/{Biller}")
	public String GetPaidBills(@PathVariable String Biller) {
		
	}*/
	
	
}
