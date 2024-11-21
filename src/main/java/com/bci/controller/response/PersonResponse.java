package com.bci.controller.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse implements Serializable {

  private UUID id;

  private LocalDate created;

  private LocalDate modified;

  private LocalDate lastLogin;

  private String token;

  private boolean isActive;

}
