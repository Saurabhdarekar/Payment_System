package com.PB1b.Payment_System.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.PB1b.Payment_System.dto.Users;
import com.PB1b.Payment_System.service.User_Service;

@RestController
public class User_controller {
	@Autowired
	User_Service service;
	@PostMapping("/users")
	public Users saveUser(@RequestBody Users user) {
		return service.saveUser(user);
	}
	@GetMapping("/users")
	public List<Users> findAllUsers(){
		return service.findAllUsers();
	}
	@GetMapping("/validateUser/{username}/{pass}")
	public ResponseEntity<?> validateUser(@PathVariable String username,@PathVariable String pass,HttpServletResponse response){
		List<Users> u = service.validateUser(username,pass);
		
		

	    // return response entity
	    //return new ResponseEntity<>(jwtToken, HttpStatus.OK);
		if(u.isEmpty()) {
			String jwtToken =  "Invalid Login";
			return new ResponseEntity<>(jwtToken, HttpStatus.OK);
		}else {
			Users user = u.get(0);
			System.out.println(user.getUsername());
			Cookie cookie = new Cookie("username", user.getUsername());
			cookie.setMaxAge(1 * 24 * 60 * 60);
			cookie.setPath("/");
			Cookie cookie1 = new Cookie("Role", user.getRole_Id());
			cookie1.setMaxAge(7 * 24 * 60 * 60);
			cookie1.setPath("/");
		    response.addCookie(cookie);
		    response.addCookie(cookie1);
		    // TODO: add your login logic here
		    String jwtToken = "Logged in as "+user.getRole_Id();
		    return new ResponseEntity<>(jwtToken, HttpStatus.OK);
		}
		//return service.validateUser(username,pass);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response){
		
		Cookie cookie = new Cookie("username", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");

		Cookie cookie1 = new Cookie("Role", null);
		cookie1.setMaxAge(0);
		cookie1.setPath("/");

		//add cookie to response
		response.addCookie(cookie);
		response.addCookie(cookie1);
		String jwtToken = "Logged out ";
	    return new ResponseEntity<>(jwtToken, HttpStatus.OK);
		
	}
}
