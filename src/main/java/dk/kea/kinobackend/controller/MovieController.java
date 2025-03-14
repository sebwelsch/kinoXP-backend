package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.Category;
import dk.kea.kinobackend.model.Movie;
import dk.kea.kinobackend.repository.MovieRepository;
import dk.kea.kinobackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;

@RestController
@RequestMapping("movies")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @PostMapping("/add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie newMovie) {
        Movie savedMovie = movieRepository.save(newMovie);

        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
        // Saving cover image and setting file path for DB
//        if (!coverImage.isEmpty()) {
//            try {
//                String fileName = StringUtils.cleanPath(coverImage.getOriginalFilename());
//                Path targetLocation = Paths.get(uploadDir).resolve(fileName);
//                coverImage.transferTo(targetLocation);
//
//                // setting file path
//                String coverImagePath = uploadDir + fileName;
//                Movie newMovie = new Movie(name, description, age_rating, category, actors, duration, coverImagePath);
//                Movie savedMovie = movieRepository.save(newMovie);
//
//                return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            // setting file path to default if no image is provided
//            String coverImagePath = uploadDir + "default.png";
//            Movie newMovie = new Movie(name, description, age_rating, category, actors, duration, coverImagePath);
//            Movie savedMovie = movieRepository.save(newMovie);
//
//            return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
//        }
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

    @GetMapping("/categories")
    public List<String> getCategories() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
