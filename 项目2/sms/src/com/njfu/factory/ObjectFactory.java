package com.njfu.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.njfu.exception.DataAccessException;
import com.njfu.util.MyBatisUtil;

public class ObjectFactory {
	
	private static Map<String,Object> objects=new HashMap<String, Object>();
	
	static {
		BufferedReader br=null;
		try {
			br=new BufferedReader(
					new InputStreamReader(
							ObjectFactory.class
										 .getClassLoader()
										 .getResourceAsStream("objects-mybatis.txt")));
			
			String s=null;
			while((s=br.readLine())!=null){
				//如果该行时空白行或者#开头的行，则这行跳过，不做处理
				if(s.trim().equals("")||s.startsWith("#"))
				{
					continue;
				}
				String[] entry=s.split("=");
				if("mybatisDao".equals(entry[0])){
					
					String daoPackage=entry[1];
					
					daoPackage=daoPackage.replace(".", "/");
					
					
					URL url=ObjectFactory.class
										 .getClassLoader()
										 .getResource(daoPackage);
					
//					String urlStr=URLDecoder.decode(url.toString(),"utf-8");
					
					File dir=new File(url.toURI());
					
					String[] fileNames=dir.list();
					
					for (String fileName : fileNames) {
						if(fileName.endsWith(".class")){
							String daoClassName=entry[1]+"."+fileName.substring(0,fileName.lastIndexOf(".class"));
							Class daoClass=Class.forName(daoClassName);
							
							String key=daoClass.getSimpleName();
							
							String firstStr=key.charAt(0)+"";
							
							key=firstStr.toLowerCase()+key.substring(1);
							
							objects.put(key, daoClass);
						}
					}
					
					continue;
				}
				objects.put(entry[0], Class.forName(entry[1]).newInstance());
				//System.out.println(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("ObjectFactory初始化错误"+e);
		} finally {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Object getObject(String key){
		Object obj=objects.get(key);
		if(obj instanceof Class){
			SqlSession session=MyBatisUtil.getSession();
			final Object dao=session.getMapper((Class)obj);
			
			Object daoProxy=Proxy.newProxyInstance(dao.getClass().getClassLoader(), 
												   new Class[]{(Class)obj},
												   new InvocationHandler(){
				
														public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
															Object result=null;
															try {
																result=method.invoke(dao, args);
															} catch (Exception e) {
																throw new DataAccessException("数据访问失败",e);
															}
															return result;
														}
								
												   });
			
			
			return daoProxy;
		}
		return obj;
	}
}
