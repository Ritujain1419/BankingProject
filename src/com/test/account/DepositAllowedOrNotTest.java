package com.test.account;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.banking.account.Transaction;



class DepositAllowedOrNotTest {

	@Test
	void testDeposit_lessthan500Test() {
		Assert.assertTrue(Transaction.checkDepositAmountLessThan500(100));
	}
	void testDeposit_morethan5000Test() {
		Assert.assertFalse(Transaction.checkDepositAmountLessThan500(1000));
	}
	void testDeposit_morethan50000Test() {
		Assert.assertTrue(Transaction.checkDepositAmountMoreThan50000(60000));
	}
	void testDeposit_lessthan50000Test() {
		Assert.assertFalse(Transaction.checkDepositAmountMoreThan50000(40000));
	}


}
