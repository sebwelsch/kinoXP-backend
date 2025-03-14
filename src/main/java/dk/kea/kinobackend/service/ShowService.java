package dk.kea.kinobackend.service;

import dk.kea.kinobackend.model.Show;
import dk.kea.kinobackend.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public List<String> getAvailableDates(int movieId) {
        return showRepository.findByMovieId(movieId).stream()
                .map(Show::getStartDate)
                .distinct()
                .collect(Collectors.toList());
    }


    public List<String> getAvailableTimes(int movieId, String date) {
        return showRepository.findByMovieIdAndStartDate(movieId, date).stream()
                .flatMap(show -> Stream.of(show.getTime().split(",")))
                .map(String::trim)  // Trim any extra spaces around times
                .collect(Collectors.toList());
    }



}
