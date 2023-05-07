package com.example.springBootTest.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springBootTest.configuration.ParamMap;
import com.example.springBootTest.mapper.UserMapper;
import com.example.springBootTest.vo.UserVo;
@Service								//유저 정보 확인
public class UserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	/**
	 * 
	 * 
	 * @param paramMap
	 * 패스워드를 암호화 후 
	 * 입력된 정보로 회원가입을 한다
	 * 
	 */
	@Transactional
	public void JoinUser(HashMap<String,Object> paramMap) {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 paramMap.put("userPw", passwordEncoder.encode((String)paramMap.get("userPw")));
		 userMapper.saveUser(paramMap);
	}
    
	public void startUserIncome(HashMap<String,Object> paramMap) {
		userMapper.startUserIncome(paramMap);
	}
	
	
	/**
	 * db에서 유저 정보를 찾은후 입력된 유저정보와 일치하는지 보고 로그인 성공 여부를 결정
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVo userVo = userMapper.getUserAccount(userId);
		if(userVo == null) {
			throw new UsernameNotFoundException("User not authorized.");
		}
		return User.builder()
					.username(userVo.getUserId())
					.password(userVo.getUserPw())
					.roles(userVo.getUserAuth())
					.build();
}
	/**
	 * 
	 * @param userId
	 * @return
	 * 아이디 중복체크
	 */
	public String checkId(String userId) {
		String id = userMapper.checkId(userId);
		return id;
	}
	
	/**
	 * 
	 * @param userId
	 * 아이디 삭제시 아이디에 해당하는 가계정보를 삭제한다
	 */
	@Transactional
	public void accountDeleteInfo(String userId) {
		userMapper.accountDeleteInfo(userId);
	}
	
	/**
	 * 
	 * @param userId
	 * 아이디 삭제
	 */
	@Transactional
	public void deleteUser(String userId) {
		userMapper.deleteUser(userId);
	}
}
