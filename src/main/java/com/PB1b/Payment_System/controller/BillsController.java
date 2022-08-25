package com.PB1b.Payment_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.service.BillsService;

@RestController
public class BillsController {

	@Autowired
	BillsService service;
	
	
	@PostMapping("/bills")
	public ResponseEntity<Bills> saveBills(@RequestBody Bills bills,@CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		if(Role.equals("BankManager")) {
			return new ResponseEntity<>(service.saveBills(bills), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/bills")
	public ResponseEntity<List<Bills>> findAllBills(@CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role){
		if(Role.equals("BankManager")) {
			return new ResponseEntity<>(service.findAllBills(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	@GetMapping("/bills/{id}")
	public ResponseEntity<Bills> findUserbyId(@PathVariable int id, @RequestBody Bills bills,@CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		if(Role.equals("BankManager")) {
			return new ResponseEntity<>(service.findBillsById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	@DeleteMapping("/bills/{id}")
	public ResponseEntity<String> deleteBills(@PathVariable int id, @RequestBody Bills bills,@CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		if(Role.equals("BankManager")) {
			return new ResponseEntity<>(service.deleteBills(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	@PutMapping("/bills")
	public ResponseEntity<Bills> updateBills(@RequestBody Bills bills,@CookieValue(value = "username", defaultValue = "Atta") String username, @CookieValue(value = "Role", defaultValue = "None") String Role) {
		if(Role.equals("BankManager")) {
			return new ResponseEntity<>(service.updateBills(bills), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}	
}
