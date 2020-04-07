package com.njfu.service;

import java.util.List;

import com.njfu.entity.LendingPeriod;
import com.njfu.form.LendingPeriodForm;

public interface LendingPeriodService {
	//lendingPeriod
	public List<LendingPeriod> findLendingPeriod();
	
	public void addLendingPeriod(LendingPeriodForm lendingPeriodForm) throws Exception;

	public void modifyLendingPeriod(LendingPeriodForm lendingPeriodForm) throws Exception;

	public void modifyPeriodStatus(LendingPeriod lendingPeriod) throws Exception;

	public List<LendingPeriod> findByPeriod(String period);

	public List<LendingPeriod> findByIdAndPeriod(Integer lendingPeriodId, String period);
}
