package com.bci.controller.request;

import com.bci.common.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PersonRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El correo no puede estar vacío")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{1,50}@[a-zA-Z0-9]{1,50}\\.cl$",
            message = "El correo debe tener un usuario y dominio alfanuméricos (máx. 50 caracteres) y terminar con .cl"
    )
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @ValidPassword
    private String password;

    @NotNull(message = "La lista de teléfonos no puede ser nula")
    @Size(min = 1, message = "Debe incluir al menos un teléfono")
    private List<PhoneDTO> phones;

    public static class PhoneDTO {
        @NotBlank(message = "El número no puede estar vacío")
        private String number;

        @NotBlank(message = "El código de ciudad no puede estar vacío")
        private String cityCode;

        @NotBlank(message = "El código de país no puede estar vacío")
        private String contryCode;
    }
}
