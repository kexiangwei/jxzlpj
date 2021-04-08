package com.mycode.util;

import org.springframework.lang.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class StringUtils {

	/**
	 * 生成随机字符
	 * @param length 生成的字符位数
	 * @param isNumeric 是否仅使用数字生成随机字符
	 * @return
	 **/
	public static String guid(int length, boolean isNumeric) {
		String base = "abcdefghijklmnopqrstuvwxyz1234567890";
		if (isNumeric) {
			base = "1234567890";
		}
		Random random = new Random();
		StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			buffer.append(base.charAt(random.nextInt(base.length())));
		}
		//判断首位是不是0，若是重新生成一个
		String code = buffer.toString();
		if(code.matches("^0.*")){
			return guid(length,isNumeric);
		}
		return code;
	}

	public static String uuid(){
		return UUID.randomUUID().toString().replace("-","");
	}

	public static boolean isEmpty(@Nullable Object str) {
		return (str == null || "".equals(str));
	}

	public static boolean isNotEmpty(@Nullable Object str) {
		return !isEmpty(str);
	}
}
