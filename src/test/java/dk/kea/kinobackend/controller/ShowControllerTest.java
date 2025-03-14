package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.Show;
import dk.kea.kinobackend.service.ShowService;
import dk.kea.kinobackend.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ShowController.class)
public class ShowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowService showService;

    @MockBean
    private ShowRepository showRepository;

    private Show show;

    @BeforeEach
    public void setUp() {
        show = new Show();
        show.setShow_id(1);
        show.setHallId(1);
        show.setMovieId(1);
        show.setTime("18:00");
        show.setStartDate("2025-03-15");
    }

    @Test
    public void testGetAllShows() throws Exception {
        List<Show> shows = Arrays.asList(show);

        when(showRepository.findAll()).thenReturn(shows);

        mockMvc.perform(get("/shows/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].show_id").value(show.getShow_id()))
                .andExpect(jsonPath("$[0].hallId").value(show.getHallId()))
                .andExpect(jsonPath("$[0].time").value(show.getTime()));
    }

    @Test
    public void testGetDates() throws Exception {
        List<String> dates = Arrays.asList("2025-03-15", "2025-03-16");

        when(showService.getAvailableDates(1)).thenReturn(dates);

        mockMvc.perform(get("/shows/dates").param("movie_id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value("2025-03-15"))
                .andExpect(jsonPath("$[1]").value("2025-03-16"));
    }

    @Test
    public void testGetTimes() throws Exception {
        List<String> times = Arrays.asList("18:00", "20:00");

        when(showService.getAvailableTimes(1, "2025-03-15")).thenReturn(times);

        mockMvc.perform(get("/shows/times")
                        .param("movie_id", "1")
                        .param("date", "2025-03-15"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value("18:00"))
                .andExpect(jsonPath("$[1]").value("20:00"));
    }

    @Test
    public void testCheckAvailability() throws Exception {
        when(showRepository.findByHallIdAndTime(1, "18:00")).thenReturn(List.of());

        mockMvc.perform(get("/shows/availability")
                        .param("hallId", "1")
                        .param("time", "18:00"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testCheckAvailabilityWhenNotAvailable() throws Exception {
        when(showRepository.findByHallIdAndTime(1, "18:00")).thenReturn(Arrays.asList(show));

        mockMvc.perform(get("/shows/availability")
                        .param("hallId", "1")
                        .param("time", "18:00"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testCreateShow() throws Exception {
        when(showRepository.findByHallIdAndTime(show.getHallId(), show.getTime())).thenReturn(List.of());
        when(showRepository.save(show)).thenReturn(show);

        mockMvc.perform(post("/shows/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hallId\": 1, \"movieId\": 1, \"time\": \"18:00\", \"startDate\": \"2025-03-15\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.show_id").value(show.getShow_id()))
                .andExpect(jsonPath("$.time").value(show.getTime()));
    }

    @Test
    public void testCreateShowWhenConflict() throws Exception {
        when(showRepository.findByHallIdAndTime(show.getHallId(), show.getTime())).thenReturn(Arrays.asList(show));

        mockMvc.perform(post("/shows/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hallId\": 1, \"movieId\": 1, \"time\": \"18:00\", \"startDate\": \"2025-03-15\"}"))
                .andExpect(status().isConflict());
    }
}
