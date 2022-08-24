package com.PB1b.Payment_System.billpayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.service.AutoPayService;

@Component
public class PayBill {

	@Autowired
	AutoPayService autoPayService;

	public void checkLimits(Bills bill, boolean isAutoPay) {
		double auto_pay_limit = autoPayService.getPayLimit(bill.getBiller_Code(), bill.getConsumer_No());

		if (auto_pay_limit < bill.getAmount()) {
			handleExceptions("CrossedPayLimit");
		} else {
			payBill(bill);
		}

		// get the account balance
	}

	private void payBill(Bills bill) {
		// transfer the amount to the biller

		// Change status of pending to paid
		autoPayService.changeBillStatus(bill);
	}

	private void handleExceptions(String exception_type) {
		if (exception_type == "CrossedPayLimit") {
			// send mail if auto pay limit crossed
			System.out.println("Amount above pay limit!");
		} else if (exception_type == "InsufficientBalance") {
			// send mail if insufficient balance
			System.out.println("Insufficient Amount balance!");
		}
	}
}
