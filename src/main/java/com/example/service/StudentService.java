package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	public StudentRepository repos;
	
	public List<Student> getAllStudents(){
		return repos.findAll();
	}
	
	public Student saveStudent(Student student) {
		
		return repos.save(student);
	}
	
	public Student getStudentById(Long id) {
	
		return repos.findById(id).get();
	}
	
	public Student updateStudent(Student student) {
		
		return repos.save(student);
	}
	
	public void deleteStudentByid(Long id) {
		 repos.deleteById(id);
	}
	
}
