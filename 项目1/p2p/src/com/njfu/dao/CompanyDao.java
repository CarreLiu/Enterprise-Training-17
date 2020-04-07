package com.njfu.dao;

import java.util.List;

import com.njfu.entity.Company;
import com.njfu.exception.DataAccessException;
import com.njfu.form.CompanyForm;

public interface CompanyDao {
	public List<Company> selectCompany();
	
	public void insertCompany(CompanyForm companyForm) throws DataAccessException;

	public Company selectByCompanyId(Integer companyId);

	public void updateCompany(CompanyForm companyForm) throws DataAccessException;

	public void delCompany(Integer companyId) throws DataAccessException;

	public List<Company> selectByCompanyName(String companyName);

	public List<Company> selectByCompanyIdAndName(Integer companyId, String companyName);
}
