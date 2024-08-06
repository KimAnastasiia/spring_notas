package com.uniovi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Subject;
import com.uniovi.services.SubjectsService;

@RestController
@RequestMapping("/v1/subject")

public class SubjectsController {
	@Autowired //Inyectar el servicio
	private SubjectsService subjectsService;

	@RequestMapping() 
	public List<Subject> getSubjects(){
		// NOTA: Version MUY reducida para simplificar, ni usamos ResponseEntity<?>
		// Automáticamente pone un Content-type: application/json. 
		return subjectsService.getAllWithoutMarks();
	}
	@RequestMapping("/{id}/mark")
	public List<Mark> getSubjectMarks(@PathVariable  Long id){
		Subject subject = subjectsService.getSubject(id);		
		return subject.getMarks();
	}

	@RequestMapping("/{id}")
	public Subject getSubject(@PathVariable  Long id){	
		return subjectsService.getSubject(id);
	}
	
	@PostMapping(value="", produces={"application/json"}, consumes={"application/json"} )   
	public Subject addSubject(@RequestBody Subject subject  ){	
		return subjectsService.addSubject(subject);
	}

}
