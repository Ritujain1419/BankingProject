package com.banking.account;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
	private int accountNumber=1000;
	private String name;
	private int AccountBalance;
	private int numberofDepositsDoneInADay;
	private int numberofWithdrawalsDoneInADay;
	private static AtomicInteger counter = new AtomicInteger(0);
	
	public Account(String name) {
		super();
		this.accountNumber+= counter.incrementAndGet();
		this.name = name;
		this.AccountBalance = 0;
		this.numberofDepositsDoneInADay = 0;
		this.numberofWithdrawalsDoneInADay = 0;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public String getName() {
		return name;
	}

	public int getNumberofDepositsDoneInADay() {
		return numberofDepositsDoneInADay;
	}
	public void setNumberofDepositsDoneInADay(int numberofDepositsDoneInADay) {
		this.numberofDepositsDoneInADay = numberofDepositsDoneInADay;
	}
	public int getNumberofWithdrawalsDoneInADay() {
		return numberofWithdrawalsDoneInADay;
	}
	public void setNumberofWithdrawalsDoneInADay(int numberofWithdrawalsDoneInADay) {
		this.numberofWithdrawalsDoneInADay = numberofWithdrawalsDoneInADay;
	}
	public int getAccountBalance() {
		return AccountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		AccountBalance = accountBalance;
	}
}
