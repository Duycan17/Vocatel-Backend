package net.codejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codejava.entity.Quiz;
import net.codejava.entity.User;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {
    private Long userId;
    private Long quizId;
    private int score;
}
