package com.njfu.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njfu.constant.Constant;
import com.njfu.entity.Student;
import com.njfu.entity.Sysuser;
import com.njfu.entity.vo.SysuserVO;
import com.njfu.exception.UserOrPassWrongException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.StudentService;
import com.njfu.service.SysuserService;

public class SysuserAction {
	
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		
		SysuserService sysuserProxy = (SysuserService)ObjectFactory.getObject("sysuserProxy");
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		SysuserVO sysuserVO = new SysuserVO();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		sysuserVO.setUsername(username);
		sysuserVO.setPassword(password);
		String state = req.getParameter("state");
		if ("admin".equals(state)) {
			Sysuser sysuser = null;
			try {
				sysuser = sysuserProxy.findUserByUsernamePass(sysuserVO);
				req.getSession().setAttribute("user", sysuser);
			} catch (UserOrPassWrongException e) {
				e.printStackTrace();
				req.setAttribute("error", e.getMessage());
				return "fail";
			}
			
			return "admin";
		}
		else if ("student".equals(state)) {
			Student student = null;
			try {
				student = studentProxy.findUserByUsernameAndPass(username, password);
				req.getSession().setAttribute("user", student);
			} catch(Exception e){
				e.printStackTrace();
				req.setAttribute("error",e.getMessage());
				return "fail";
			}
			return "student";
		}
		
		return "fail";
	}
	
	
	public void modifyPwd(HttpServletRequest req,HttpServletResponse resp) throws IOException{

		//获取请求提交过来的值
		String id = req.getParameter("id");
		String oldPass = req.getParameter("oldPass");
		String newPass = req.getParameter("newPass");

		SysuserService sysuserProxy  = (SysuserService) ObjectFactory.getObject("sysuserProxy");
		SysuserVO sysuserVO = new SysuserVO();
		sysuserVO.setId(Integer.parseInt(id));
		sysuserVO.setPassword(oldPass);
		sysuserVO.setNewPass(newPass);
		resp.setContentType(Constant.CONTENT_TYPE);
		try {
			sysuserProxy.findUserByIdAndPass(sysuserVO);
			//根据id修改密码
			sysuserProxy.modifyPassById(sysuserVO);
			resp.getWriter().print("success");

		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().print("fail");
		}
	}
	
	public void logOut(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
	}
	
	//首页
	public String toAdminMain(HttpServletRequest req, HttpServletResponse resp) {
		//返回首页
		return "adminMain";
	}
	
	public String addSysuser(HttpServletRequest req, HttpServletResponse resp) {
		SysuserService sysuserProxy  = (SysuserService) ObjectFactory.getObject("sysuserProxy");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Integer state = 1;	//默认启用
		Sysuser sysuser = new Sysuser(null, username, password, state);
		sysuserProxy.addSysuser(sysuser);
		
		return "toRegist";
	}
}
