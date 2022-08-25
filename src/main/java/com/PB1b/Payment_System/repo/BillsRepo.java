package com.PB1b.Payment_System.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PB1b.Payment_System.dto.Bills;

public interface BillsRepo extends JpaRepository<Bills, Integer> {
	
//	@Query("Select")
//	List<Bills> UserBills(int Consumer_Account_No);

	@Query("select b from Bills as b where (select Auto_Pay "
			+ "from Registered_Billers where Biller_Ref_code=b.Biller_Code "
			+ "AND Consumer_No=b.Consumer_No)=1 "
			+ "AND b.Bill_Status=0 "
			+ "AND b.Due_Date<=?1")
	List<Bills> getFilteredBills(Date current_date);
	
	
	@Query("select Auto_Pay_Limit from Registered_Billers where Biller_Ref_code=?1 AND Consumer_No=?2")
	double getAuto_Pay_Limit(String biller_code, String consumer_number); 
	
	@Query("SELECT u FROM Bills u where u.Consumer_Account_No=?1 and u.Bill_Status = 1 ")
	List<Bills> UserBillsPaid(int Consumer_Account_No);
	
	@Query("Select u From Bills u where u.Consumer_Account_No=?1 and u.Bill_Status = 0 ")
	List<Bills> UserBillsUnPaid(int Consumer_Account_No);

	@Query("select Current_Balance from accounts where Account_No=?1")
	double getAccountBalance(int account_number);

}
//"SELECT u FROM Bills u WHERE u.Consumer_Account_No=?1 AND u.Bill_Status=1")