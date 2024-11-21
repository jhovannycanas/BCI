package com.bci.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bci.common.Constants;
import com.bci.common.JsonWebToken;
import com.bci.controller.request.PersonRequest;
import com.bci.controller.request.PhoneDTO;
import com.bci.exception.EmailAlreadyExistsException;
import com.bci.exception.PersonBadRequestException;
import com.bci.model.Person;
import com.bci.model.Phone;
import com.bci.repositories.PersonRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PersonServiceImplTest {

  @InjectMocks
  private PersonServiceImpl personService;

  @MockBean
  private PersonRepository personRepository;

  @MockBean
  private JsonWebToken jsonWebToken;

  @MockBean
  private PasswordEncoder passwordEncoder;

  private PersonRequest personRequest;

  private Person person;

  @BeforeEach
  public void setUp() {
    PhoneDTO phone = new PhoneDTO("344", "567", "co");
    List<PhoneDTO> phones = new ArrayList<>();
    phones.add(phone);
    personRequest = new PersonRequest("Juan", "juan@example.cl", "Secure#9", phones);

    person = new Person();
    person.setEmail(personRequest.getEmail());
    person.setPassword("Secure#9");
    person.setName(personRequest.getName());
    person.setCreated(LocalDate.now());
    person.setUpdated(LocalDate.now());
    person.setToken("TestToken");
    person.setActive(true);
    person.setPhones(Set.of(new Phone("1234567", "1", "57", person)));

    personService = new PersonServiceImpl(personRepository, passwordEncoder, jsonWebToken);

  }

  @Test
  public void testCreatePerson_Success() {

    when(personRepository.findByEmail(personRequest.getEmail())).thenReturn(Optional.empty());
    when(personRepository.save(any(Person.class))).thenReturn(person);

    Person createdPerson = personService.createPerson(personRequest);

    verify(personRepository, times(1)).save(any(Person.class));

    assertNotNull(createdPerson);
    assertEquals(person.getEmail(), createdPerson.getEmail());
    assertEquals(person.getName(), createdPerson.getName());
  }

  @Test
  public void testCreatePerson_EmailAlreadyExists() {
    when(personRepository.findByEmail(personRequest.getEmail())).thenReturn(Optional.of(person));

    EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
      personService.createPerson(personRequest);
    });

    assertEquals(Constants.EMAIL_ALREADY_EXISTS, exception.getMessage()); // O el mensaje adecuado
  }

  @Test
  public void testCreatePerson_Error() {
    when(personRepository.findByEmail(personRequest.getEmail())).thenReturn(Optional.empty());
    when(personRepository.save(any(Person.class))).thenThrow(
        new RuntimeException("Error al guardar la persona"));

    PersonBadRequestException exception = assertThrows(PersonBadRequestException.class, () -> {
      personService.createPerson(personRequest);
    });
    assertTrue(exception.getMessage().contains("Error al guardar la persona"));
  }

}