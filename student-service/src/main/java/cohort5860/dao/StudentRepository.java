package cohort5860.dao;

import cohort5860.model.Student;


import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    Optional<Student> findById(Long id);

    void deleteById(Long id);

    List<Student> findAll();


}
