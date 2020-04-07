package com.njfu.action;

import java.io.IOException;
import java.util.Date;
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
import com.njfu.entity.LendingPeriod;
import com.njfu.entity.Product;
import com.njfu.entity.ProductType;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.LendingPeriodService;
import com.njfu.service.ProductService;
import com.njfu.service.ProductTypeService;

public class IndexAction extends MappingDispatchAction {
	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取三个proxy
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		ProductTypeService typeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		LendingPeriodService lendProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		
		//获取这三个集合
		List<Product> products = productProxy.findProduct();
		List<ProductType> typeList = typeProxy.findProductType();
		List<LendingPeriod> periodList = lendProxy.findLendingPeriod();
		
		//将这三个集合存入request作用域
		request.setAttribute("products", products);
		request.setAttribute("typeList", typeList);
		request.setAttribute("periodList", periodList);		
		
		//返回首页
		return mapping.findForward("success");
	}
	
	//返回首页前获取条件列表
	public ActionForward index_ajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取2个proxy
		ProductTypeService typeProxy = (ProductTypeService) ObjectFactory.getObject("productTypeProxy");
		LendingPeriodService lendProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		
		//获取这2个集合
		List<ProductType> typeList = typeProxy.findProductType();
		List<LendingPeriod> periodList = lendProxy.findLendingPeriod();
		
		//将这2 个集合存入request作用域
		request.setAttribute("typeList", typeList);
		request.setAttribute("periodList", periodList);
		
		//返回首页
		return mapping.findForward("success");
	}
	
	//前台首页(ajax分页,不带查询条件)
	public void query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(Constant.CONTENT_TYPE);
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<Product> proList = productProxy.findFrontProducts();
		//使用fastjson
		PageInfo<Product> pageInfo = new PageInfo<Product>(proList);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
	
	//显示前台首页,按条件查询
	public void queryCondition(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(Constant.CONTENT_TYPE);
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		//获取ajax提交过来的查询参数值
		Integer typeId = null;
		Integer periodId = null;
		Double rateFrom = null;
		Double rateTo = null;
		Integer amountFrom = null;
		Integer amountTo = null;
		
		//获取提交过来的这6个值
		//产品类型
		String typeIdStr = request.getParameter("typeId");
		if (typeIdStr != null) {
			typeId = Integer.valueOf(typeIdStr);
		}
		//贷款周期
		String periodIdStr = request.getParameter("periodId");
		if (periodIdStr != null) {
			periodId = Integer.valueOf(periodIdStr);
		}
		//产品利率
		String rateFromStr = request.getParameter("rateFrom");
		if (rateFromStr != null) {
			rateFrom = Double.valueOf(rateFromStr);
		}
		String rateToStr = request.getParameter("rateTo");
		if (rateToStr != null) {
			rateTo = Double.valueOf(rateToStr);
		}
		//贷款规模
		String amountFromStr = request.getParameter("amountFrom");
		if (amountFromStr != null) {
			amountFrom = Integer.valueOf(amountFromStr);
		}
		String amountToStr = request.getParameter("amountTo");
		if (amountToStr != null) {
			amountTo = Integer.valueOf(amountToStr);
		}
		
		Product pro = new Product();
		ProductType proType = new ProductType();
		LendingPeriod lendPeriod = new LendingPeriod();
		if (typeId != null) {
			proType.setProductTypeId(typeId);
			pro.setProductType(proType);
		}
		if (periodId != null) {
			lendPeriod.setLendingPeriodId(periodId);
			pro.setLendingPeriod(lendPeriod);
		}
		if (rateFrom != null) {
			pro.setPrimeLendingRateFrom(rateFrom);
		}
		if (rateTo != null) {
			pro.setPrimeLendingRateTo(rateTo);
		}
		if (amountFrom != null) {
			pro.setFinancingAmountFrom(amountFrom);
		}
		if (amountTo != null) {
			pro.setFinancingAmountTo(amountTo);
		}
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<Product> proList = productProxy.findFrontProductByQueryVO(pro);
		//使用fastjson
		PageInfo<Product> pageInfo = new PageInfo<Product>(proList);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
}
