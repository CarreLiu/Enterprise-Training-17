package com.njfu.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.alibaba.fastjson.JSON;
import com.njfu.constant.Constant;
import com.njfu.entity.Apply;
import com.njfu.entity.Product;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ApplyForm;
import com.njfu.service.ApplyService;
import com.njfu.service.ProductService;

public class FrontProductAction extends MappingDispatchAction {
	public ActionForward productInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductService productProxy = (ProductService) ObjectFactory.getObject("productProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int productId = Integer.parseInt(request.getParameter("productId"));
		Product product = productProxy.findByProductId(productId);
		request.setAttribute("product", product);
		
		return mapping.findForward("findProduct");
	}
	
	public void applyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType(Constant.CONTENT_TYPE);
		ApplyService applyProxy = (ApplyService) ObjectFactory.getObject("applyProxy");
		int applyProductId = Integer.parseInt(request.getParameter("applyProductId"));
		List<Apply> applyList = applyProxy.findByApplyProductId(applyProductId);
		//使用fastjson
		response.getWriter().print(JSON.toJSONString(applyList));
	}
	
	public void applyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplyService applyProxy = (ApplyService) ObjectFactory.getObject("applyProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		ApplyForm applyForm = (ApplyForm) form;
		applyProxy.addApply(applyForm);
		
	}
}
