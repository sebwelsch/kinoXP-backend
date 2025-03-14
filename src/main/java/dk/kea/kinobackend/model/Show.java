package dk.kea.kinobackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int show_id;
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "hall_id")
    private int hallId;
    @Column(name = "start_date")
    private String startDate;
    private String end_date;
    private String time;

    public Show() {
    }

    public Show(int movieId, int hallId, String startDate, String end_date, String time) {
        this.movieId = movieId;
        this.hallId = hallId;
        this.startDate = startDate;
        this.end_date = end_date;
        this.time = time;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
