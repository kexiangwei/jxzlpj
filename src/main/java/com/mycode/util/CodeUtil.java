package com.mycode.util;

import java.util.Random;

public class CodeUtil {

	/**
	 * 生成随机字符
	 * @param length 生成的字符位数
	 * @param isNumeric 是否仅使用数字生成随机字符
	 * @return
	 **/
	public static String randomChar(int length, boolean isNumeric) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		if (isNumeric) {
			base = "1234567890";
		}
		Random random = new Random();
		StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			buffer.append(base.charAt(random.nextInt(base.length())));
		}
		return buffer.toString();
	}
}
