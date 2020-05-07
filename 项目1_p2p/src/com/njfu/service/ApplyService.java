package com.njfu.service;

import java.util.List;

import com.njfu.entity.Apply;
import com.njfu.entity.CompanyReport;
import com.njfu.entity.CompanyReportDetail;
import com.njfu.form.ApplyForm;

public interface ApplyService {
	public List<Apply> findByApplyProductId(int applyProductId);
	
	public List<CompanyReport> findAllReport();

	//查询对应公司下的所有报表
	public List<CompanyReportDetail> findReportDetailByCompanyId(Integer id);
	
	//根据年份查询对应公司下的所有报表
	public List<CompanyReportDetail> findReportDetailByCompanyIdAndYear(Integer id, String year);

	public List<CompanyReport> findReportByYear(String year);

	public void addApply(ApplyForm applyForm) throws Exception;
}
