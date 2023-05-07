package com.example.springBootTest.mapper;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springBootTest.page.Page;
import com.example.springBootTest.vo.AccountVo;


@Mapper
public interface HomeMapper {
		public void insertAccount(AccountVo accountVo);
		public Integer searchRecentryBalance(String userId);
		public List<AccountVo> searchHomeAccount(Page page);
		public int searchHomeAccountCount(Page page);
		public List<AccountVo> findAccountBookList(Page page);
		public int findAccountBookListCount(Page page);
		public HashMap<String,String> totalAccount(Page page);
		public HashMap<String,String> findTotalAccount(Page page);
		public AccountVo findDetailAccount(String serial);
		public void modifyAccountDetail(AccountVo accountVo);
		public void modifyAccountBalance(AccountVo accountVo);
		public void deleteAccountDetail(AccountVo accountVO);
		public List<HashMap<String,String>> findUser(Page page);
		public int findUserCount();
}
