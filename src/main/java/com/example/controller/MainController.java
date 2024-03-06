package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Student;
import com.example.service.StudentService;

@Controller
public class MainController {

	@Autowired
	public StudentService serv;
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students",serv.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "student_form";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {		 
		 
		 serv.saveStudent(student);
		 return "redirect:/students";
		 
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudenForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", serv.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student,Model model) {
		Student existingStd = serv.getStudentById(id);
		existingStd.setId(id);
		existingStd.setFirstName(student.getFirstName());
		existingStd.setLastName(student.getLastName());
		existingStd.setEmail(student.getEmail());
		
		serv.updateStudent(existingStd);
		
		return "redirect:/students";
	}
	
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		serv.deleteStudentByid(id);
		return "redirect:/students";
	}
	
}
