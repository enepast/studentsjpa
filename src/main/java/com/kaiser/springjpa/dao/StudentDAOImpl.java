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
	@Transactional
	public void delete(int id) {
		Student theStudent = entityManager.find(Student.class, id);
		entityManager.remove(theStudent);
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
	public int updateLastName(String lastName, int id) {
		String theQuery = "UPDATE Student SET lastName=:valueLastName WHERE id=:valueId" ;
		int numRowsUpdated = entityManager.createQuery(theQuery)
				.setParameter("valueLastName", lastName)
				.setParameter("valueId", id)
				.executeUpdate();
		return numRowsUpdated;
		
	}

	@Override
	@Transactional
	public Student anotherUpdateLastName(Student theStudent) {
		Student resultStudent = entityManager.merge(theStudent);
		return resultStudent;
	}
	
	
	

}
