package com.example.springBootTest.configuration;

import java.util.HashMap;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcUtils;



@Configuration
public class ParamMap extends HashMap<Object,Object> {
	
	//mybis 이용시 map타입을 반환할떄 카멜케이스 자동전환 기능
	public Object put(Object key,Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String)key.toString().toLowerCase()), value);
	}

}
