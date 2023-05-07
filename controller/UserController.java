package com.example.springBootTest.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBootTest.configuration.ParamMap;
import com.example.springBootTest.service.HomeService;
import com.example.springBootTest.service.UserService;
import com.example.springBootTest.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
public class UserController {
	
	@Autowired
    UserService userService;
	
	/**
	 * method가 get일시 회원가입 페이지를 보여준다 
	 * 
	 * **/
	@GetMapping("/user/signUp")
	public ModelAndView signUpForm(ModelAndView mv) {
		mv.setViewName("user/signUp");
		return mv;
	}
	
	/**
	 * 
	 * method가 post 일시 입력된 정보로 회원가입을 한다
	 * */
	@PostMapping("/user/signUp")
	public ModelAndView signUp(@RequestParam HashMap<String,Object> paramMap,ModelAndView mv) {
		userService.JoinUser(paramMap);
		userService.startUserIncome(paramMap);
		mv.setViewName("/index");
		return mv;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * 회원가입시 중복아이디를 체크
	 * 
	 */
	@GetMapping("/user/signUp/1")
	public Map<String,String> checkId(@RequestParam("id")String id){
		Map<String,String> checkMap = new HashMap<String,String>();
		String checkResult = userService.checkId(id);
		checkMap.put("checkResult", checkResult);
		return checkMap;
	}
	
	/**
	 * 
	 * 
	 * @param id
	 * @param request
	 * @return
	 * 회원삭제
	 * 삭제시 세션 삭제후 로그인페이지로 넘어간다
	 */
	@DeleteMapping("/user/signUp/2")
	public ResponseEntity<?> deleteId(@RequestParam("id")String id,HttpServletRequest request){
		userService.accountDeleteInfo(id);
		userService.deleteUser(id);
		HttpSession session = request.getSession();
		session.invalidate();
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	
	
}
