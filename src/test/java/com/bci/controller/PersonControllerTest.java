package com.bci.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bci.controller.request.PersonRequest;
import com.bci.controller.request.PhoneDTO;
import com.bci.model.Person;
import com.bci.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  @InjectMocks
  private PersonController personController;

  private PersonRequest personRequest;

  private Person person;

  @BeforeEach
  public void setUp() {

    PhoneDTO phone = new PhoneDTO("344", "567", "co");
    List<PhoneDTO> phones = new ArrayList<>();
    phones.add(phone);
    personRequest = new PersonRequest("Juan", "juan@example.cl", "Secure#9", phones);

    person = new Person();
    person.setToken("testToken");
    person.setCreated(LocalDate.now());
    person.setLastLogin(LocalDate.now());
    person.setActive(true);
  }

  @Test
  public void testCreatePerson() throws Exception {
    when(personService.createPerson(any(PersonRequest.class))).thenReturn(person);

    mockMvc.perform(post("/api/v1/bci/persons").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(personRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.token").value(
            person.getToken()))
        .andExpect(jsonPath("$.data.created").value(
            person.getCreated().toString()))
        .andExpect(jsonPath("$.data.active").value(
            person.isActive()));

    verify(personService, times(1)).createPerson(any(PersonRequest.class));
  }

  @Test
  public void testCreatePerson_Failure() throws Exception {
    when(personService.createPerson(any(PersonRequest.class))).thenThrow(
        new RuntimeException("Error al crear persona"));

    mockMvc.perform(post("/api/v1/bci/persons").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(personRequest))).andExpect(
        status().isInternalServerError());

    verify(personService, times(1)).createPerson(any(PersonRequest.class));
  }

}