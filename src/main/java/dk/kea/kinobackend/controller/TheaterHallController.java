package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.TheaterHall;
import dk.kea.kinobackend.repository.TheaterHallRepository;
import dk.kea.kinobackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theaterhalls")
@CrossOrigin(origins = "*")
public class TheaterHallController {

    @Autowired
    MovieService movieService;

    @Autowired
    TheaterHallRepository theaterHallRepository;

    @PostMapping("/add")
    public ResponseEntity<TheaterHall> createTheaterHall(@RequestBody TheaterHall newTheaterHall) {
        TheaterHall savedTheaterHall = theaterHallRepository.save(newTheaterHall);
        return new ResponseEntity<>(savedTheaterHall, HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteTheaterHall(@RequestParam int hallId) {
        if (theaterHallRepository.existsById(hallId)) {
            theaterHallRepository.deleteById(hallId);
            return new ResponseEntity<>("Theater hall deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Theater hall not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<TheaterHall> getAllTheaterHalls() {
        return theaterHallRepository.findAll();
    }
}
