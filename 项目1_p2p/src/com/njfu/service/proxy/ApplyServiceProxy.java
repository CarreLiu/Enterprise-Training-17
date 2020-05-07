package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.Apply;
import com.njfu.entity.CompanyReport;
import com.njfu.entity.CompanyReportDetail;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.ServiceException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ApplyForm;
import com.njfu.service.ApplyService;
import com.njfu.transaction.TransactionManager;

public class ApplyServiceProxy implements ApplyService {
	public List<Apply> findByApplyProductId(int applyProductId) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ApplyService applyService=(ApplyService) ObjectFactory.getObject("applyService");
		List<Apply> list = null;
		try {
			tran.beginTransaction();
			list = applyService.findByApplyProductId(applyProductId);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return list;
	}

	public List<CompanyReport> findAllReport() {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		ApplyService applyService = (ApplyService) ObjectFactory.getObject("applyService");
		List<CompanyReport> reportList = null;
		try {
			tran.beginTransaction();
			reportList = applyService.findAllReport();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		
		return reportList;
	}

	public List<CompanyReportDetail> findReportDetailByCompanyId(Integer id) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		ApplyService applyService = (ApplyService) ObjectFactory.getObject("applyService");
		List<CompanyReportDetail> reportDetails = null;
		try {
			tran.beginTransaction();
			reportDetails = applyService.findReportDetailByCompanyId(id);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		
		return reportDetails;
	}

	public List<CompanyReportDetail> findReportDetailByCompanyIdAndYear(Integer id, String year) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		ApplyService applyService = (ApplyService) ObjectFactory.getObject("applyService");
		List<CompanyReportDetail> reportDetails = null;
		try {
			tran.beginTransaction();
			reportDetails = applyService.findReportDetailByCompanyIdAndYear(id, year);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		
		return reportDetails;
	}

	public List<CompanyReport> findReportByYear(String year) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		ApplyService applyService = (ApplyService) ObjectFactory.getObject("applyService");
		List<CompanyReport> reportList = null;
		try {
			tran.beginTransaction();
			reportList = applyService.findReportByYear(year);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		
		return reportList;
	}

	public void addApply(ApplyForm applyForm) throws Exception {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		ApplyService applyService = (ApplyService) ObjectFactory.getObject("applyService");
		try {
			tran.beginTransaction();
			applyService.addApply(applyForm);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
}
