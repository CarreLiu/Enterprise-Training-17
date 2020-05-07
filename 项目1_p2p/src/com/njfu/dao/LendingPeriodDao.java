package com.njfu.dao;

import java.util.List;

import com.njfu.entity.LendingPeriod;
import com.njfu.exception.DataAccessException;
import com.njfu.form.LendingPeriodForm;

public interface LendingPeriodDao {
	public List<LendingPeriod> selectLendingPeriod();
	
	public void insertLendingPeriod(LendingPeriodForm lendingPeriodForm) throws DataAccessException;

	public void updateLendingPeriod(LendingPeriodForm lendingPeriodForm) throws DataAccessException;

	public void updatePeriodStatus(LendingPeriod lendingPeriod) throws DataAccessException;

	public List<LendingPeriod> selectByPeriod(String period);

	public List<LendingPeriod> selectByIdAndPeriod(Integer lendingPeriodId, String period);
}
