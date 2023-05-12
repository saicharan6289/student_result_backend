package com.student.Result.Student;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentNameDto {
private String studentName;
private String email;
private Date dateOfBirth;
private Long id;
}
