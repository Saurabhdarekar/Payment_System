package com.PB1b.Payment_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.PB1b.Payment_System.dao.User_dao;
import com.PB1b.Payment_System.dto.Users;

@Service
public class User_Service {
@Autowired
User_dao user_dao;
public Users saveUser(Users user) {
	return user_dao.saveUser(user);
}
public List<Users> findAllUsers(){
	return user_dao.findAllUsers();
}

public List<Users> validateUser(String username, String pass){
	return user_dao.validateUser(username,pass);
}
}
