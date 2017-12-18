package com.oldbutcher.javafordiscuz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oldbutcher.javafordiscuz.qingtui.entity.UserInfo;

public class AppUtil {
	
	/**
	 * 获取access_token
	 */
	public static String getAccessToken() throws ServletException, IOException {
		
		String appId = ConfigUtil.getProperty("sys.properties", "appId");
		String appSecret = ConfigUtil.getProperty("sys.properties", "appSecret");
		
		URL url = new URL("https://open.qingtui.im/v1/token?grant_type=client_credential"
				+ "&appid=" + appId
				+ "&secret=" + appSecret);
		
		String json = getJson(url);
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		return jsonObject.getString("access_token");
	}
	
	/**
	 * 根据qtCode获取openId
	 */
	public static String getOpenId(String qtCode) throws ServletException, IOException {
		String appId = ConfigUtil.getProperty("sys.properties", "appId");
		String appSecret = ConfigUtil.getProperty("sys.properties", "appSecret");
		
		URL url = new URL("https://open.qingtui.im/v1/oauth2/userinfo"
				+ "?appid=" + appId
				+ "&qt_code=" + qtCode
				+ "&secret=" + appSecret);
		
		String json = getJson(url);
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		return jsonObject.getString("openid");
	}
	
	/**
	 * 根据qtCode获取用户详细信息
	 */
	public static UserInfo getUserInfo(String qtCode) throws ServletException, IOException {
		URL url = new URL("https://open.qingtui.im/team/member/openid/detail"
				+ "?access_token=" + getAccessToken()
				+ "&open_id=" + getOpenId(qtCode));
		
		String json = getJson(url);
		UserInfo userInfo = JSON.parseObject(json, UserInfo.class);
		
		return userInfo;
	}
	
	/**
	 * 向指定url地址发起请求并返回响应的json数据
	 * @param url 请求地址
	 * @return 返回的json数据
	 */
	private static String getJson(URL url) throws ServletException, IOException {
		// 建立连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");		
		
		InputStream in = connection.getInputStream();
		BufferedReader reader =
				new BufferedReader(new InputStreamReader(in, "UTF-8"));
		
		String t = null;
		StringBuilder builder = new StringBuilder();
		while ((t = reader.readLine()) != null) {
			builder.append(t);
		}
		
		String json = builder.toString();
		
		return json;
	}
}
