package com.oldbutcher.javafordiscuz.utils;

import java.util.LinkedList;

import com.fivestars.interfaces.bbs.client.Client;
import com.fivestars.interfaces.bbs.util.XMLHelper;

public class DisUtil {
	
	/**
	 * 返回用户登录所需要的链接
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String userLogin(String userName, String password) {
		Client e = new Client();
		String result = e.uc_user_login(userName, password);
		
		LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		if(rs.size()>0){
			int uid = Integer.parseInt(rs.get(0));
			if (uid > 0) {
				String ucsynlogin = e.uc_user_synlogin(uid);
				
				ucsynlogin = ucsynlogin.substring(ucsynlogin.indexOf("src=") + 5,
						ucsynlogin.indexOf("reload") - 2);
				
				return ucsynlogin;
			}
		}
		
		return null;
	}
	
	/**
	 * 若是新用户，则自动为其在discuz中注册
	 * 用户名轻推用户名, 密码为123456
	 */
	public static String addUser(String userName) {
		Client uc = new Client();
		String result = uc.uc_user_register(userName, "123456", "test@qingtui.im");
		int uid = Integer.parseInt(result);
		
		int rep = 0;
		while (uid <= 0) {
			if (uid == -3) {
				rep++;
				uid = Integer.parseInt(uc.uc_user_register(userName + rep, 
											"123456" , "test@qingtui.im"));
			} else {
				System.out.println("注册失败，未知错误!");
			}
		}
		System.out.println("注册成功, 用户id：" + uid);
		return rep >= 1 ? userName + rep : userName;
	}
}