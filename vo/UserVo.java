package com.example.springBootTest.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

					//사용자 정보
public class UserVo implements UserDetails {
	
	private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userAuth;
    private String appendDate;
	private String updateDate;
	private int startIncome;


	//사용자 권한 리스트
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.userAuth));
	}
	
	//유저 패스워드
	@Override
	public String getPassword() {
		return this.userPw;
	}

	//유저 네임 아이디로 판단
	@Override
	public String getUsername() {
		return this.userId;
	}

	// 계정이 만료 되지 않았는가?
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 잠기지 않았는가?
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 패스워드가 만료되지 않았는가?
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 활성화 되었는가?
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	 public int getUserNo() {
			return userNo;
		}

		public void setUserNo(int userNo) {
			this.userNo = userNo;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserPw() {
			return userPw;
		}

		public void setUserPw(String userPw) {
			this.userPw = userPw;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserAuth() {
			return userAuth;
		}

		public void setUserAuth(String userAuth) {
			this.userAuth = userAuth;
		}

		public String getAppendDate() {
			return appendDate;
		}

		public void setAppendDate(String appendDate) {
			this.appendDate = appendDate;
		}

		public String getUpdateDate() {
			return updateDate;
		}

		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}

		public int getStartIncome() {
			return startIncome;
		}

		public void setStartIncome(int startIncome) {
			this.startIncome = startIncome;
		}
}
