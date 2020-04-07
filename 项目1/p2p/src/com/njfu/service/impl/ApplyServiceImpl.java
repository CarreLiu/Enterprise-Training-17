package com.njfu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.njfu.dao.ApplyDao;
import com.njfu.dao.ProductDao;
import com.njfu.entity.Apply;
import com.njfu.entity.CompanyReport;
import com.njfu.entity.CompanyReportDetail;
import com.njfu.entity.Product;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ApplyForm;
import com.njfu.service.ApplyService;

public class ApplyServiceImpl implements ApplyService {

	public List<Apply> findByApplyProductId(int applyProductId) {
		ApplyDao applyDao = (ApplyDao) ObjectFactory.getObject("applyDao");
		List<Apply> list = applyDao.selectByApplyProductId(applyProductId);
		
		return list;
	}

	public List<CompanyReport> findAllReport() {
		//获取公司id列表
		
		//初始化CompanyReport
		
		/**
		 * 具体如何初始化
		 * 1:遍历公司id
		 * 2:根据公司id获取所有产品id
		 * 3:遍历这些产品
		 * 4:根据id获取对应产品
		 * 5:根据产品获取申请列表
		 * 6:初始化CompanyReportDetail
		 * 7:将detail存入集合
		 * 8:初始化CompanyReport
		 * 9:将CompanyReport存入集合
		 * 10:返回该集合
		 */
		
		ApplyDao applyDao = (ApplyDao) ObjectFactory.getObject("applyDao");
		ProductDao proDao = (ProductDao) ObjectFactory.getObject("productDao");
		List<CompanyReport> companyReports = new ArrayList<CompanyReport>();
		
		List<Integer> companyIdList = applyDao.selectAllCompanyId();
		
		for (Integer id : companyIdList) {
			//定义detail集合
			List<CompanyReportDetail> reportList = new ArrayList<CompanyReportDetail>();
			List<Integer> productIdList = applyDao.selectAllProductIdByCompanyId(id);
			for (Integer productId : productIdList) {
				Product product = proDao.selectByProductId(productId);
				List<Apply> applyList = applyDao.selectByApplyProductId(productId);
				CompanyReportDetail reportDetail = new CompanyReportDetail(product, applyList);
				reportList.add(reportDetail);
			}
			CompanyReport companyReport = new CompanyReport(reportList);
			if (productIdList != null && !productIdList.isEmpty()) {				
				companyReports.add(companyReport);
			}
		}
		
		return companyReports;
	}

	public List<CompanyReportDetail> findReportDetailByCompanyId(Integer id) {
		List<CompanyReportDetail> reportDetails = new ArrayList<CompanyReportDetail>();
		ApplyDao applyDao = (ApplyDao) ObjectFactory.getObject("applyDao");
		ProductDao proDao = (ProductDao) ObjectFactory.getObject("productDao");
		//获取该公司下所有的产品id
		List<Integer> productIdList = applyDao.selectAllProductIdByCompanyId(id);
		for (Integer productId : productIdList) {
			//根据产品id获取该产品
			Product product = proDao.selectByProductId(productId.intValue());
			//根据产品id获取所有申请者信息
			List<Apply> applyList = applyDao.selectByApplyProductId(productId);
			CompanyReportDetail reportDetail = new CompanyReportDetail(product, applyList);
			reportDetails.add(reportDetail);
		}
		
		return reportDetails;
	}

	public List<CompanyReportDetail> findReportDetailByCompanyIdAndYear(Integer id, String year) {
		List<CompanyReportDetail> reportDetails = new ArrayList<CompanyReportDetail>();
		ApplyDao applyDao = (ApplyDao) ObjectFactory.getObject("applyDao");
		ProductDao proDao = (ProductDao) ObjectFactory.getObject("productDao");
		//获取该公司下所有的产品id
		List<Integer> productIdList = applyDao.selectAllProductIdByCompanyIdAndYear(id, year);
		for (Integer productId : productIdList) {
			//根据产品id获取该产品
			Product product = proDao.selectByProductId(productId.intValue());
			//根据产品id获取所有申请者信息
			List<Apply> applyList = applyDao.selectByApplyProductIdAndYear(productId, year);
			CompanyReportDetail reportDetail = new CompanyReportDetail(product, applyList);
			reportDetails.add(reportDetail);
		}
		
		return reportDetails;
	}

	public List<CompanyReport> findReportByYear(String year) {
		ApplyDao applyDao = (ApplyDao) ObjectFactory.getObject("applyDao");
		ProductDao proDao = (ProductDao) ObjectFactory.getObject("productDao");
		List<CompanyReport> companyReports = new ArrayList<CompanyReport>();
		
		List<Integer> companyIdList = applyDao.selectAllCompanyId();
		
		for (Integer id : companyIdList) {
			//定义detail集合
			List<CompanyReportDetail> reportList = new ArrayList<CompanyReportDetail>();
			List<Integer> productIdList = applyDao.selectAllProductIdByCompanyIdAndYear(id, year);
			for (Integer productId : productIdList) {
				Product product = proDao.selectByProductId(productId);
				List<Apply> applyList = applyDao.selectByApplyProductId(productId);
				CompanyReportDetail reportDetail = new CompanyReportDetail(product, applyList);
				reportList.add(reportDetail);
			}
			CompanyReport companyReport = new CompanyReport(reportList);
			if (productIdList != null && !productIdList.isEmpty()) {				
				companyReports.add(companyReport);
			}
		}
		
		return companyReports;
	}

	public void addApply(ApplyForm applyForm) throws Exception {
		ApplyDao applyDao = (ApplyDao) ObjectFactory.getObject("applyDao");
		applyForm.setApplyDate(new Date());
		applyDao.insertApply(applyForm);
	}
	
}
