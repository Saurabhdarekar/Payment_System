package com.PB1b.billpayment;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.PB1b.dto.Bills;
import com.PB1b.service.AutoPayService;

@Component
public class ScheduleAutoPay {
	
	@Autowired
	AutoPayService autoPayService;
	
	@Autowired
	PayBill pay_bill;

    //	Now 6:05am
	@Scheduled(cron = "0 5 6 * * *", zone = "Asia/Calcutta")
	public void performAutoPay() {
		List<Bills> billsForDay = autoPayService.getDayBills();
		for(Bills bill: billsForDay) {
			pay_bill.checkLimits(bill, true);
		}
	}
	
}
