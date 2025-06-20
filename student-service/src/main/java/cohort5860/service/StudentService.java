package cohort5860.service;

import cohort5860.studentDto.ScoreDto;
import cohort5860.studentDto.StudentCredentialsDto;
import cohort5860.studentDto.StudentDto;
import cohort5860.studentDto.StudentUpdateDto;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Boolean addStudent (StudentCredentialsDto studentCredentialsDto);

    StudentDto findStudent(Long id);

    StudentDto removeStudent(Long id);

    StudentCredentialsDto updateStudent(Long id, StudentUpdateDto studentUpdateDto);

    Boolean addScore (Long id, ScoreDto scoreDto);

    List<StudentDto> findStudentsByName();

    Long countStudentsByName(Set<String> names);

    List<StudentDto> findStudentsByExamNameMinScore(String exampleName, Integer minScore);

}
