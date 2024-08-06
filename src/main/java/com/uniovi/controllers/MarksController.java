
package com.uniovi.controllers;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Mark;
import com.uniovi.errors.ErrorMessage;
import com.uniovi.services.MarksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/v1/mark")
public class MarksController {
	
	@Autowired //Inyectar el servicio
	private MarksService marksService;

	
	@RequestMapping()
	public List<Mark> getAllMarks(){
	    
	    List<Mark> marks = marksService.getMarks();
	 
	    // Incluir self link
	    for (Mark mark : marks) {
	        Link lSelf = WebMvcLinkBuilder
	                .linkTo(WebMvcLinkBuilder.methodOn(MarksController.class)
	                        .getMark(mark.getId())).withRel("mark");
	        mark.add(lSelf);
	        
	        Link lSubject = WebMvcLinkBuilder
	                .linkTo(WebMvcLinkBuilder.methodOn(SubjectsController.class)
	                        .getSubject(mark.getSubject().getId())).withRel("subject");
	        mark.add(lSubject);
	        
	    }
	    
	    return marks;

	}
	
	@RequestMapping("/{id}")
	public Mark getMark(@PathVariable  Long id){
		return marksService.getMark(id);
	}
	
	@PostMapping(value="", produces={"application/json"}, consumes={"application/json"} )   
	@ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<?> setMark(@RequestBody Mark mark){
		
		Mark addedMark = marksService.addMark(mark);
		return new ResponseEntity<Mark>(addedMark, null, HttpStatus.CREATED);
	}  


	@RequestMapping(value="/{id}", method=RequestMethod.PUT )   
	public Mark updateMark(@RequestBody Mark mark){
		
		// Viene completa
		if ( mark.notNullValues() ) {
			return marksService.updateFullMark(mark);
		
		// No viene completa
		} else  {
			// Campo revised
			if (mark.getRevised() != null)
				marksService.updateMarkRevised(mark.getRevised(), mark.getId());
			// ...
			return marksService.getMark(mark.getId());
		}
	}


	@RequestMapping(value="/{id}", method=RequestMethod.DELETE )   
	public boolean deleteMark(@PathVariable  Long id){
		return marksService.deleteMark(id);
	}

	@RequestMapping("/summary")
	public List<Mark> getAllMarksSummary(){
		return marksService.getMarksSummary();
	}


}

