package dk.kea.kinobackend.repository;

import dk.kea.kinobackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByShowIdAndDateAndTime(int show_id, String date, String time);
}
