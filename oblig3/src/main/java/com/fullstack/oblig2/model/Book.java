package com.fullstack.oblig2.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private int pages;

    @ManyToMany
    private List<Author> authors;

}
