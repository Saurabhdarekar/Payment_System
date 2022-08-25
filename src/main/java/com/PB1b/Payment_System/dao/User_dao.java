package com.PB1b.Payment_System.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PB1b.Payment_System.dto.Users;
import com.PB1b.Payment_System.repo.User_repo;

@Repository
public class User_dao {
	@Autowired
	User_repo user_repo;
	
	public Users saveUser(Users user) {
		return user_repo.save(user);
	}
	 public List<Users> findAllUsers(){
		return user_repo.findAll();
	}
	public List<Users> validateUser(String username, String pass){
		System.out.println(username + pass);
		return user_repo.validateUser(username,pass);
	}
}