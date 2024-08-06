package com.uniovi.services;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.uniovi.entities.Mark;
import com.uniovi.repositories.MarksRepository;

@Service
public class MarksService {
	
	@Autowired
	private MarksRepository marksRepository;  
	
	

    public List<Mark> getMarks(){
		List<Mark> marks = new ArrayList<Mark>();
		marksRepository.findAll().forEach(marks::add);
		//marksRepository.findAllByDescription("Ejercicio 1").forEach(marks::add);
		return marks;

	}
	@Cacheable(value="mark", key="#id")
	public Mark getMark(Long id){
		System.out.println("Get a Mark");
		return marksRepository.findById(id).get();
	}

    @Caching(evict = {
            @CacheEvict(value = "marksSummary", allEntries = true)
        })

    public Mark addMark(Mark mark){
       return marksRepository.save(mark);
    }


    @Caching(evict = {
            @CacheEvict(value="marksSummary",  key = "#mark.id"),
            @CacheEvict(value = "marksSummary", allEntries = true)
        })
    public Mark updateFullMark(Mark mark){
        return marksRepository.save(mark);
    }

	
    @Caching(evict = {
            @CacheEvict(value="mark",  key = "#id"),
            @CacheEvict(value = "marksSummary", allEntries = true)
        })

    public boolean deleteMark(Long id){
        if ( marksRepository.existsById(id) ) {
            marksRepository.deleteById(id);
            return true;
        }
        return false;
    }
	

	@Cacheable("marksSummary")
	public List<Mark> getMarksSummary(){
		System.out.println("Get a List<Mark> ");
		List<Mark> marks = new ArrayList<Mark>();
		marksRepository.findAllSummary().forEach(marks::add);
		return marks;
	}
    
	@CacheEvict(value="mark",  key = "#id")
	public int updateMarkRevised(boolean revised, long id){
		return marksRepository.updateRevised(revised, id);
	}

}
