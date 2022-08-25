package com.PB1b.Payment_System.billpayment;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.service.AutoPayService;

@Component
public class ScheduleAutoPay {
	
	@Autowired
	AutoPayService autoPayService;
	
	@Autowired
	PayBill pay_bill;

    //	Now 6:05am
	@Scheduled(cron = "20 1 11 * * *", zone = "Asia/Calcutta")
	public void performAutoPay() {
		List<Bills> billsForDay = autoPayService.getDayBills();
		System.out.println("Bill Ids: ");
		for(Bills bill: billsForDay) {
			pay_bill.checkLimits(bill, true);
			System.out.println(bill.getBill_Id());
		}
	}
	
}
