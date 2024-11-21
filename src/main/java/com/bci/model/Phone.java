package com.bci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone extends AbstractEntity {

    private String number;
    private String cityCode;
    private String contryCode;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
