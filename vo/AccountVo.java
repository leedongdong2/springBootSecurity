package com.example.springBootTest.vo;

import java.util.Date;

public class AccountVo {
	
	private String userId;
	private int accountNo;
	private String accountDate;
	private String accountStartDate;
	private String accountEndDate;
	private String accountItem;
	private String accountBriefs;
	private Integer accountIncome = 0;
	private Integer accountExpenditure = 0;
	private Integer accountChangeIncome = 0;
	private Integer accountChangeExpenditure = 0 ;
	private Integer changedValue = 0;
	private Integer accountBalance;
	private String accountNote;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	public String getAccountItem() {
		return accountItem;
	}
	public void setAccountItem(String accountItem) {
		this.accountItem = accountItem;
	}
	public String getAccountBriefs() {
		return accountBriefs;
	}
	public void setAccountBriefs(String accountBriefs) {
		this.accountBriefs = accountBriefs;
	}
	public Integer getAccountIncome() {
		if(accountIncome == null) {
			accountIncome = 0;
		}
		return accountIncome;
	}
	public void setAccountIncome(Integer accountIncome) {
		this.accountIncome = accountIncome;
	}
	public Integer getAccountExpenditure() {
		if(accountExpenditure == null) {
			accountExpenditure = 0;
		}
		return accountExpenditure;
	}
	public void setAccountExpenditure(Integer accountExpenditure) {
		this.accountExpenditure = accountExpenditure;
	}
	public Integer getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountNote() {
		return accountNote;
	}
	public void setAccountNote(String accountNote) {
		this.accountNote = accountNote;
	}
	public String getAccountStartDate() {
		return accountStartDate;
	}
	public void setAccountStartDate(String accountStartDate) {
		this.accountStartDate = accountStartDate;
	}
	public String getAccountEndDate() {
		return accountEndDate;
	}
	public void setAccountEndDate(String accountEndDate) {
		this.accountEndDate = accountEndDate;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	public Integer getAccountChangeIncome() {
		return accountChangeIncome;
	}
	public void setAccountChangeIncome(Integer accountChangeIncome) {
		this.accountChangeIncome = accountChangeIncome;
	}
	public Integer getAccountChangeExpenditure() {
		return accountChangeExpenditure;
	}
	public void setAccountChangeExpenditure(Integer accountChangeExpenditure) {
		this.accountChangeExpenditure = accountChangeExpenditure;
	}
	public Integer getChangedValue() {
		return changedValue;
	}
	public void setChangedValue(Integer changedValue) {
		this.changedValue = changedValue;
	}
}
