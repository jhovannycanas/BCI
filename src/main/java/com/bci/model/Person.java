package com.bci.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "phones")
@EqualsAndHashCode(callSuper = true, exclude = "phones")
public class Person extends AbstractEntity {

  private String name;

  private String password;

  private LocalDate created;

  private LocalDate updated;

  private LocalDate lastLogin;

  @Email(regexp = "^[a-zA-Z0-9]{1,50}@[a-zA-Z0-9]{1,50}.cl$",
      message = "El formato de correo electonico no es el adecuado")
  private String email;

  private String token;

  private boolean isActive;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval = true)
  private Set<Phone> phones;

}
