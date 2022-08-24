package com.PB1b.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PB1b.dto.Bills;

public interface BillsRepo extends JpaRepository<Bills, Integer> {
	
	@Query("Select")
	List<Bills> UserBills(int Consumer_Account_No);

	@Query("select b from Bills as b where (select AutoPay "
			+ "from Registered_Billers where Biller_Ref_code=b.Biller_Code "
			+ "AND Consumer_No=b.Consumer_No)=1 "
			+ "AND b.Bill_Status=0 "
			+ "AND b.Due_Date<=?1")
	List<Bills> getFilteredBills(String current_date);
	
	
	@Query("select Limit from Registered_Billers where Biller_Ref_code=?1 AND Consumer_No=?2")
	double getPayLimit(String biller_code, String consumer_number); 
}
