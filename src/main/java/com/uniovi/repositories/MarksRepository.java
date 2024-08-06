package com.uniovi.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.uniovi.entities.Mark;

import jakarta.transaction.Transactional;

public interface MarksRepository extends CrudRepository<Mark, Long>{

	Mark findByDescription (String description);
	Iterable<Mark> findAllByDescription (String description);

	@Query("select NEW com.uniovi.entities.Mark(m.id, m.description) from Mark m")
	Collection<Mark> findAllSummary();
	
	@Transactional
	@Modifying  // Lo requieren las Queries INSERT, UPDATE, DELETE,
	@Query("update Mark Set revised = ?1 WHERE id = ?2")
	int updateRevised (boolean revised, long id );
	// Solo puede retornar int 

}
