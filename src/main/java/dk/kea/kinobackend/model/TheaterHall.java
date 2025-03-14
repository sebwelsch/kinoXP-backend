package dk.kea.kinobackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "theater_halls")
public class TheaterHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hall_id;
    private int cinema_id;
    private String name;
    private int seats;
    private int rows;

    public TheaterHall() {
    }

    public TheaterHall(int cinema_id, String name, int seats, int rows) {
        this.cinema_id = cinema_id;
        this.name = name;
        this.seats = seats;
        this.rows = rows;
    }

    public int getHall_id() {
        return hall_id;
    }

    public void setHall_id(int hall_id) {
        this.hall_id = hall_id;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
