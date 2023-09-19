package com.kaiser.springjpa;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kaiser.springjpa.dao.StudentDAOImpl;
import com.kaiser.springjpa.entity.Student;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAOImpl studentDAO) {
		return runner -> {
			findAll(studentDAO);
		};
	}
	
	private void createStudent(StudentDAOImpl studentDAO) {
		Student tempStudent = new Student("Franco", "Gobo", "franco@email.com");
		
		studentDAO.save(tempStudent);
		
		System.out.println("Saved student. Generated Id: " + tempStudent.getId());
	}
	
	private void findStudent(StudentDAOImpl studentDAO, int id) {
			Student theStudent = studentDAO.findById(id);
			if(theStudent != null) {
				System.out.println(theStudent.toString());
			} else {
				System.out.println("Student doesn't found");
			}
	}
	
	private void findStudentsByLastName(StudentDAOImpl studentDAO, String theData) {
		List<Student> students = studentDAO.findByLastName(theData);
		if(students != null) {
			students.stream().forEach(student -> System.out.println(student.toString()));
		}
		
	}
	
	private void findAll(StudentDAOImpl studentDAO) {
		List<Student> students = studentDAO.findAll();
		students.stream().forEach(student -> System.out.println(student.toString()));
	}
	
	private void updateStudent(StudentDAOImpl studentDAO, Student theStudent, int id) {
		boolean flag = studentDAO.updateStudent(theStudent, id);
		if(flag == true) {
			System.out.printf("Student id: %d updated", id);
		} else {
			System.out.println("Student id doesn't match with a student in the database");
		}
	}

}
