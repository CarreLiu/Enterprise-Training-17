package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.LendingPeriod;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.ServiceException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.LendingPeriodForm;
import com.njfu.service.LendingPeriodService;
import com.njfu.transaction.TransactionManager;

public class LendingPeriodServiceProxy implements LendingPeriodService {

	public List<LendingPeriod> findLendingPeriod() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		LendingPeriodService lendingPeriodService=(LendingPeriodService) ObjectFactory.getObject("lendingPeriodService");
		List<LendingPeriod> list = null;
		try {
			tran.beginTransaction();
			list = lendingPeriodService.findLendingPeriod();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return list;
	}

	public void addLendingPeriod(LendingPeriodForm lendingPeriodForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		LendingPeriodService lendingPeriodService=(LendingPeriodService) ObjectFactory.getObject("lendingPeriodService");
		try {
			tran.beginTransaction();
			lendingPeriodService.addLendingPeriod(lendingPeriodForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public void modifyLendingPeriod(LendingPeriodForm lendingPeriodForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		LendingPeriodService lendingPeriodService=(LendingPeriodService) ObjectFactory.getObject("lendingPeriodService");
		try {
			tran.beginTransaction();
			lendingPeriodService.modifyLendingPeriod(lendingPeriodForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public void modifyPeriodStatus(LendingPeriod lendingPeriod) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		LendingPeriodService lendingPeriodService=(LendingPeriodService) ObjectFactory.getObject("lendingPeriodService");
		try {
			tran.beginTransaction();
			lendingPeriodService.modifyPeriodStatus(lendingPeriod);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public List<LendingPeriod> findByPeriod(String period) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		LendingPeriodService lendingPeriodService=(LendingPeriodService) ObjectFactory.getObject("lendingPeriodService");
		List<LendingPeriod> lendingPeriodList = null;
		try {
			tran.beginTransaction();
			lendingPeriodList = lendingPeriodService.findByPeriod(period);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return lendingPeriodList;
	}

	public List<LendingPeriod> findByIdAndPeriod(Integer lendingPeriodId, String period) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		LendingPeriodService lendingPeriodService=(LendingPeriodService) ObjectFactory.getObject("lendingPeriodService");
		List<LendingPeriod> lendingPeriodList = null;
		try {
			tran.beginTransaction();
			lendingPeriodList = lendingPeriodService.findByIdAndPeriod(lendingPeriodId, period);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return lendingPeriodList;
	}

}
