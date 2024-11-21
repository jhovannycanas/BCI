package com.bci.services.impl;

import com.bci.common.Constants;
import com.bci.common.JsonWebToken;
import com.bci.controller.request.PersonRequest;
import com.bci.exception.EmailAlreadyExistsException;
import com.bci.exception.PersonBadRequestException;
import com.bci.model.Person;
import com.bci.model.Phone;
import com.bci.repositories.PersonRepository;
import com.bci.services.PersonService;
import java.time.LocalDate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  private final PasswordEncoder passwordEncoder;

  private final JsonWebToken jsonWebToken;

  @Override
  public Person createPerson(PersonRequest personRequest) {

    var emailExists = personRepository.findByEmail(personRequest.getEmail());
    if (emailExists.isPresent()) {
      throw new EmailAlreadyExistsException(Constants.EMAIL_ALREADY_EXISTS);
    }

    try {
      Person newPerson = new Person();
      newPerson.setEmail(personRequest.getEmail());
      newPerson.setPassword(passwordEncoder.encode(personRequest.getPassword()));
      newPerson.setName(personRequest.getName());
      newPerson.setCreated(LocalDate.now());
      newPerson.setUpdated(LocalDate.now());
      newPerson.setToken(jsonWebToken.generateToken(personRequest.getName()));
      newPerson.setLastLogin(LocalDate.now());
      newPerson.setActive(true);
      newPerson.setPhones(personRequest.getPhones().stream().map(phone -> {
        Phone newPhone = Phone.builder().number(phone.getNumber()).cityCode(phone.getCityCode())
            .contryCode(phone.getContryCode()).build();
        newPhone.setPerson(newPerson);
        return newPhone;
      }).collect(Collectors.toSet()));
      return personRepository.save(newPerson);

    } catch (Exception exc) {
      throw new PersonBadRequestException(Constants.ERROR_RESPONSE + exc.getMessage());
    }
  }

}
