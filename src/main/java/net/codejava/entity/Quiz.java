package net.codejava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
@Data
@AllArgsConstructor
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "difficulty_level", nullable = false)
    private String difficultyLevel;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @OneToMany(mappedBy = "quiz")
    private Set<Enrollment> enrollments = new HashSet<Enrollment>();
    public Quiz() {
        this.creationDate = new Date();
    }

}