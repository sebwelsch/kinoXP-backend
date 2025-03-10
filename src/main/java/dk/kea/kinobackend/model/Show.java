package dk.kea.kinobackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int show_id;
    private int movie_id;
    private int hall_id;
    private String start_date;
    private String end_date;
    private String time;

    public Show() {}

    public Show(int movie_id, int hall_id, String start_date, String end_date, String time) {
        this.movie_id = movie_id;
        this.hall_id = hall_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.time = time;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getHall_id() {
        return hall_id;
    }

    public void setHall_id(int hall_id) {
        this.hall_id = hall_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
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
