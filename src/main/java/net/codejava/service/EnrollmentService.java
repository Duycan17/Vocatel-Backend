package net.codejava.service;

import net.codejava.dto.EnrollmentDto;
import net.codejava.entity.Enrollment;

public interface EnrollmentService {
    Enrollment save(EnrollmentDto enrollmentDto);

}
