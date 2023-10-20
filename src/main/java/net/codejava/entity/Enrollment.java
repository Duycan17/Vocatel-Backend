package net.codejava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JsonIgnore
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @Column(nullable = false)
    private int score;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    public Enrollment() {
        this.date = new Date();
    }

}