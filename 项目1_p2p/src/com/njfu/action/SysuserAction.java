package com.njfu.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.njfu.entity.Sysuser;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.SysuserForm;
import com.njfu.service.SysuserService;

public class SysuserAction extends MappingDispatchAction {
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysuserForm sysuserForm = (SysuserForm) form;
		SysuserService sysuserProxy = (SysuserService) ObjectFactory.getObject("sysuserProxy");
		Sysuser user = sysuserProxy.findByLoginNameAndLoginPassword(sysuserForm);
		request.getSession().setAttribute("user", user);
		return mapping.findForward("success");
	}
	
}
