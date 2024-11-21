package com.bci.repositories;

import com.bci.model.Person;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID> {

  Optional<Person> findByEmail(String email);

}
