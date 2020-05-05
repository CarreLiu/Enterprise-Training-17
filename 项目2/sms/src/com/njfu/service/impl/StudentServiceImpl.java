package com.njfu.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.njfu.dao.CourseDao;
import com.njfu.dao.GradeDao;
import com.njfu.dao.StudentDao;
import com.njfu.entity.Student;
import com.njfu.entity.vo.StudentVO;
import com.njfu.entity.vo.StudentVO2;
import com.njfu.exception.OldPassWrongException;
import com.njfu.exception.StudentImportException;
import com.njfu.exception.StudentUsernameExistException;
import com.njfu.exception.UserOrPassWrongException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.StudentService;
import com.njfu.util.MD5;

public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> findStudentByPage() {
		StudentDao studentDao = (StudentDao) ObjectFactory.getObject("studentDao");
		List<Student> students = studentDao.selectStudentsByPage();
		
		return students;
	}

	@Override
	public Student findByUsername(String username) throws StudentUsernameExistException {
		StudentDao studentDao = (StudentDao) ObjectFactory.getObject("studentDao");
		Student student = studentDao.selectByUsername(username);
		if (student != null) {
			throw new StudentUsernameExistException("用户名(" + username + ")已经被占用");
		}
		
		return student;
	}
	
	@Override
	public void addStudent(Student student) {
		StudentDao studentDao = (StudentDao) ObjectFactory.getObject("studentDao");
		studentDao.insertStudent(student);
	}

	@Override
	public Student findById(Integer sid) {
		StudentDao studentDao = (StudentDao) ObjectFactory.getObject("studentDao");
		Student student = studentDao.selectById(sid);
		
		return student;
	}
	
	@Override
	public void modifyStudent(Student student) {
		StudentDao studentDao = (StudentDao)ObjectFactory.getObject("studentDao");
		studentDao.updateStudent(student);
	}

	@Override
	public void removeById(Integer sid) {
		StudentDao studentDao = (StudentDao)ObjectFactory.getObject("studentDao");
		studentDao.deleteStudent(sid);
	}

	@Override
	public void importExcel(String fileName, File file) throws StudentImportException {
		GradeDao gradeDao = (GradeDao) ObjectFactory.getObject("gradeDao");
		CourseDao courseDao = (CourseDao) ObjectFactory.getObject("courseDao");
		StudentDao studentDao = (StudentDao) ObjectFactory.getObject("studentDao");
		
		try {
			//判断是否是excel文档
			if (fileName.matches("^.+\\.((?i)(xls)|(xlsx)F)$")) {
				//1:读取工作簿
				Workbook workbook = null;
				if (fileName.matches("^.+\\.((?i)(xls))$")) {
					workbook = new HSSFWorkbook(new FileInputStream(file));	//03版本,后缀为xls
				}
				else {
					workbook = new XSSFWorkbook(new FileInputStream(file)); //07及以上版本,后缀为xlsx	
				}
				//2:读取工作表
				Sheet sheet = workbook.getSheetAt(0);
				//3:读取行
				if (sheet.getPhysicalNumberOfRows() > 2) {
					Student student = null;
					for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
						Row row = sheet.getRow(i);
						student = new Student();
						//读取单元格
						String username = row.getCell(0).getStringCellValue();
						student.setUsername(username);
						//密码默认123
						student.setPassword(MD5.MD5Encode("123"));
						student.setName(row.getCell(1).getStringCellValue());
						student.setGender("男".equals(row.getCell(2).getStringCellValue())?0:1);
						student.setAge((int)row.getCell(3).getNumericCellValue());
						student.setGid(gradeDao.selectIdByName(row.getCell(4).getStringCellValue()));
						student.setCid(courseDao.selectIdByName(row.getCell(5).getStringCellValue()));
						studentDao.insertStudent(student);
					}
				}
				
				//关闭工作表
				workbook.close();
			}
		} catch (Exception e) {
			throw new StudentImportException("导入失败: " + e.getMessage());
		}
	}

	@Override
	public List<Student> findStudentsByCondition(StudentVO studentVO) {
		StudentDao studentDao = (StudentDao) ObjectFactory.getObject("studentDao");
		List<Student> students = studentDao.selectStudentsByCondition(studentVO);
		
		return students;
	}
	
	@Override
	public Student findUserByUsernameAndPass(String username, String password) throws UserOrPassWrongException {
		StudentDao studentDao = (StudentDao)ObjectFactory.getObject("studentDao");
		Student student= studentDao.selectUserByUsernameAndPass(username, password);
		if (student == null) {
			throw new UserOrPassWrongException("用户名或密码错误");
		}
		
		return student;
	}
	
	@Override
	public Student findUserByIdAndPass(StudentVO2 studentVO2) throws OldPassWrongException {
		StudentDao studentDao = (StudentDao)ObjectFactory.getObject("studentDao");
		Student student = studentDao.selectUserByIdAndPass(studentVO2);
		if(student == null){
			throw new OldPassWrongException("旧密码错误");
		}
		return student;
	}

	@Override
	public void modifyPassById(StudentVO2 studentVO2) {
		StudentDao studentDao = (StudentDao)ObjectFactory.getObject("studentDao");
		studentDao.updatePassById(studentVO2);
		
	}
}
