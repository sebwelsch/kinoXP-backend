package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.Booking;
import dk.kea.kinobackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @GetMapping("/is-full")
    public boolean isShowFullyBooked(@RequestParam int showId, @RequestParam String date, @RequestParam String time) {
        return bookingService.isShowFullyBooked(showId, date, time);
    }


    @PostMapping("/book")
    public ResponseEntity<Booking> bookSeats(@RequestParam int showId, @RequestParam String date,
                                             @RequestParam String time, @RequestParam int seatsToBook,
                                             @RequestParam String customerName, @RequestParam String customerEmail) {
        if (bookingService.isShowFullyBooked(showId, date, time)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Booking booking = bookingService.bookSeats(showId, date, time, seatsToBook, customerName, customerEmail);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
}
