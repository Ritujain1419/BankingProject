package com.test.account;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.banking.account.Transaction;



class DepositCheck {
	
	@BeforeAll
	public static void createAccounts() {
		Transaction.createNewAccount("Ritu Jain");
		Transaction.createNewAccount("Radha Tiwari");
		Transaction.createNewAccount("Raj Jain");
		Transaction.createNewAccount("Agarwal Tiwari");
		Transaction.createNewAccount("Agarwal Kalyan");
		Transaction.createNewAccount("Radhe Kalyan");
		
	}
	
	@Test
	void depositCheck_PositiveTestWhenDepositAmountIsMoreThanorEqualTo500() {
		Assert.assertTrue(Transaction.deposit("1001","1000",false));
		Assert.assertTrue(Transaction.deposit("1001","500",false));
	}

	
	@Test
	void depositCheck_NegativeTestWhenDepositAmountIsLessThan500() {
		Assert.assertFalse(Transaction.deposit("1002","400",false));  //deposit amount should be more than 500
	}
	
	@Test
	void depositCheck_PositiveTest2WhenDepositAmountIsLessThanOrEqualTo50000() {
		Assert.assertTrue(Transaction.deposit("1003","40000",false));
	}
	
	@Test
	void depositCheck_NegativeTest2WhenDepositAmountIsMoreThan50000() {
		Assert.assertFalse(Transaction.deposit("1004","60000",false));  //deposit amount should be less than 50000
	}
	
	@Test
	void depositCheck_NegativeTest2WhenNumberOfDepositBecomesMoreThan3() {
		Transaction.deposit("1005","10000",false); 
		Transaction.deposit("1005","10000",false);
		Transaction.deposit("1005","10000",false);
		Assert.assertFalse(Transaction.deposit("1005","10000",false));  //deposit will return false as 3 transactions of deposit happened for this account number
	}
	
	@Test
	void depositCheck_NegativeTest2WhenBalanceAmountBecomesMoreThan100000() {
		Transaction.deposit("1005","50000",false); 
		Transaction.deposit("1005","40000",false); 
		Assert.assertFalse(Transaction.deposit("1005","10001",false));  //total balance becomes 100001 which exceeds 1 lakh
	}

}
