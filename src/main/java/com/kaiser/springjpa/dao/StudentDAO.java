package com.kaiser.springjpa.dao;

import java.util.List;

import com.kaiser.springjpa.entity.Student;

public interface StudentDAO {
	
	void save(Student theStudent);

	void delete(Student theStudent);
	
	Student findById(int id);

	List<Student> findByLastName(String lastName);
	
	List<Student> findAll();
	
	boolean updateStudent(Student theStudent, int id);
	}
