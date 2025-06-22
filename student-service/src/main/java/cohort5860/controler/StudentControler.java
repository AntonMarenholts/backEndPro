package cohort5860.controler;

import cohort5860.service.StudentService;
import cohort5860.studentDto.ScoreDto;
import cohort5860.studentDto.StudentCredentialsDto;
import cohort5860.studentDto.StudentDto;
import cohort5860.studentDto.StudentUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentControler {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentCredentialsDto studentCredentialsDto) {
        return studentService.addStudent(studentCredentialsDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(Long id) {
        return studentService.removeStudent(id);
    }

    @PatchMapping("/student/{id}")
    public StudentCredentialsDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PatchMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Long id, ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/student/name/{name}")
    public List<StudentDto> findStudentsByName() {
        return studentService.findStudentsByName();
    }

    @GetMapping("/quantity/students")
    public Long countStudentsByName(Set<String> names) {
        return studentService.countStudentsByName(names);
    }

    @GetMapping("/students/exam/{examName}/minscore/{minScore}")
    public List<StudentDto> findStudentsByExamNameMinScore(String exampleName, Integer minScore) {
        return studentService.findStudentsByExamNameMinScore(exampleName, minScore);
    }
}
