package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.Company;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.ServiceException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.CompanyForm;
import com.njfu.service.CompanyService;
import com.njfu.transaction.TransactionManager;

public class CompanyServiceProxy implements CompanyService {

	public List<Company> findCompany() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		CompanyService	companyService=(CompanyService) ObjectFactory.getObject("companyService");
		List<Company> list = null;
		try {
			tran.beginTransaction();
			list = companyService.findCompany();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return list;
	}
	
	public void addCompany(CompanyForm companyForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		CompanyService	companyService=(CompanyService) ObjectFactory.getObject("companyService");
		try {
			tran.beginTransaction();
			companyService.addCompany(companyForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public Company findByCompanyId(Integer companyId) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		CompanyService	companyService=(CompanyService) ObjectFactory.getObject("companyService");
		Company company = null;
		try {
			tran.beginTransaction();
			company = companyService.findByCompanyId(companyId);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return company;
	}

	public void modifyCompany(CompanyForm companyForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		CompanyService	companyService=(CompanyService) ObjectFactory.getObject("companyService");
		try {
			tran.beginTransaction();
			companyService.modifyCompany(companyForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}
	
	public void removeCompany(Integer companyId) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		CompanyService	companyService=(CompanyService) ObjectFactory.getObject("companyService");
		try {
			tran.beginTransaction();
			companyService.removeCompany(companyId);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException("公司删除异常");
		}
	}

	public List<Company> findByCompanyName(String companyName) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		CompanyService	companyService=(CompanyService) ObjectFactory.getObject("companyService");
		List<Company> companyList = null;
		try {
			tran.beginTransaction();
			companyList = companyService.findByCompanyName(companyName);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return companyList;
	}

	public List<Company> findByCompanyIdAndName(Integer companyId, String companyName) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		CompanyService	companyService=(CompanyService) ObjectFactory.getObject("companyService");
		List<Company> companyList = null;
		try {
			tran.beginTransaction();
			companyList = companyService.findByCompanyIdAndName(companyId, companyName);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return companyList;
	}

}
