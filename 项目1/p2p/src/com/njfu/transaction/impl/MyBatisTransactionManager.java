package com.njfu.transaction.impl;

import org.apache.ibatis.session.SqlSession;

import com.njfu.exception.DataAccessException;
import com.njfu.transaction.TransactionManager;
import com.njfu.util.MyBatisUtil;

public class MyBatisTransactionManager implements TransactionManager {

	public void beginTransaction() {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
		} catch (Exception e) {
			throw new DataAccessException("数据访问失败", e);
		}
		
	}

	public void commit() {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
			session.commit();
		} catch (Exception e) {
			throw new DataAccessException("数据访问失败", e);
		} finally {
			MyBatisUtil.closeSession();
		}
	}

	public void rollback() {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
			session.rollback();
		} catch (Exception e) {
		} finally {
			MyBatisUtil.closeSession();
		}
	}

}
