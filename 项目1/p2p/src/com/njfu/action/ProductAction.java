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
import com.njfu.entity.LendingPeriod;
import com.njfu.entity.Product;
import com.njfu.entity.ProductType;
import com.njfu.exception.ProductInUseException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ProductForm;
import com.njfu.service.CompanyService;
import com.njfu.service.LendingPeriodService;
import com.njfu.service.ProductService;
import com.njfu.service.ProductTypeService;

public class ProductAction extends MappingDispatchAction {
	public void productList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<Product> list = productProxy.findProduct();
		//使用fastjson
		PageInfo<Product> pageInfo = new PageInfo<Product>(list);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CompanyService companyProxy = (CompanyService) ObjectFactory.getObject("companyProxy");
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		List<Company> company = companyProxy.findCompany();
		List<ProductType> productType = productTypeProxy.findProductType();
		List<LendingPeriod> lendingPeriod = lendingPeriodProxy.findLendingPeriod();
		
		request.setAttribute("companies", company);
		request.setAttribute("productTypes", productType);
		request.setAttribute("lendingPeriods", lendingPeriod);
		
		return mapping.findForward("addProduct");
	}
	
	public void addCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		//调用proxy
		ProductService proProxy = (ProductService) ObjectFactory.getObject("productProxy");
		String productName = request.getParameter("productName");
		Integer companyId = Integer.valueOf(request.getParameter("companyId"));
		List<Product> proList = proProxy.findByComIdAndProName(companyId, productName);
		PrintWriter out = response.getWriter();
		if (proList != null && !proList.isEmpty()) {
			out.print("该公司产品名称已存在");
		}
		else {
			out.print("");
		}
	}	
	
	public ActionForward productAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		
		ProductForm productForm = (ProductForm) form;
		productProxy.addProduct(productForm);
		
		return mapping.findForward("findAllProduct");
	}
	
	public ActionForward toModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		ProductService productProxy=(ProductService) ObjectFactory.getObject("productProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		List<ProductType> productType = productTypeProxy.findProductType();
		List<LendingPeriod> lendingPeriod = lendingPeriodProxy.findLendingPeriod();
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		//获取请求中传递过来的pageNo
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
		
		Product product = productProxy.findByProductId(productId);
		request.setAttribute("productTypes", productType);
		request.setAttribute("lendingPeriods", lendingPeriod);
		request.setAttribute("product", product);
		
		return mapping.findForward("modifyProduct");
	}
	
	public ActionForward productModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		ProductForm productForm = (ProductForm) form;
		productProxy.modifyProduct(productForm);
		//获取隐藏表单域传递过来的pageNo
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
		
		return mapping.findForward("findAllProduct");
	}
	
	public ActionForward productDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String productId = request.getParameter("productId");
		Product product = productProxy.findByProductId(Integer.parseInt(productId));
		request.setAttribute("product", product);
		
		return mapping.findForward("toDetailPage");
	}
	
	public ActionForward productDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String productId = request.getParameter("productId");
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
		try {
			productProxy.removeProduct(Integer.parseInt(productId));
		} catch (ProductInUseException e) {
			request.setAttribute("errMsg", "产品使用中,无法删除");
		}
		
		catch (Exception e) {
			request.setAttribute("errMsg", "服务器繁忙");
		}
		
		return mapping.findForward("findAllProduct");
	}
}
