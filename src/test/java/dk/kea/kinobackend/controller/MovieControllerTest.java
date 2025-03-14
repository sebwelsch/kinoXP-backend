package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.Movie;
import dk.kea.kinobackend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MovieControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    void testCreateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setMovie_id(1);
        movie.setName("Test Movie");
        movie.setDescription("Test Description");

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/movies/add")
                        .contentType("application/json")
                        .content("{\"name\":\"Test Movie\",\"description\":\"Test Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.movie_id").value(1))
                .andExpect(jsonPath("$.name").value("Test Movie"))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void testDeleteMovie_MovieExists() throws Exception {
        when(movieRepository.existsById(1)).thenReturn(true);

        mockMvc.perform(post("/movies/delete")
                        .param("movieId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Movie deleted successfully"));

        verify(movieRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteMovie_MovieNotFound() throws Exception {
        when(movieRepository.existsById(1)).thenReturn(false);

        mockMvc.perform(post("/movies/delete")
                        .param("movieId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Movie not found"));

        verify(movieRepository, times(0)).deleteById(1);
    }

    @Test
    void testGetAllMovies() throws Exception {
        mockMvc.perform(get("/movies/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void testGetCategories() throws Exception {
        mockMvc.perform(get("/movies/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
