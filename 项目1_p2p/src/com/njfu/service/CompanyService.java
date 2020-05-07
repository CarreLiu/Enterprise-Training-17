package com.njfu.service;

import java.util.List;

import com.njfu.entity.Company;
import com.njfu.form.CompanyForm;

public interface CompanyService {
	//company
	public List<Company> findCompany();
	
	public void addCompany(CompanyForm companyForm) throws Exception;

	public Company findByCompanyId(Integer companyId) throws Exception;

	public void modifyCompany(CompanyForm companyForm) throws Exception;

	public void removeCompany(Integer companyId) throws Exception;

	public List<Company> findByCompanyName(String companyName);

	public List<Company> findByCompanyIdAndName(Integer companyId, String companyName);
}
