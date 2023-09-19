package com.kaiser.springjpa.dao;

import com.kaiser.springjpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO{

	private EntityManager entityManager;
	
	@Autowired
	public StudentDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Student theStudent) {
		entityManager.persist(theStudent);
	}

	@Override
	public void delete(Student theStudent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Student findById(int id) {
		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
		List<Student> students = theQuery.getResultList();
		return students;
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
		theQuery.setParameter("theData", lastName);
		List<Student> students = theQuery.getResultList();
		return students;
	}

	@Override
	@Transactional
	public boolean updateStudent(Student theStudent, int id) {
		Student oldStudent = entityManager.find(Student.class, id);
		if (oldStudent != null) {
			oldStudent.setFirstName(theStudent.getFirstName());
			oldStudent.setLastName(theStudent.getLastName());
			oldStudent.setEmail(theStudent.getEmail());
			entityManager.merge(oldStudent);
			return true;
		}
		return false;
	}
	
	
	

}
