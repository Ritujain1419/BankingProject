package com.test.account;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.banking.account.Transaction;



class WithdrawalCheck {
	
	@BeforeAll
	public static void createAccounts() {
		Transaction.createNewAccount("Ritu Jain");
		Transaction.createNewAccount("Radha Tiwari");
		Transaction.createNewAccount("Raj Jain");
		Transaction.createNewAccount("Agarwal Tiwari");
		Transaction.createNewAccount("Agarwal Kalyan");
		Transaction.createNewAccount("Radhe Kalyan");
		Transaction.deposit("1001","20000",false);
		Transaction.deposit("1002","20000",false);
		Transaction.deposit("1003","20000",false);
		Transaction.deposit("1004","30000",false);
		Transaction.deposit("1005","30000",false);
		Transaction.deposit("1005","30000",false);
		
	}
	@Test
	void withdrawalCheck_PositiveTestWhenWithdrawalAmountIsMoreThan1000() {
		Assert.assertTrue(Transaction.withdraw("1001","2000",false));
	}

	
	@Test
	void withdrawalCheck_NegativeTestWhenWithdrawalAmountIsLessThan1000() {
		Assert.assertFalse(Transaction.withdraw("1002","900",false));  //withdraw amount should be more than 1000
	}
	
	@Test
	void withdrawalCheck_PositiveTest2WhenWithdrawalAmountIsLessThan25000() {
		Assert.assertTrue(Transaction.withdraw("1003","10000",false));
	}
	
	@Test
	void withdrawalCheck_NegativeTest2WhenWithdrawalAmountIsMoreThan25000() {
		Assert.assertFalse(Transaction.withdraw("1004","26000",false));  //withdraw amount should be less than 25000
	}

	@Test
	void withdrawalCheck_NegativeTest2WhenNumberOfWithdrawalBecomesMoreThan3() {
		Transaction.withdraw("1005","10000",false); 
		Transaction.withdraw("1005","10000",false);
		Transaction.withdraw("1005","10000",false);
		Assert.assertFalse(Transaction.withdraw("1005","10000",false));  //withdraw will return false as 3 transactions of withdrawal happened for this account number
	}
	
	@Test
	void withdrawalCheck_NegativeTest2WhenBalanceBecomesLessThan0() {
		Assert.assertFalse(Transaction.withdraw("1006","10000",false));  //withdraw will return false total balance is less than the amount he/she is withdrawing
	}
}
