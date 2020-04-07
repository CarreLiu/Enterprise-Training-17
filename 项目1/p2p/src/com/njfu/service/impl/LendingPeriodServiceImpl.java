package com.njfu.service.impl;

import java.util.List;

import com.njfu.dao.LendingPeriodDao;
import com.njfu.entity.LendingPeriod;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.LendingPeriodForm;
import com.njfu.service.LendingPeriodService;

public class LendingPeriodServiceImpl implements LendingPeriodService {
	public List<LendingPeriod> findLendingPeriod() {
		LendingPeriodDao lendingPeriodDao = (LendingPeriodDao) ObjectFactory.getObject("lendingPeriodDao");
		List<LendingPeriod> list = lendingPeriodDao.selectLendingPeriod();
		
		return list;
	}

	public void addLendingPeriod(LendingPeriodForm lendingPeriodForm) throws Exception {
		LendingPeriodDao lendingPeriodDao = (LendingPeriodDao) ObjectFactory.getObject("lendingPeriodDao");
		lendingPeriodDao.insertLendingPeriod(lendingPeriodForm);
	}

	public void modifyLendingPeriod(LendingPeriodForm lendingPeriodForm) throws Exception {
		LendingPeriodDao lendingPeriodDao = (LendingPeriodDao) ObjectFactory.getObject("lendingPeriodDao");
		lendingPeriodDao.updateLendingPeriod(lendingPeriodForm);
	}

	public void modifyPeriodStatus(LendingPeriod lendingPeriod) throws Exception {
		LendingPeriodDao lendingPeriodDao = (LendingPeriodDao) ObjectFactory.getObject("lendingPeriodDao");
		lendingPeriodDao.updatePeriodStatus(lendingPeriod);
	}

	public List<LendingPeriod> findByPeriod(String period) {
		LendingPeriodDao lendingPeriodDao = (LendingPeriodDao) ObjectFactory.getObject("lendingPeriodDao");
		List<LendingPeriod> lendingPeriodList = lendingPeriodDao.selectByPeriod(period);
		
		return lendingPeriodList;
	}

	public List<LendingPeriod> findByIdAndPeriod(Integer lendingPeriodId, String period) {
		LendingPeriodDao lendingPeriodDao = (LendingPeriodDao) ObjectFactory.getObject("lendingPeriodDao");
		List<LendingPeriod> lendingPeriodList = lendingPeriodDao.selectByIdAndPeriod(lendingPeriodId, period);
		
		return lendingPeriodList;
	}
	
	
}
