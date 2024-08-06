package com.uniovi.services;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Subject;
import com.uniovi.repositories.SubjectsRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SubjectsService {

	@Autowired
	private SubjectsRepository subjectsRepository;  
	
    @PostConstruct
    public void init(){
        Subject subject1 = new Subject();
        subject1.setName("Lengua Española");

        List subject1Marks = new LinkedList<Mark>();
        // Las Marks tienen que tener Subject
        subject1Marks.add(new Mark("Ejercicio 1",10.0,false, subject1));
        subject1Marks.add(new Mark("Ejercicio 2",9.0, false, subject1));
        subject1Marks.add(new Mark("Ejercicio 1",6.0, false, subject1));
        subject1.setMarks(subject1Marks);
        
        // Agregar la Subject al servicio
        addSubject(subject1);

    	Subject subject2 = new Subject();
    	subject2.setName("Arte");
    	addSubject(subject2);    

    }

    
	public List<Subject> getAllWithoutMarks() {
		List<Subject> subjects = new ArrayList<Subject>();
		subjectsRepository.findAllWithoutMarks().forEach(subjects::add);
		return subjects;
	}

	public List<Subject> getAllSubjects() {
		List<Subject> subjects = new ArrayList<Subject>();
		subjectsRepository.findAll().forEach(subjects::add);
		return subjects;
	}

	public Subject getSubject(Long id) {
		return subjectsRepository.findById(id).get();
	}

	public Subject addSubject(Subject subject) {
		return subjectsRepository.save(subject);
	}

	public void deleteSubject(Long id) {
		subjectsRepository.deleteById (id);
	}

	
}
