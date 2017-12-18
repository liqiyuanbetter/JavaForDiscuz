package test;

import java.util.LinkedList;

import com.fivestars.interfaces.bbs.client.Client;
import com.fivestars.interfaces.bbs.util.XMLHelper;

public class MyTest {
	public static String login(String userName, String pwd) {
		Client e = new Client();
		String result = e.uc_user_login(userName, pwd);
		String $ucsynlogin = "";
		LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		if (rs.size() > 0) {
			int $uid = Integer.parseInt(rs.get(0));
			String $username = rs.get(1);
			String $password = rs.get(2);
			String $email = rs.get(3);
			if ($uid > 0) {
				$ucsynlogin = e.uc_user_synlogin($uid);
			} else if ($uid == -1) {
				System.out.println("用户不存在,或者被删除");
			} else if ($uid == -2) {
				System.out.println("密码错");
			} else {
				System.out.println("未定义");
			}
		} else {
			System.out.println("Login failed");
			System.out.println(result);
		}
		return $ucsynlogin;
	}
	
	public static void main(String[] args) {
		String result = login("cccc", "ccccc");
		System.out.println(result);
	}
	
	public static void deleteUserById(int id) {
		
	}
}
