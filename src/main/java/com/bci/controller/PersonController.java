package com.bci.controller;

import com.bci.common.Constants;
import com.bci.controller.request.PersonRequest;
import com.bci.controller.response.BasicResponse;
import com.bci.controller.response.PersonResponse;
import com.bci.model.Person;
import com.bci.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/bci/persons")
public class PersonController {

  private final PersonService personService;

  @PostMapping
  public ResponseEntity<BasicResponse<PersonResponse>> createPerson(
      @Valid @RequestBody PersonRequest personRequest) {

    Person person = personService.createPerson(personRequest);
    return new ResponseEntity<>(new BasicResponse<>(Constants.SUCCESS_RESPONSE,
        PersonResponse.builder().id(person.getId()).token(person.getToken())
            .created(person.getCreated()).lastLogin(person.getLastLogin())
            .isActive(person.isActive()).build()), HttpStatus.CREATED);
  }

}
