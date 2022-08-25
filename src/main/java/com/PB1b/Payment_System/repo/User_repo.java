package com.PB1b.Payment_System.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PB1b.Payment_System.dto.Users;

public interface User_repo extends JpaRepository<Users,Integer> {

	@Query("SELECT u FROM Users u where u.username=?1 and pass=?2")
	List<Users> validateUser(String username, String pass);
}
