package com.fullstack.oblig2.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
public class Address {

    @Id @GeneratedValue
    private Long id;
    private String street;
    private Integer apartmentNumber;

}
