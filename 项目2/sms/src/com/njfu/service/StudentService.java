package com.njfu.service;

import java.io.File;
import java.util.List;

import com.njfu.entity.Student;
import com.njfu.entity.vo.StudentVO;
import com.njfu.entity.vo.StudentVO2;
import com.njfu.exception.OldPassWrongException;
import com.njfu.exception.StudentImportException;
import com.njfu.exception.StudentUsernameExistException;
import com.njfu.exception.UserOrPassWrongException;

public interface StudentService {
	
	public List<Student> findStudentByPage();

	public Student findByUsername(String username) throws StudentUsernameExistException;
	
	public void addStudent(Student student);

	public Student findById(Integer sid);

	public void modifyStudent(Student student);

	public void removeById(Integer sid);

	public void importExcel(String fileName, File file) throws StudentImportException;

	public List<Student> findStudentsByCondition(StudentVO studentVO);
	
	public Student findUserByUsernameAndPass(String username, String password) throws UserOrPassWrongException;
	
	public Student findUserByIdAndPass(StudentVO2 studentVO2) throws OldPassWrongException;
	
	public void modifyPassById(StudentVO2 studentVO2);
}
