package com.uniovi.entities;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Mark extends RepresentationModel<Mark> {
	@Id
	@GeneratedValue
	private Long id;
	

	private String description;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)


	private Double score = null;;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean revised;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Subject subject;
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public boolean notNullValues() {
		if ( id == null)
			return false;
		if ( description == null)
			return false;
		if ( score == null)
			return false;
		if ( revised == null)
			return false;
	
		return true;
	}
	
	public Mark() {
		super();
	}

	public Mark( String description, Double score, Boolean revised, Subject subject) {
		super();
		this.description = description;
		this.score = score;
		this.revised = revised;
	    this.subject = subject;
	}
	public Mark(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
		this.score = null;
		this.revised = null;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getRevised() {
		return revised;
	}
	public void setRevised(Boolean revised) {
		this.revised = revised;
	}

}
