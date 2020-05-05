package com.njfu.service.proxy;

import java.io.File;
import java.util.List;

import com.njfu.entity.Student;
import com.njfu.entity.vo.StudentVO;
import com.njfu.entity.vo.StudentVO2;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.OldPassWrongException;
import com.njfu.exception.ServiceException;
import com.njfu.exception.StudentImportException;
import com.njfu.exception.StudentUsernameExistException;
import com.njfu.exception.UserOrPassWrongException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.StudentService;
import com.njfu.transaction.TransactionManager;

public class StudentProxy implements StudentService {

	@Override
	public List<Student> findStudentByPage() {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		List<Student> students = null;
		try {
			tran.beginTransaction();
			students = studentService.findStudentByPage();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}

		return students;
	}
	
	@Override
	public Student findByUsername(String username) throws StudentUsernameExistException {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		Student student = null;
		try {
			tran.beginTransaction();
			student = studentService.findByUsername(username);
			tran.commit();
		} catch (StudentUsernameExistException e) {
			throw e;
		}
		
		return student;
	}

	@Override
	public void addStudent(Student student) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		try {
			tran.beginTransaction();
			studentService.addStudent(student);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

	@Override
	public Student findById(Integer sid) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		Student student = null;
		try {
			tran.beginTransaction();
			student = studentService.findById(sid);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		
		return student;
	}
	
	@Override
	public void modifyStudent(Student student) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		try {
			tran.beginTransaction();
			studentService.modifyStudent(student);
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

	@Override
	public void removeById(Integer sid) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		try {
			tran.beginTransaction();
			studentService.removeById(sid);
			tran.commit();

		} catch (Exception e) {
			tran.rollback();
			//需要向外部抛出异常,将异常交给action层处理
			throw new RuntimeException("删除失败");
		}
	}

	@Override
	public void importExcel(String fileName, File file) throws StudentImportException {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		try {
			tran.beginTransaction();
			studentService.importExcel(fileName, file);
			tran.commit();

		} catch (Exception e) {
			tran.rollback();
			//需要向外部抛出异常,将异常交给action层处理
			throw new RuntimeException("导入数据失败");
		}
	}

	@Override
	public List<Student> findStudentsByCondition(StudentVO studentVO) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		List<Student> students = null;
		try {
			tran.beginTransaction();
			students = studentService.findStudentsByCondition(studentVO);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}

		return students;
	}
	
	@Override
	public Student findUserByUsernameAndPass(String username, String password) throws UserOrPassWrongException {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService) ObjectFactory.getObject("studentService");
		Student student = null;
		try {
			tran.beginTransaction();
			student = studentService.findUserByUsernameAndPass(username, password);
			tran.commit();

		} catch (UserOrPassWrongException e) {
			e.printStackTrace();
			tran.rollback();
			throw e;
		}

		return student;
	}
	
	@Override
	public Student findUserByIdAndPass(StudentVO2 studentVO2) throws OldPassWrongException {
		TransactionManager tran = (TransactionManager)ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService)ObjectFactory.getObject("studentService");
		Student student = null;
		try {
			tran.beginTransaction();
			student = studentService.findUserByIdAndPass(studentVO2);
			tran.commit();
		} 
        catch (DataAccessException e) {
        	tran.rollback();
        	throw new ServiceException(e.getMessage());
		}		
		catch (OldPassWrongException e) {
			tran.rollback();
			throw e;
		}
		return student;
		
	}

	@Override
	public void modifyPassById(StudentVO2 studentVO2) {
		TransactionManager tran = (TransactionManager)ObjectFactory.getObject("transaction");
		StudentService studentService = (StudentService)ObjectFactory.getObject("studentService");
		
		try {
			tran.beginTransaction();
			studentService.modifyPassById(studentVO2);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e.getMessage());
		}
	}

}
