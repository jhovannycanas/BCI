package com.bci.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{

    private String name;
    private String password;
    private LocalDate created;
    private LocalDate updated;
    private LocalDate lastLogin;
    @Email(regexp = "^[a-zA-Z0-9]{1,50}@[a-zA-Z0-9]{1,50}.cl$", message = "El formato de correo electonico no es el adecuado")
    private String email;
    private String token;
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Phone> phones;
}
