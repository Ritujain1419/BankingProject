package com.test.account;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.banking.account.Transaction;



class TransferCheck {
	
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
	void withdrawalCheck_PositiveTestWhentransferAmountIsMoreThan1000() {
		Assert.assertTrue(Transaction.transfer("1001","1002","2000"));
	}

	
	@Test
	void withdrawalCheck_NegativeTestWhenTransferAmountIsLessThan1000() {
		Assert.assertFalse(Transaction.transfer("1002","1003","500"));  //transfer amount should be more than 1000
	}
	
	@Test
	void withdrawalCheck_PositiveTest2WhenTransferAmountIsLessThan25000() {
		Assert.assertTrue(Transaction.transfer("1003","1004","20000"));
	}
	
	@Test
	void withdrawalCheck_NegativeTest2WhenWithdrawalAmountIsMoreThan25000() {
		Assert.assertFalse(Transaction.transfer("1004","1005","26000"));  //transfer amount should be less than 25000
	}

	
	@Test
	void withdrawalCheck_NegativeTest2WhenbalanceBecomesLessThan0() {
		Assert.assertFalse(Transaction.transfer("1001","1002","20000"));  //transfer will return false total balance is less than the amount he/she is transferring
	}
}
