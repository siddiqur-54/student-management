package com.example.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.services.StudentService;


@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> readStudents() {
		return studentService.readStudents();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> readStudentById(@PathVariable("id") Long id) {
        return studentService.readStudentById(id);
    }
	
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ResponseEntity<?> readStudentByFilterId(@RequestParam("id") Long id) {
        return studentService.readStudentById(id);
    }

	
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> readStudentByName(@PathVariable("name") String nameForSearch) {
		return studentService.readStudentByName(nameForSearch);
	}

	
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(
            @PathVariable("id") Long id, 
            @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }
}