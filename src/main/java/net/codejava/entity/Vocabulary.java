package net.codejava.entity;

import javax.persistence.*;

@Entity
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String word;
    @Column(nullable = false)

    private String definition;
    @Column(nullable = false)

    private String example;
    @Column(name = "is_rememeber")

    private boolean isRemember = false;
}
