package com.oldbutcher.javafordiscuz.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.oldbutcher.javafordiscuz.qingtui.entity.Assosiation;

public class DBUtil {
	
	private static SqlSession getSqlSession() throws IOException {
		// 加载mybatis配置文件
		Reader reader = Resources.getResourceAsReader("dbconf.xml");
		// 构建SqlSession工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		// 创建SqlSession对象
		SqlSession sqlSession = factory.openSession();
		
		return sqlSession;
	}
	
	/**
	 * 查看用户号码是否已经关联
	 * @param mobile
	 * @return
	 * @throws IOException
	 */
	public static Assosiation getAssoByMobile(String mobile) throws IOException {
		SqlSession sqlSession = getSqlSession();
		
		Assosiation assosiation = sqlSession.selectOne("getByMobile", mobile);
		sqlSession.close();
		
		return assosiation;
	}
	
	/**
	 * 根据电话号码得到在discuz中的用户名
	 * @param mobile
	 * @throws IOException 
	 */
	public static String getUserName(String mobile) throws IOException {
		SqlSession sqlSession = getSqlSession();
		
		Integer id = sqlSession.selectOne("getIdByMobile", mobile);
		if (id == null) {
			sqlSession.close();
			return null;
		}
		
		String userName = sqlSession.selectOne("getUsernameById", id);
		
		sqlSession.close();
		
		return userName;
	} 
	
	/**
	 * 向关联表中创建关联关系
	 */
	public static void createAssosiation(String name, String mobile) throws IOException {
		SqlSession sqlSession = getSqlSession();
		
		// 用户name在discuz中的id
		int uid = sqlSession.selectOne("getIdByUserName", name);
		
		// 关联对象
		Assosiation assosiation = new Assosiation();
		assosiation.setId(uid);
		assosiation.setMobile(mobile);
		
		int result = sqlSession.insert("addAssosiation", assosiation);
		sqlSession.commit();
		sqlSession.close();
		if (result != 1) {
			System.out.println("添加关联失败: DBUtil.createAssosiation");
		}
	}
}
