package com.uniovi.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Subject;

public interface SubjectsRepository extends CrudRepository<Subject, Long> {


	@Query("select  NEW com.uniovi.entities.Subject(s.id, s.name) from Subject s")
	Collection<Subject> findAllWithoutMarks();

}
