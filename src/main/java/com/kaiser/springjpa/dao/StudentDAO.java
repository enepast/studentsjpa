package com.kaiser.springjpa.dao;

import java.util.List;

import com.kaiser.springjpa.entity.Student;

public interface StudentDAO {
	
	void save(Student theStudent);

	void delete(int id);
	
	Student findById(int id);

	List<Student> findByLastName(String lastName);
	
	List<Student> findAll();
	
	int updateLastName(String lastName, int id);

	Student anotherUpdateLastName(Student theStudent);
	}
