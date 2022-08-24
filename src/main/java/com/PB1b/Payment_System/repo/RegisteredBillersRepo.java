package com.PB1b.Payment_System.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PB1b.Payment_System.dto.Registered_Billers;

public interface RegisteredBillersRepo extends JpaRepository<Registered_Billers, Integer>{
	@Query("SELECT u FROM Registered_Billers u WHERE u.Biller_Ref_code=?1 AND u.Consumer_No=?2")
	Registered_Billers getRegBiller(int biller_code, int consumer_number);
}
