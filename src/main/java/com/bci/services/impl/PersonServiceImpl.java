package com.bci.services.impl;

import com.bci.common.JsonWebToken;

import com.bci.controller.request.PersonRequest;
import com.bci.exception.EmailAlreadyExist;
import com.bci.model.Person;
import com.bci.repositories.PersonRepository;
import com.bci.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;
    private final JsonWebToken jsonWebToken;


    @Override
    public Person createPerson(PersonRequest personRequest) throws EmailAlreadyExist {

        Optional<Person> emailExists = personRepository.findByEmail(personRequest.getEmail());
        if (emailExists.isPresent()) {
            throw new EmailAlreadyExist("El correo electronico suministrado ya existe");
        }

        Person newPerson = new Person();
        newPerson.setEmail(personRequest.getEmail());
        newPerson.setPassword(passwordEncoder.encode(personRequest.getPassword()));
        newPerson.setName(personRequest.getName());
        newPerson.setCreated(LocalDate.now());
        newPerson.setUpdated(LocalDate.now());
        newPerson.setToken(jsonWebToken.generateToken(personRequest.getName()));
        newPerson.setActive(true);
        return personRepository.save(newPerson);
    }
}
