package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class Common {

	//StringをparceIntできるか
	public static boolean isParceInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//StringをparceDoubleできるか
	public static boolean isParceDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//正規表現
	//_ または 0~9 またはa~Zの1回以上の繰り返し
	public static final String REGULAR_EXPRESSION = "^[_0-9a-zA-Z]+$";

	public static boolean isMatchRE(String str) {
		return str.matches(REGULAR_EXPRESSION);
	}

}
