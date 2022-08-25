package com.PB1b.Payment_System.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
		System.out.println(Role);
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
	
	@GetMapping("DownloadBills/{id}")
	public String ViewBills(@PathVariable int id, @CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role)
			 throws IOException{
		if(!Role.equals("AccountHolder")) {
			return "Invalid Attempt";
		}
		int Consumer_Account_No = id;//bill.getConsumer_Account_No();
		List<Bills> list = service.FindUsersAllBillsPaid(Consumer_Account_No);
        /*for(Bills t : list) {
            System.out.println(t.getBill_Id() + " " + t.getBiller_Code() + " " + t.getConsumer_Account_No() + " " + t.getConsumer_No() + " " + t.getBill_Status() + " " + t.getDue_Date()+ " " + t.getAmount());
        }*/
        File file = new File("D:/test.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        /*for(int i=0;i<list.size();i++)
        {
            bw.write(list.get(i));
            bw.newLine();
        }*/
        for(Bills t : list) {
        	bw.write(t.getBill_Id() + " " + t.getBiller_Code() + " " + t.getConsumer_Account_No() + " " + t.getConsumer_No() + " " + t.getBill_Status() + " " + t.getDue_Date()+ " " + t.getAmount());
        	bw.newLine();
        }
        bw.close();
        fw.close();
        return "Done";
	}
	
	@GetMapping("ViewUnpaidBills/{id}")
	public ResponseEntity<List<Bills>> ViewUnpaidBills(@PathVariable int id, @CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		if(!Role.equals("AccountHolder")) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		System.out.println(id);
		int Consumer_Account_No = id;//bill.getConsumer_Account_No();
		return new ResponseEntity<>(service.FindUsersAllBillsUnPaid(Consumer_Account_No), HttpStatus.OK);
	}
	
	
	
	
}
