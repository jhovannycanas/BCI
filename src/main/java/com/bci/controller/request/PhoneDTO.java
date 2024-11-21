package com.bci.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneDTO {

  @NotBlank(message = "El número no puede estar vacío")
  private String number;

  @NotBlank(message = "El código de ciudad no puede estar vacío")
  private String cityCode;

  @NotBlank(message = "El código de país no puede estar vacío")
  private String contryCode;

}
