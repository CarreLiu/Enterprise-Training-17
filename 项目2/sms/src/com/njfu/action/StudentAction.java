package com.njfu.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.njfu.constant.Constant;
import com.njfu.entity.Course;
import com.njfu.entity.Grade;
import com.njfu.entity.Student;
import com.njfu.entity.vo.PageInfo;
import com.njfu.entity.vo.StudentVO;
import com.njfu.entity.vo.StudentVO2;
import com.njfu.exception.StudentImportException;
import com.njfu.exception.StudentUsernameExistException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.CourseService;
import com.njfu.service.GradeService;
import com.njfu.service.StudentService;
import com.njfu.util.ExcelUtil;

public class StudentAction {

	public String findStudents(HttpServletRequest req, HttpServletResponse resp) {
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		List<Student> students = studentProxy.findStudentByPage();
		req.setAttribute("students", students);
		return "adminMain";
	}
	
	public void findStudentsByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		String pageNoStr = req.getParameter("pageNo");
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 0;
		int pageSize = 0;
		if (pageNoStr == null) {
			pageNo = Constant.PAGE_NO;
		}
		else {
			pageNo = Integer.parseInt(pageNoStr);
		}
		if (pageSizeStr == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		else {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		PageHelper.startPage(pageNo, pageSize);
		List<Student> students = studentProxy.findStudentByPage();
		PageInfo<Student> pageInfo = new PageInfo<Student>(students);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(pageInfo));
	}
	
	//显示学生管理页面
	public String toStudentManager(HttpServletRequest req, HttpServletResponse resp) {
		//获取页面中的下拉列表值
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		List<Grade> gradeList = gradeProxy.findAll();
		List<Course> courseList = courseProxy.findAll();
		req.setAttribute("gradeList", gradeList);
		req.setAttribute("courseList", courseList);
		
		//显示添加学生页面
		return "toStudentManager";
	}
	
	//校验用户名是否已经存在
	public void findByUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE);
		String primeUsername = req.getParameter("primeUsername");
		String username = req.getParameter("username");
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//为防止修改学员信息的时候,用户名不变
			if (primeUsername == null || !primeUsername.equals(username)) {
				studentProxy.findByUsername(username);
			}
			map.put("valid", true);//设置valid属性,在false时,输出message所对应的值
		} catch (StudentUsernameExistException e) {
			// TODO Auto-generated catch block
			map.put("valid", false);
			map.put("message", e.getMessage());
		}
		//返回2个值:message,是否输出该消息:valid
		resp.getWriter().print(JSON.toJSON(map));
	}
	
	//添加新学员
	public String addStudent(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		Integer gender = Integer.valueOf(req.getParameter("gender"));
		Integer age = Integer.valueOf(req.getParameter("age"));
		Integer gid = Integer.valueOf(req.getParameter("gid"));
		Integer cid = Integer.valueOf(req.getParameter("cid"));
		
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		Student student = new Student(null, username, password, name, gender, age, gid, cid);
		studentProxy.addStudent(student);
		
		return "toStudentManager";
	}
	
	//显示修改学员页面
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE);
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		Integer sid = Integer.valueOf(req.getParameter("sid"));
		Student student = studentProxy.findById(sid);
		resp.getWriter().print(JSON.toJSON(student));
	}
	
	// 修改学员
	public String modifyStudent(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		Integer sid = Integer.valueOf(req.getParameter("sid"));
		Integer gender = Integer.valueOf(req.getParameter("gender"));
		Integer age = Integer.valueOf(req.getParameter("age"));
		Integer gid = Integer.valueOf(req.getParameter("gid"));
		Integer cid = Integer.valueOf(req.getParameter("cid"));

		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		Student student = new Student(sid, username, null, name, gender, age, gid, cid);
		studentProxy.modifyStudent(student);

		return "toStudentManager";
	}
	
	//删除学员
	public String deleteStudent(HttpServletRequest req, HttpServletResponse resp) {
		Integer sid = Integer.valueOf(req.getParameter("sid"));

		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		try {
			studentProxy.removeById(sid);
			req.setAttribute("msg", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("msg", "删除失败");
		}

		return "toStudentManager";
	}
	
	//导出excel
	//不需要返回值
	public void exportExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置响应类型
		resp.setContentType("application/x-excel");
		//设置处理方式为附件
		resp.setHeader("content-disposition", "attachment;filename=student1.xls");
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		List<Student> students = studentProxy.findStudentByPage();
		//将记录输出的xls,通过浏览器下载
		ExcelUtil.exportStudent(students, resp.getOutputStream());
	}
	
	//导出数据到数据库
	public String importExcel(HttpServletRequest req, HttpServletResponse resp) {
		String fileName = "student.xls";
		File file = new File("d:/students.xls");
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		try {
			studentProxy.importExcel(fileName, file);
			req.setAttribute("msg", "导入成功");
		} catch (StudentImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("msg", e.getMessage());
		}
		
		return "toStudentManager";
	}
	
	//按条件查询学员信息
	public void findByCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String pageNoStr = req.getParameter("pageNo");
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 0;
		int pageSize = 0;
		if (pageNoStr == null) {
			pageNo = Constant.PAGE_NO;
		}
		else {
			pageNo = Integer.parseInt(pageNoStr);
		}
		if (pageSizeStr == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		else {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		String name = req.getParameter("name");
		String minAge = req.getParameter("minAge");
		String maxAge = req.getParameter("maxAge");
		String gender = req.getParameter("gender");
		String gid = req.getParameter("gid");
		String cid = req.getParameter("cid");
		
		StudentVO studentVO = new StudentVO();
		if (!"".equals(name) && name != null) {
			studentVO.setName("%" + name + "%");
		}
		if (!"".equals(minAge) && minAge != null) {
			studentVO.setMinAge(Integer.valueOf(minAge));
		}
		if (!"".equals(maxAge) && maxAge != null) {
			studentVO.setMaxAge(Integer.valueOf(maxAge));
		}
		if (!"all".equals(gender)) {
			studentVO.setGender(Integer.valueOf(gender));
		}
		if (!"all".equals(gid)) {
			studentVO.setGid(Integer.valueOf(gid));
		}
		if (!"all".equals(cid)) {
			studentVO.setCid(Integer.valueOf(cid));
		}
		
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		PageHelper.startPage(pageNo, pageSize);
		List<Student> students = studentProxy.findStudentsByCondition(studentVO);
		PageInfo<Student> pageInfo = new PageInfo<Student>(students);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(pageInfo));
	}
	
	public String toStudentMain(HttpServletRequest req, HttpServletResponse resp) {
		
		return "toStudentMain";
	}
	
	public void modifyPwd(HttpServletRequest req,HttpServletResponse resp) throws IOException{

		//获取请求提交过来的值
		String sid = req.getParameter("sid");
		String oldPass = req.getParameter("oldPass");
		String newPass = req.getParameter("newPass");

		StudentService studentProxy  = (StudentService) ObjectFactory.getObject("studentProxy");
		StudentVO2 studentVO2 = new StudentVO2();
		studentVO2.setSid(Integer.parseInt(sid));
		studentVO2.setPassword(oldPass);
		studentVO2.setNewPass(newPass);
		resp.setContentType(Constant.CONTENT_TYPE);
		try {
			studentProxy.findUserByIdAndPass(studentVO2);
			//根据sid修改密码
			studentProxy.modifyPassById(studentVO2);
			resp.getWriter().print("success");

		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().print("fail");
		}
	}
	
	public void logOut(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
	}
	
	public String modifyStudentInfo(HttpServletRequest req, HttpServletResponse resp) {
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		Integer sid = Integer.valueOf(req.getParameter("sid"));
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		Integer age = Integer.valueOf(req.getParameter("age"));
		Integer gender = Integer.valueOf(req.getParameter("gender"));
		Integer gid = Integer.valueOf(req.getParameter("gid"));
		Integer cid = Integer.valueOf(req.getParameter("cid"));
		Student student = new Student(sid, username, null, name, gender, age, gid, cid);
		studentProxy.modifyStudent(student);
		//更新session中的登录用户信息
		Student stu = studentProxy.findById(sid);
		req.getSession().setAttribute("user", stu);
		
		return "toStudentMain";
	}
	
}
