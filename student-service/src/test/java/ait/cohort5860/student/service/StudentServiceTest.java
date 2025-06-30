package ait.cohort5860.student.service;

import ait.cohort5860.student.dao.StudentRepository;
import ait.cohort5860.student.model.Student;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class StudentServiceTest {
    private final long studentId = 1000L;
    private final String name = "John";
    private final String password = "1234";
    private Student student;



    private StudentService studentService;

    @MockitoBean
    private StudentRepository studentRepository;

    public void setUp(){
        student = new Student(studentId,name,password);
        studentService = new StudentServiceImpl(studentRepository);
    }

}
