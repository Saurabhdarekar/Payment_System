package com.PB1b.Payment_System.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PB1b.Payment_System.dto.accounts;

public interface AccountRepo extends JpaRepository<accounts, Integer>{

	@Query("select a from accounts as a where "
			+ "Account_No=(select Account_No from Registered_Billers "
			+ "Where Biller_Ref_code=(select Biller_Code from Bills where Bill_Id=?1))")
	accounts getBillerAccount(int bill_id);
}
