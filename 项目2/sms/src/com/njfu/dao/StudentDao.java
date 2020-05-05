package com.njfu.dao;

import java.util.List;

import com.njfu.entity.Student;
import com.njfu.entity.vo.StudentVO;
import com.njfu.entity.vo.StudentVO2;

public interface StudentDao {
	public List<Student> selectStudentsByPage();
	
	public Student selectByUsername(String username);

	public void insertStudent(Student student);

	public Student selectById(Integer sid);

	public void updateStudent(Student student);

	public void deleteStudent(Integer sid);

	public List<Student> selectStudentsByCondition(StudentVO studentVO);

	public Student selectUserByUsernameAndPass(String username, String password);

	public Student selectUserByIdAndPass(StudentVO2 studentVO2);

	public void updatePassById(StudentVO2 studentVO2);

}
