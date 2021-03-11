package com.fullstack.oblig2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
public class Author {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int birthdate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany
    @JsonIgnore
    private List<Book> books;

}
