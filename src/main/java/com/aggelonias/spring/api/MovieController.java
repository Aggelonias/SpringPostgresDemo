package com.aggelonias.spring.api;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aggelonias.spring.exception.ResourceNotFoundException;
import com.aggelonias.spring.model.Movie;
import com.aggelonias.spring.repository.MovieRepository;

@RestController
public class MovieController {
	
	@Autowired
	private MovieRepository movieRepo;
	
	private String omdbApiKey = "(your omdb api key)";
	
	// Add a movie to the database
	@PostMapping(path="/add")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		
		// Create the url needed to call the api
		String url = "http://www.omdbapi.com/?apikey=" + omdbApiKey
		+ "&t=" + movie.getName();
		
		RestTemplate tmp = new RestTemplate();
		String result = tmp.getForObject(url, String.class);
		
		JsonParser parser = JsonParserFactory.getJsonParser();
		
		
		Map<String, Object> map = parser.parseMap(result);
		
		// Check if we actually found a movie
		if(map.get("Response").equals("False")) throw new ResourceNotFoundException("Movie not found with name " + movie.getName());
		
	    Movie newMovie = new Movie();
	    newMovie.setName((String) map.get("Title"));
	    newMovie.setDirector((String) map.get("Director"));
	    newMovie.setGenre((String) map.get("Genre"));
	    newMovie.setImdbRating(Float.parseFloat((String)map.get("imdbRating")));
	    newMovie.setRuntime((String)map.get("Runtime"));
	    newMovie.setYear(Integer.parseInt((String)map.get("Year")));
	    movieRepo.save(newMovie);
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}
	
	// Return all movies inside the database
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Movie> getAllMovies() {
		return movieRepo.findAll();
	}
	
	// Return the movie with the specified id
	@GetMapping(path="/movie/{id}")
	public Movie getMovie(@PathVariable Long id) {
		return movieRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
	}
	
	// Return the movie with the specified name
	@GetMapping(path="/search/name")
	public Movie getMovieByName(@RequestBody Movie movie) {
		return movieRepo.findByName(movie.getName())
				.orElseThrow(() -> new ResourceNotFoundException("Movie not found with name " + movie.getName()));
	}
	
	// Return all the movies for that year
	@GetMapping(path="/search/year")
	public List<Movie> getMoviesByYear(@RequestBody Movie movie) {
		List<Movie> movies= movieRepo.findByYear(movie.getYear());
		if(movies.size() == 0) throw new ResourceNotFoundException("There aren't any movies for this year (" + movie.getYear() + ")");
		return movies;
	}

}
