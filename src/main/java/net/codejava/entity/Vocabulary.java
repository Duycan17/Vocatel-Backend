package net.codejava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(nullable = false)
    private String type;

    @Column()
    private String pronounce;

    @Column
    private String imageURL;
    @Column(name = "is_rememeber")

    private boolean isRemember = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
