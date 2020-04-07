package com.njfu.action;

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
import com.njfu.factory.ObjectFactory;
import com.njfu.service.NewsService;

public class FrontNewsAction extends MappingDispatchAction {
	public ActionForward headingNewsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		List<News> headingNewsList = newsProxy.findHeadingNews();
		request.setAttribute("headingNewsList", headingNewsList);
		
		return mapping.findForward("findHeadingNews");
	}
	
	public void commonNewsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<News> list = newsProxy.findCommonNews();
		//使用fastjson
		PageInfo<News> pageInfo = new PageInfo<News>(list);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
}
