package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	//hy 
    @PostMapping("/api/students")
	public Student saveMethod(@RequestBody Student student) {
    	return studentRepository.save(student);	
}
  

	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getAllEmp()
	{
		return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.CREATED  );
		
	}
    
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getonlyEmp(@PathVariable Long id) {
		Optional<Student>student=studentRepository.findById(id);
		if (student.isPresent()){
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY  );
		}
	}
	@DeleteMapping("/api/students/delete/{id}")
	public String deleteStudent(@PathVariable Long id)
	{
		 studentRepository.deleteById(id);
		return "deleted";
		
	}
	
	@DeleteMapping("/api/students/delete")
	public String deleteAllStudent()
	{
		 studentRepository.deleteAll()  ;
		return "deleted all successfully";
		
	}
	
	
	
	
}