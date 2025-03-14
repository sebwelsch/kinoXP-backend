package dk.kea.kinobackend.controller;

import dk.kea.kinobackend.model.Booking;
import dk.kea.kinobackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/is-full")
    public boolean isShowFullyBooked(@RequestParam int showId, @RequestParam String date, @RequestParam String time) {
        return bookingService.isShowFullyBooked(showId, date, time);
    }

    @PostMapping("/book")
    public ResponseEntity<Booking> bookSeats(@RequestBody Booking newBooking) {
        // check if seats are available
        if (bookingService.isShowFullyBooked(newBooking.getShowId(), newBooking.getDate(), newBooking.getTime())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Booking booking = bookingService.bookSeats(
                newBooking.getShowId(),
                newBooking.getDate(),
                newBooking.getTime(),
                newBooking.getSeats(),
                newBooking.getCustomer_name(),
                newBooking.getCustomer_email()
        );

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        boolean deleted = bookingService.deleteBooking(id);
        if (deleted) {
            return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
    }
}
