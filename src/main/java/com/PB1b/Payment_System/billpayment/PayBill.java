package com.PB1b.Payment_System.billpayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PB1b.Payment_System.dto.accounts;
import com.PB1b.Payment_System.dto.Bills;
import com.PB1b.Payment_System.email.SendEmail;
import com.PB1b.Payment_System.repo.AccountRepo;
import com.PB1b.Payment_System.service.AutoPayService;

@Component
public class PayBill {

	@Autowired
	AutoPayService autoPayService;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	SendEmail sendEmail;

	public void checkLimits(Bills bill, boolean isAutoPay) {
		double auto_pay_limit = autoPayService.getPayLimit(bill.getBiller_Code(), bill.getConsumer_No());

		if (auto_pay_limit < bill.getAmount()) {
			handleExceptions("CrossedPayLimit", bill);
		} else {
			payBill(bill);
		}

	}

	public Bills payBill(Bills bill) {
		
		double account_balance = autoPayService.getAccountBalance(bill);
		
		if (account_balance < bill.getAmount()) {
			handleExceptions("InsufficientBalance", bill);
			return bill;
		}
		
		// transfer the amount to the biller
		accounts consumer_account = accountRepo.findById(bill.getConsumer_Account_No()).get();
		consumer_account.setAmount(consumer_account.getAmount() - bill.getAmount());
		accountRepo.save(consumer_account);
		
		accounts biller_account = autoPayService.getBillerAccount(bill.getBill_Id());
		biller_account.setAmount(biller_account.getAmount() + bill.getAmount());
		accountRepo.save(biller_account);

		// Change status of pending to paid
		autoPayService.changeBillStatus(bill);
		
		return bill;
	}

	private void handleExceptions(String exception_type, Bills bill) {
		
		accounts currrent_account = accountRepo.findById(bill.getConsumer_Account_No()).get();
		
		if (exception_type == "CrossedPayLimit") {
			sendEmail.sendMail(currrent_account.getEmail_Address(), "Amount above pay limit!", "Auto Pay Amount exceeded pay limit please pay manually!");
		} else if (exception_type == "InsufficientBalance") {
			sendEmail.sendMail(currrent_account.getEmail_Address(), "Insufficient Amount balance!", "Your payment was not successful due insufficient funds in your account!");
		}
	}
}
