package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.Show;
import dk.kea.kinobackend.service.ShowService;
import dk.kea.kinobackend.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
@CrossOrigin(origins = "*")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private ShowRepository showRepository;


    @GetMapping("/all")
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @GetMapping("/dates")
    public List<String> getDates(@RequestParam("movie_id") int movieId) {
        return showService.getAvailableDates(movieId);
    }

    @GetMapping("/times")
    public List<String> getTimes(@RequestParam("movie_id") int movieId, @RequestParam("date") String date) {
        return showService.getAvailableTimes(movieId, date);
    }


    @GetMapping("/availability")
    public boolean checkAvailability(@RequestParam int hallId, @RequestParam String time) {
        return showRepository.findByHallIdAndTime(hallId, time).isEmpty();
    }


    @PostMapping("/add")
    public ResponseEntity<Show> createShow(@RequestBody Show show) {

        boolean available = showRepository.findByHallIdAndTime(show.getHallId(), show.getTime()).isEmpty();

        if (!available) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Show savedShow = showRepository.save(show);
        return new ResponseEntity<>(savedShow, HttpStatus.CREATED);
    }
}
