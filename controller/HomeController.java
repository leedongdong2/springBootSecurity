package com.example.springBootTest.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBootTest.mapper.HomeMapper;
import com.example.springBootTest.page.Page;
import com.example.springBootTest.service.HomeService;
import com.example.springBootTest.vo.AccountVo;

@RestController
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	
	
	/**
	 * 
	 * @param mv
	 * @param page
	 * @param authentication
	 * @return mv
	 * 메인페이지 
	 * 로그인된 유저의 당일기록을 메인뷰로 보여준다
	 *
	 */
	@GetMapping("/main/home")										
	public ModelAndView home(ModelAndView mv,Page page,Authentication authentication) {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		page.setAccountDate(date.format(today));
		/**
		 * 스프링 시큐리티 로그인을 이용
		 * 로그인된 유저의 아이디 정보를 가져온다
		 **/
		page.setUserId(authentication.getName());
		List<AccountVo> accountList = homeService.searchHomeAccount(page);
		HashMap<String,String> totAccount = homeService.totalAccount(page);
		Integer recentryBalance = homeService.recentryBalance(page.getUserId());
		mv.addObject("accountList", accountList);
		mv.addObject("totAccount",totAccount);
		mv.addObject("recentryBalance", recentryBalance);
		mv.addObject("accountPage", page);
		mv.setViewName("main/home");
		return mv;
	}
	
	/**
	 * 
	 * @param mv
	 * @param page
	 * @param authentication
	 * @return mv
	 * 
	 * 조회기능
	 * 조회할 데이터를 받아 조회 후 메인페이지에 뿌려준다
	 * 
	 */
	@GetMapping("/main/home/1")
	public ModelAndView findHomeAccount(ModelAndView mv,Page page,Authentication authentication) {
		mv.addObject("findAccountStartDate",page.getAccountStartDate());
		mv.addObject("findAccountEndDate",page.getAccountEndDate());
		page.setUserId(authentication.getName());
		List<AccountVo> findAccountList = homeService.findHomeAccount(page);
		HashMap<String,String> findTotAccount = homeService.findTotalAccount(page);
		Integer recentryBalance = homeService.recentryBalance(page.getUserId());
		mv.addObject("accountList", findAccountList);
		mv.addObject("totAccount",findTotAccount);
		mv.addObject("recentryBalance", recentryBalance);
		mv.addObject("accountPage", page);
		mv.setViewName("main/home");
		return mv;
	}
	
	/**
	 * 
	 * 입력페이지
	 * 가계부를 입력한다
	 * 
	 */
	@GetMapping("/main/produce-page")
	public ModelAndView producePage(ModelAndView mv) {
		mv.setViewName("main/producePage");
		return mv;
	}
	
	/**
	 * 
	 * @param mv
	 * @param accountVo
	 * @param authentication
	 * @return 
	 * 
	 * 입력기능
	 * 입력된 가계 데이터를 받아 db에 저장한다
	 * 저장 후 메인페이지 url을 타고 
	 * 메인페이지로 넘어간다
	 * 
	 */
	@PostMapping("/main/produce-page/1")
	public ResponseEntity<?> insertAccount(ModelAndView mv,AccountVo accountVo,Authentication authentication) {
		accountVo.setUserId(authentication.getName());
		Integer balance = homeService.calculateBalance(accountVo);
		accountVo.setAccountBalance(balance);
		homeService.insertAccout(accountVo);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/main/home"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	/**
	 * 
	 * @param mv
	 * @param serial
	 * @return
	 * 
	 * 수정페이지
	 * 수정할 수 있는 페이지
	 */
	@GetMapping("/main/modify-page")
	public ModelAndView modifyPage(ModelAndView mv,@RequestParam("serial")String serial) {
		AccountVo accountVo = homeService.findDetailAccount(serial);
		mv.addObject("accountVo", accountVo);
		mv.setViewName("main/modifyPage");
		return mv;
	}
	
	/**
	 * 
	 * @param accountVo
	 * @return
	 * 
	 * 수정기능
	 * 입력된 수정 데이터를 받아 db를 수정한다
	 * 
	 */
	@PutMapping("/main/modify-page/1")
	public ResponseEntity<?> modifyDetail(AccountVo accountVo) {
		/**남은 잔액 계산 서비스 
		 * 수정 시 남은 잔액이 변경 되기에 수정된 수입 지출의 차액을 계산하여
		 * 남은 잔액에 반영시켜줌**/
		int changedValue = homeService.calculateChangedValue(accountVo);
		accountVo.setChangedValue(changedValue);
		accountVo.setAccountBalance(accountVo.getAccountBalance() + changedValue);
		
		homeService.modifyAccountDetail(accountVo);
		homeService.modifyAccountBalance(accountVo);
       
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/main/home"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	/**
	 * 
	 * @param accountVo
	 * @return 
	 * 삭제 기능
	 * 입력한 가계기록을 삭제
	 */
	@DeleteMapping("/main/modify-page/2")
	public ResponseEntity<?> deleteAccountDetail(AccountVo accountVo) {
		/**남은 잔액 계산 서비스
		 * 삭제 시 남은 잔액이 변경 되기에 삭제된 수입과 지출을 남은 잔액에 반영시켜줌
		 */
		int deleteValue = homeService.calculateDeleteValue(accountVo);
		accountVo.setChangedValue(deleteValue);
		
		homeService.modifyAccountBalance(accountVo);
		homeService.deleteAccountDetail(accountVo);	

		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/main/home"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	
	
	/**
	 * 
	 * @param mv
	 * @return
	 * 로그인 실패시 화면전환 페이지
	 */
	@GetMapping("/main/assetDenied")
	public ModelAndView assetDenied(ModelAndView mv) {
		mv.setViewName("main/assetDenied");
		return mv;
	}
	
	/**
	 * 
	 * @param mv
	 * @return
	 * 관리자 페이지
	 */
	@GetMapping("/admin/admin")
	public ModelAndView admin(ModelAndView mv,Page page) {
		page.setListSize(50);
		List<HashMap<String,String>> users = homeService.findUser(page);
		mv.addObject("users",users);
		mv.addObject("usersPage", page);
		mv.setViewName("admin/admin");
		return mv;
	}

	
	
}
