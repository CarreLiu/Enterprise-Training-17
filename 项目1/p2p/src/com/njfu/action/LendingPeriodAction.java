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
import com.njfu.entity.LendingPeriod;
import com.njfu.entity.ProductType;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.LendingPeriodForm;
import com.njfu.service.LendingPeriodService;
import com.njfu.service.ProductTypeService;

public class LendingPeriodAction extends MappingDispatchAction {
	public void lendingPeriodList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<LendingPeriod> list = lendingPeriodProxy.findLendingPeriod();
		//使用fastjson
		PageInfo<LendingPeriod> pageInfo = new PageInfo<LendingPeriod>(list);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
	
	public void addCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		String period = request.getParameter("period");
		List<LendingPeriod> lendingPeriodList = lendingPeriodProxy.findByPeriod(period);
		PrintWriter out = response.getWriter();
		if (lendingPeriodList != null && !lendingPeriodList.isEmpty()) {
			out.print("该贷款周期已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward lendingPeriodAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		
		LendingPeriodForm lendingPeriodForm = (LendingPeriodForm) form;
		lendingPeriodProxy.addLendingPeriod(lendingPeriodForm);
		
		return mapping.findForward("findAllLendingPeriod");
	}
	
	public void modifyCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		Integer lendingPeriodId = Integer.valueOf(request.getParameter("lendingPeriodId"));
		String period = request.getParameter("period");
		List<LendingPeriod> lendingPeriodList = lendingPeriodProxy.findByIdAndPeriod(lendingPeriodId, period);
		PrintWriter out = response.getWriter();
		if (lendingPeriodList != null && !lendingPeriodList.isEmpty()) {
			out.print("该贷款周期已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward lendingPeriodModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		
		LendingPeriodForm lendingPeriodForm = (LendingPeriodForm) form;
		lendingPeriodProxy.modifyLendingPeriod(lendingPeriodForm);
		
		return mapping.findForward("findAllLendingPeriod");
	}
	
	public ActionForward periodStatusModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LendingPeriodService lendingPeriodProxy = (LendingPeriodService) ObjectFactory.getObject("lendingPeriodProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		Integer lendingPeriodId = Integer.valueOf(request.getParameter("lendingPeriodId"));
		Integer periodStatus = Integer.valueOf(request.getParameter("periodStatus"));
		
		LendingPeriod lendingPeriod = new LendingPeriod();
		lendingPeriod.setLendingPeriodId(lendingPeriodId);
		lendingPeriod.setPeriodStatus(periodStatus);
		lendingPeriodProxy.modifyPeriodStatus(lendingPeriod);
		
		return mapping.findForward("findAllLendingPeriod");
	}
	
}
