package com.example.springBootTest.mapper;


import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.springBootTest.configuration.ParamMap;
import com.example.springBootTest.vo.UserVo;

@Mapper
public interface UserMapper {
	public void saveUser(HashMap<String,Object> userData);
	public UserVo getUserAccount(String userId);
	public String checkId(String userId);
	public void accountDeleteInfo(String userId);
	public void deleteUser(String userId);
	public void startUserIncome(HashMap<String,Object> userData);
}
