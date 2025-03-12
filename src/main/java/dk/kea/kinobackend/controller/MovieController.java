package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.Movie;
import dk.kea.kinobackend.repository.MovieRepository;
import dk.kea.kinobackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @PostMapping("/add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie newMovie) {
        Movie savedMovie = movieRepository.save(newMovie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteMovie(@RequestParam int movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
