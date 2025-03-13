package dk.kea.kinobackend.service;

import dk.kea.kinobackend.model.Booking;
import dk.kea.kinobackend.model.Show;
import dk.kea.kinobackend.model.TheaterHall;
import dk.kea.kinobackend.repository.BookingRepository;
import dk.kea.kinobackend.repository.ShowRepository;
import dk.kea.kinobackend.repository.TheaterHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterHallRepository theaterHallRepository;


    public boolean isShowFullyBooked(int showId, String date, String time) {
        Optional<Booking> booking = bookingRepository.findByShowIdAndDateAndTime(showId, date, time);
        Optional<Show> show = showRepository.findById(showId);

        if (booking.isPresent() && show.isPresent()) {
            Optional<TheaterHall> hall = theaterHallRepository.findById(show.get().getHall_id());
            return hall.isPresent() && booking.get().getSeats() >= hall.get().getSeats();
        }
        return false;
    }


    public Booking bookSeats(int showId, String date, String time, int seatsToBook, String customerName, String customerEmail) {
        Optional<Booking> existingBooking = bookingRepository.findByShowIdAndDateAndTime(showId, date, time);

        if (existingBooking.isPresent()) {
            Booking booking = existingBooking.get();
            booking.setSeats(booking.getSeats() + seatsToBook);
            return bookingRepository.save(booking);
        } else {
            Booking newBooking = new Booking(showId, time, seatsToBook, date, customerName, customerEmail);
            return bookingRepository.save(newBooking);
        }
    }
}
