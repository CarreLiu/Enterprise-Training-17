package com.njfu.service.impl;

import java.util.List;

import com.njfu.dao.CompanyDao;
import com.njfu.entity.Company;
import com.njfu.exception.CompanyInUseException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.CompanyForm;
import com.njfu.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {
	public List<Company> findCompany() {
		CompanyDao companyDao = (CompanyDao) ObjectFactory.getObject("companyDao");
		List<Company> list = companyDao.selectCompany();
		for (Company company : list) {
			String companyDetail = company.getCompanyDetail();
			if (companyDetail != null && companyDetail.length() > 20) {
				company.setCompanyDetail(companyDetail.substring(0, 20) + "...");
			}
		}
		
		return list;
	}
	
	public void addCompany(CompanyForm companyForm) throws Exception {
		CompanyDao companyDao = (CompanyDao) ObjectFactory.getObject("companyDao");
		companyDao.insertCompany(companyForm);
	}

	public Company findByCompanyId(Integer companyId) throws Exception {
		CompanyDao companyDao = (CompanyDao) ObjectFactory.getObject("companyDao");
		Company company = companyDao.selectByCompanyId(companyId);
		
		return company;
	}

	public void modifyCompany(CompanyForm companyForm) throws Exception {
		CompanyDao companyDao = (CompanyDao) ObjectFactory.getObject("companyDao");
		companyDao.updateCompany(companyForm);
	}

	public void removeCompany(Integer companyId) throws Exception {
		CompanyDao companyDao = (CompanyDao) ObjectFactory.getObject("companyDao");
		try {
			companyDao.delCompany(companyId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CompanyInUseException();
		}
	}

	public List<Company> findByCompanyName(String companyName) {
		CompanyDao companyDao = (CompanyDao) ObjectFactory.getObject("companyDao");
		List<Company> companyList = companyDao.selectByCompanyName(companyName);
		
		return companyList;
	}

	public List<Company> findByCompanyIdAndName(Integer companyId, String companyName) {
		CompanyDao companyDao = (CompanyDao) ObjectFactory.getObject("companyDao");
		List<Company> companyList = companyDao.selectByCompanyIdAndName(companyId, companyName);
		
		return companyList;
	}
	
}
