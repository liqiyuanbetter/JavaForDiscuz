package com.oldbutcher.javafordiscuz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oldbutcher.javafordiscuz.qingtui.entity.UserInfo;
import com.oldbutcher.javafordiscuz.utils.AppUtil;
import com.oldbutcher.javafordiscuz.utils.DBUtil;
import com.oldbutcher.javafordiscuz.utils.DisUtil;

@WebServlet(urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("servlet visited...");
		
		// qt_code
		String qtCode = request.getParameter("qt_code");
		
		// 当前轻推用户信息
		UserInfo user = AppUtil.getUserInfo(qtCode);
		String mobile = user.getMobile();
		String name = user.getName();
		System.out.println("轻推用户名:" + name);
		
		// 根据电话号码得到discuz中的用户名
		String disName = DBUtil.getUserName(mobile);
		// 用户名为空说明未注册,则先注册再登录
		if (disName == null) {
			// 向discuz中注册用户
			disName = DisUtil.addUser(name);
			// 和discuz用户表创建关联关系
			DBUtil.createAssosiation(name, mobile);
			System.out.println("新用户: " + disName + "注册成功!");
		}
		
		// 登录到discuz
		doLogin(disName, response);
	}
	
	/**
	 * 执行登录操作
	 */
	private void doLogin(String disName, HttpServletResponse response) throws IOException {
		String loginUrl = DisUtil.userLogin(disName, "123456");
		System.out.println("用户: " + disName + "正在登录");
		response.sendRedirect(loginUrl + "&tag=index");
	}
}
