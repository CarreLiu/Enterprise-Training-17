package com.njfu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.njfu.entity.Student;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.StudentService;

public class POITest {
	
	public static void main(String[] args) throws FileNotFoundException {
		StudentService studentProxy = (StudentService) ObjectFactory.getObject("studentProxy");
		List<Student> students = studentProxy.findStudentByPage();
		ExcelUtil.exportStudent(students, new FileOutputStream(new File("d:/a.xls")));
	}
}
