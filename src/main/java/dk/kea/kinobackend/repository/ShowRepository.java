package dk.kea.kinobackend.repository;

import dk.kea.kinobackend.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    List<Show> findByHallIdAndTime(int hallId, String time);
}
