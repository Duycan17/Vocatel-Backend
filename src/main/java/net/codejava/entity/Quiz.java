package net.codejava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

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
    @OneToMany(mappedBy = "quiz",cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private Set<Enrollment> enrollments =new HashSet<>();
    public Quiz() {
        this.creationDate = new Date();
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, title, difficultyLevel, creationDate);
    }

}