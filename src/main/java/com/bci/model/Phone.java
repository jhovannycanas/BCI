package com.bci.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(callSuper = true, exclude = "user")
public class Phone extends AbstractEntity {

  private String number;

  @Column(name = "city_code")
  private String cityCode;

  @Column(name = "contry_code")
  private String contryCode;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "person_id")
  private Person person;

}
