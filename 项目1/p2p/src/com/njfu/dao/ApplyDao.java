package com.njfu.dao;

import java.util.List;

import com.njfu.entity.Apply;
import com.njfu.exception.DataAccessException;
import com.njfu.form.ApplyForm;

public interface ApplyDao {
	//根据产品id查询申请人
	public List<Apply> selectByApplyProductId(int applyProductId);

	//查询所有公司id
	public List<Integer> selectAllCompanyId();

	//根据公司id查询产品
	public List<Integer> selectAllProductIdByCompanyId(Integer id);

	public List<Integer> selectAllProductIdByCompanyIdAndYear(Integer id, String year);

	public List<Apply> selectByApplyProductIdAndYear(Integer productId, String year);

	public void insertApply(ApplyForm applyForm) throws DataAccessException;
}
