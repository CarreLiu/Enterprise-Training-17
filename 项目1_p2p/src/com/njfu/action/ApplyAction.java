package com.njfu.action;

import java.io.IOException;
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
import com.njfu.entity.Apply;
import com.njfu.entity.CompanyReport;
import com.njfu.entity.CompanyReportDetail;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.ApplyService;

public class ApplyAction extends MappingDispatchAction {
	//转发当前选中条的applyProductId
	public ActionForward saveApplyProductId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(Constant.CONTENT_TYPE);
		request.getSession().setAttribute("applyProductId", request.getParameter("applyProductId"));
		
		return mapping.findForward("success");
	}
	
	public void applyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ApplyService applyProxy = (ApplyService) ObjectFactory.getObject("applyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int applyProductId = Integer.parseInt((String) request.getSession().getAttribute("applyProductId"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<Apply> list = applyProxy.findByApplyProductId(applyProductId);
		//使用fastjson
		PageInfo<Apply> pageInfo = new PageInfo<Apply>(list);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
	
	public ActionForward findAllReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplyService applyProxy = (ApplyService) ObjectFactory.getObject("applyProxy");
		List<CompanyReport> reportList = applyProxy.findAllReport();
		request.setAttribute("reportList", reportList);
		
		//获取时间
		request.getSession().setAttribute("reportYear", null);
		
		//返回企业报表列表页面
		return mapping.findForward("toReportPage");
	}
	
	//报表详情
	public ActionForward reportDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplyService applyProxy = (ApplyService) ObjectFactory.getObject("applyProxy");
		//获取传递过来的id
		Integer id = Integer.valueOf(request.getParameter("id"));
		String reportYear = (String) request.getSession().getAttribute("reportYear");
		List<CompanyReportDetail> reportDetailList = null;
		//如果查询全部年份
		if (reportYear == null) {
			reportDetailList = applyProxy.findReportDetailByCompanyId(id);
		}
		else {
			reportDetailList = applyProxy.findReportDetailByCompanyIdAndYear(id, reportYear);
			request.getSession().setAttribute("reportYear", reportYear);
			
		}
		request.setAttribute("reportDetailList", reportDetailList);
		
		//返回报表详情页
		return mapping.findForward("toReportDetail");
	}
	
	public ActionForward findYearReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplyService applyProxy = (ApplyService) ObjectFactory.getObject("applyProxy");
		String year = request.getParameter("year");
		List<CompanyReport> reportList = null;
		if (year != null && year != "") {
			reportList = applyProxy.findReportByYear(year);
			request.getSession().setAttribute("reportYear", year);
		}
		else {
			reportList = applyProxy.findAllReport();
			request.getSession().setAttribute("reportYear", null);
		}
		request.setAttribute("reportList", reportList);
		
		//返回企业报表列表页面
		return mapping.findForward("toReportPage");
	}
}
