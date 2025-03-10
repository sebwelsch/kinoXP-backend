package dk.kea.kinobackend.repository;

import dk.kea.kinobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
