package com.aggelonias.spring.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aggelonias.spring.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	// Find movie by its name
	Optional<Movie> findByName(String name);
	
	// Find movies by year
	List<Movie> findByYear(Integer year);
}
