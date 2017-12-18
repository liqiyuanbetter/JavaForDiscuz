package com.oldbutcher.javafordiscuz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	/**
	 * 得到配置信息
	 * @param fileName 配置文件名
	 * @param key 属性名
	 * @return
	 */
	public static String getProperty(String fileName, String key) {
		InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(fileName);
		Properties prop = new Properties();
		
		try {
			prop.load(in);
			String value = prop.getProperty(key);
			in.close();
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
