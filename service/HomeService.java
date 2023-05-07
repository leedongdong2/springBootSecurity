package com.example.springBootTest.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springBootTest.mapper.HomeMapper;
import com.example.springBootTest.page.Page;
import com.example.springBootTest.vo.AccountVo;

@Service	
public class HomeService {
	
	@Autowired
	HomeMapper homeMapper;
	
	
	
	/**
	 * 	 * 
	 * @param page
	 * @return
	 * 
	 * 당일 기록 찾기 서비스
	 */
	public List<AccountVo> searchHomeAccount(Page page){
		int accountListCount = homeMapper.searchHomeAccountCount(page);
		page.setTotList(accountListCount);
		page.compute();
		List<AccountVo> accountList = homeMapper.searchHomeAccount(page);
		return accountList;
	}
	
	/**
	 * 
	 * @param page
	 * @return
	 * 
	 * 조회 요청 정보 찾기 서비스
	 */
	public List<AccountVo> findHomeAccount(Page page){
		int accountListCount = homeMapper.findAccountBookListCount(page);
		page.setTotList(accountListCount);
		page.compute();
		List<AccountVo> accountList = homeMapper.findAccountBookList(page);
		return accountList;
	}
	
	
	/**
	 * 
	 * @param accountVo
	 * @return
	 * 
	 * 가계 입력 서비스
	 */
	@Transactional
	public String insertAccout(AccountVo accountVo) {
		homeMapper.insertAccount(accountVo);
		return null;
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * 
	 * 남은 잔액 찾기
	 */
	public Integer recentryBalance(String userId) {
		Integer recentryBalance = homeMapper.searchRecentryBalance(userId);
		return recentryBalance;
	}
	
	/**
	 * 
	 * @param accountVo
	 * @return
	 * 가계 입력시 수입 지출에 따른 남은 잔액 계산
	 */
	public Integer calculateBalance(AccountVo accountVo) {
		Integer recentryBalance = recentryBalance(accountVo.getUserId());
		Integer balance = ( recentryBalance + accountVo.getAccountIncome() ) - accountVo.getAccountExpenditure();
		return balance;
	}
	
	
	/**
	 * 
	 * @param page
	 * @return
	 * 
	 * 당일 수입,지출 계산
	 */
	public HashMap<String,String> totalAccount(Page page){
		HashMap<String,String> totalAccount = homeMapper.totalAccount(page);
		
		if(totalAccount == null) {
				totalAccount = new HashMap<String,String>();
				totalAccount.put("totalAccountIncome", "0");
				totalAccount.put("totalAccountExpenditure", "0");
		}
		
		return totalAccount;
	}
	/**
	 * 
	 * 
	 * @param page
	 * @return
	 * 
	 * 검색시 조회된 수입,지출 계산
	 * 
	 */
	public HashMap<String,String> findTotalAccount(Page page){
		HashMap<String,String> findTotalAccount = homeMapper.findTotalAccount(page);
		return findTotalAccount;
	}

	/**
	 * 
	 * @param serial
	 * @return
	 * 수정할 가계 단건 조회
	 */
	public AccountVo findDetailAccount(String serial) {
		AccountVo accountVo = homeMapper.findDetailAccount(serial);
		return accountVo;	
	}
	
	/**
	 * 
	 * @param accountVo
	 * 수정기능
	 */
	@Transactional
	public void modifyAccountDetail(AccountVo accountVo) {
	    homeMapper.modifyAccountDetail(accountVo);
	}
	
	/**
	 * 
	 * @param accountVo
	 * 수정된 가계의 변경된 수입 지출의 차액값을 
	 * 수정한 가계 이후의 가계의 남은 잔액에 반영
	 */
	@Transactional
	public void modifyAccountBalance(AccountVo accountVo) {
		homeMapper.modifyAccountBalance(accountVo);
	}
	
	/**
	 * 
	 * @param accountVo
	 * 삭제기능
	 */
	@Transactional
	public void deleteAccountDetail(AccountVo accountVo) {
		homeMapper.deleteAccountDetail(accountVo);
	}
	
	/**
	 * 
	 * @param accountVo
	 * @return
	 * 
	 * 수정시 수정된 가계의 수입과 지출의 차액 계산
	 * 
	 */
	public int calculateChangedValue(AccountVo accountVo) {
		int changedValue = 0;
		/*수입 변수*/
		int accountIncome = 0;
		int beforeAccountIncome = accountVo.getAccountIncome();
		int afterAccountIncome = accountVo.getAccountChangeIncome();
		
		/*지출 변수*/
		int accountExpenditure = 0;
		int befoerAccountExpenditure = accountVo.getAccountExpenditure();
		int afterAccountExpenditure = accountVo.getAccountChangeExpenditure();
	
		/*수입 재계산*/		
		if ( beforeAccountIncome < afterAccountIncome ) {
				accountIncome = afterAccountIncome - beforeAccountIncome;
		} else if( beforeAccountIncome > afterAccountIncome ) {
				accountIncome = afterAccountIncome - beforeAccountIncome;
		} else if( beforeAccountIncome == afterAccountIncome ) {
				accountIncome = 0;
		}
		
		/*지출 재계산*/
		if( befoerAccountExpenditure < afterAccountExpenditure ) {
			accountExpenditure = befoerAccountExpenditure - afterAccountExpenditure;
		} else if( befoerAccountExpenditure > afterAccountExpenditure ) {
			accountExpenditure = befoerAccountExpenditure - afterAccountExpenditure;
		} else if( befoerAccountExpenditure == afterAccountExpenditure ) {
			accountExpenditure = 0;
		}
		
		
		changedValue = accountIncome + accountExpenditure;
		return changedValue;
	}
	
	/**
	 * 
	 * @param accountVo
	 * @return
	 * 가계 삭제시 삭제된 가계의 수입 지출 차액 계산
	 */
	public int calculateDeleteValue(AccountVo accountVo) {
		int deleteValue = accountVo.getAccountExpenditure() - accountVo.getAccountIncome();
		return deleteValue;
	}
	
	public List<HashMap<String,String>> findUser(Page page) {
		int totList = homeMapper.findUserCount();
		page.setTotList(totList);
		page.compute();
		List<HashMap<String,String>> users = homeMapper.findUser(page);
		return users;
	}
}
