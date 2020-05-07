package com.njfu.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.njfu.exception.DataAccessException;

public class MyBatisUtil {
	private static SqlSessionFactory sf;
	private static ThreadLocal<SqlSession> threadLocal;
	
	static{
		try {
			threadLocal=new ThreadLocal<SqlSession>();
			sf=new SqlSessionFactoryBuilder()
				.build(MyBatisUtil.class
								  .getClassLoader()
								  .getResourceAsStream("mybatis-config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("MyBaitsUtil初始化错误"+e);
		}
	}
	
	public static SqlSession getSession(){
		SqlSession session=threadLocal.get();
		try {
			if(session==null){
				session=sf.openSession(false);
				threadLocal.set(session);
			}
		} catch (Exception e) {
			throw new DataAccessException("数据访问失败",e);
		}
		return session;
	}
	
	public static void closeSession(){
		SqlSession session=threadLocal.get();
		if(session!=null){
			session.close();
			threadLocal.remove();
		}
	}
}






