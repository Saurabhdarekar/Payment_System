package com.PB1b.Payment_System.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CookieValue;
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
    
	@GetMapping("/Setcookies")
	public ResponseEntity<?> set(HttpServletResponse response){
		Cookie cookie = new Cookie("username", "Jovan");
		
		cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
		
		Cookie cookie1 = new Cookie("Role", "AccountHolder");
		
		cookie.setMaxAge(7 * 24 * 60 * 60);
	    // add cookie to response
	    response.addCookie(cookie);
	    response.addCookie(cookie1);

	    // TODO: add your login logic here
	    String jwtToken = "NOT_AVAILABLE";

	    // return response entity
	    return new ResponseEntity<>(jwtToken, HttpStatus.OK);
		//return service.FindAllMasterBillers();
	}
	@GetMapping("/ViewMasterBillers")
	public ResponseEntity<List<Master_Biller>> ViewMasterBillers(@CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role){
		if(Role.equals("AccountHolder")) {
			return new ResponseEntity<>(service.FindAllMasterBillers(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/ViewRegBillers")
	public ResponseEntity<List<Registered_Billers>> ViewRegBillers(@CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role){
		if(Role.equals("AccountHolder")) {
			return new ResponseEntity<>(service.FindAllRegisteredBillers(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("/RegBiller")
	public ResponseEntity<String> RegisterBiller(@RequestBody Registered_Billers regbiller, @CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) { //Registered_Billers regbiller
		if(!Role.equals("AccountHolder")) {
			return new ResponseEntity<>("Invalid User", HttpStatus.FORBIDDEN);
		}
		String status;
		status = "Biller Register";
		//System.out.println(name);
		System.out.println(regbiller.getConsumer_No());
		service.SaveRegisteredBiller(regbiller);	
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("/temp/{id}")
	public ResponseEntity<Registered_Billers> findRegisteredBillerById(@PathVariable int id, @CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		if(!Role.equals("AccountHolder")) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(service.findRegisteredBillerById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("DelRegBiller/{id}")
	public ResponseEntity<String> DeleteRegBiller(@PathVariable int id, @CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		System.out.println(id);
		if(!Role.equals("AccountHolder")) {
			return new ResponseEntity<>("Invalid User", HttpStatus.FORBIDDEN);
		}
		boolean flag = service.DeleteRegisteredBiller(id);
		String status;
		if(flag) {
			status = "Biller deleted Successfully";
		}else
		{
			status = "Biller not deleted Successfully";
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("ViewBills/{id}")
	public ResponseEntity<List<Bills>> ViewBills(@PathVariable int id, @CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		if(!Role.equals("AccountHolder")) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		int Consumer_Account_No = id;//bill.getConsumer_Account_No();
		return new ResponseEntity<>(service.FindUsersAllBillsPaid(Consumer_Account_No), HttpStatus.OK);
	}
	
	
}
