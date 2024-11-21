package com.bci.services;

import com.bci.controller.request.PersonRequest;
import com.bci.model.Person;

public interface PersonService {

  Person createPerson(PersonRequest personRequest);

}
