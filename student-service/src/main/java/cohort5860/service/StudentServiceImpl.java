package cohort5860.service;

import cohort5860.dao.StudentRepository;
import cohort5860.model.Student;
import cohort5860.studentDto.ScoreDto;
import cohort5860.studentDto.StudentCredentialsDto;
import cohort5860.studentDto.StudentDto;
import cohort5860.studentDto.StudentUpdateDto;
import cohort5860.studentDto.exceptions.NotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
        if (studentRepository.findById(studentCredentialsDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(studentCredentialsDto.getId(), studentCredentialsDto.getName(), studentCredentialsDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundExeption::new);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundExeption::new);
        studentRepository.deleteById(id);

        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentCredentialsDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {

        Student student = studentRepository.findById(id).orElseThrow(NotFoundExeption::new);
        if (studentUpdateDto.getName() != null) {
            student.setName(studentUpdateDto.getName());
        }
        if (studentUpdateDto.getPassword() != null) {
            student.setPassword(studentUpdateDto.getPassword());
        }
        return new StudentCredentialsDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundExeption::new);


        return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getScores()))
                .collect(Collectors.toList());
    }

    @Override
    public Long countStudentsByName(Set<String> names) {


        return studentRepository.findAll().stream()
                .filter(student -> names.contains(student.getName()))
                .count();
    }

    @Override
    public List<StudentDto> findStudentsByExamNameMinScore(String exampleName, Integer minScore) {

        return studentRepository.findAll().stream()
                .filter(student -> student.getScores().containsKey(exampleName) && student.getScores().get(exampleName) >= minScore)
                .map(student -> new StudentDto(student.getId(),student.getName(),student.getScores()))
                .collect(Collectors.toList());
    }
}
