package com.njfu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.constant.Constant;
import com.njfu.entity.Company;
import com.njfu.entity.News;
import com.njfu.exception.CompanyInUseException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.CompanyForm;
import com.njfu.service.CompanyService;
import com.njfu.service.NewsService;

public class CompanyAction extends MappingDispatchAction {
	public void companyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<Company> list = companyProxy.findCompany();
		//使用fastjson
		PageInfo<Company> pageInfo = new PageInfo<Company>(list);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
	
	public void addCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		//调用proxy
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		String companyName = request.getParameter("companyName");
		List<Company> companyList = companyProxy.findByCompanyName(companyName);
		PrintWriter out = response.getWriter();
		if (companyList != null && !companyList.isEmpty()) {
			out.print("该企业名称已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward companyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		CompanyForm companyForm = (CompanyForm) form;
		companyProxy.addCompany(companyForm);
		
		return mapping.findForward("findAllCompany");
	}
	
	public ActionForward showByCompanyId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String companyId = request.getParameter("companyId");
		//获取请求中传递过来的pageNo
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
		
		Company company = companyProxy.findByCompanyId(Integer.valueOf(companyId));
		request.setAttribute("company", company);
		
		return mapping.findForward("success");
	}
	
	public void modifyCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		//调用proxy
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		Integer companyId = Integer.valueOf(request.getParameter("companyId"));
		String companyName = request.getParameter("companyName");
		List<Company> companyList = companyProxy.findByCompanyIdAndName(companyId, companyName);
		PrintWriter out = response.getWriter();
		if (companyList != null && !companyList.isEmpty()) {
			out.print("该企业名称已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward companyModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		CompanyForm companyForm = (CompanyForm) form;
		companyProxy.modifyCompany(companyForm);
		//获取隐藏表单域传递过来的pageNo
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
		
		return mapping.findForward("findAllCompany");
	}
	
	public ActionForward companyDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String companyId = request.getParameter("companyId");
		Company company = companyProxy.findByCompanyId(Integer.valueOf(companyId));
		request.setAttribute("company", company);
		
		return mapping.findForward("toDetailCompany");
	}
	
	public ActionForward companyDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String companyId = request.getParameter("companyId");
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
		try {
			companyProxy.removeCompany(Integer.valueOf(companyId));
		} catch (CompanyInUseException e) {
			request.setAttribute("errMsg", "企业使用中,无法删除");
		}
		catch (Exception e) {
			request.setAttribute("errMsg", "服务器繁忙");
		}
		
		return mapping.findForward("findAllCompany");
	}
}
