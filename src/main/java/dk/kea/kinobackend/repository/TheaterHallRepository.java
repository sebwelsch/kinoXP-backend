package dk.kea.kinobackend.repository;

import dk.kea.kinobackend.model.TheaterHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterHallRepository extends JpaRepository<TheaterHall, Integer> {
}
