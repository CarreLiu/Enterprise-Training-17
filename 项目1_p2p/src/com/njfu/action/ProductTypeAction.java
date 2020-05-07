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
import com.njfu.entity.News;
import com.njfu.entity.ProductType;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ProductTypeForm;
import com.njfu.service.ProductTypeService;

public class ProductTypeAction extends MappingDispatchAction {
	public void productTypeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<ProductType> list = productTypeProxy.findProductType();
		//使用fastjson
		PageInfo<ProductType> pageInfo = new PageInfo<ProductType>(list);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
	
	public void addCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		String productTypeName = request.getParameter("productTypeName");
		List<ProductType> productTypeList = productTypeProxy.findByTypeName(productTypeName);
		PrintWriter out = response.getWriter();
		if (productTypeList != null && !productTypeList.isEmpty()) {
			out.print("该产品类型名称已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward productTypeAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		
		ProductTypeForm productTypeForm = (ProductTypeForm) form;
		productTypeProxy.addProductType(productTypeForm);
		
		return mapping.findForward("findAllProductType");
	}
	
	public void modifyCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		Integer productTypeId = Integer.valueOf(request.getParameter("productTypeId"));
		String productTypeName = request.getParameter("productTypeName");
		List<ProductType> productTypeList = productTypeProxy.findByTypeIdAndName(productTypeId, productTypeName);
		PrintWriter out = response.getWriter();
		if (productTypeList != null && !productTypeList.isEmpty()) {
			out.print("该产品类型名称已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward productTypeModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		
		ProductTypeForm productTypeForm = (ProductTypeForm) form;
		productTypeProxy.modifyProductType(productTypeForm);
		
		return mapping.findForward("findAllProductType");
	}
	
	public ActionForward productTypeStatusModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductTypeService productTypeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		Integer productTypeId = Integer.valueOf(request.getParameter("productTypeId"));
		Integer productTypeStatus = Integer.valueOf(request.getParameter("productTypeStatus"));
		
		ProductType productType = new ProductType();
		productType.setProductTypeId(productTypeId);
		productType.setProductTypeStatus(productTypeStatus);
		productTypeProxy.modifyProductTypeStatus(productType);
		
		return mapping.findForward("findAllProductType");
	}
}
