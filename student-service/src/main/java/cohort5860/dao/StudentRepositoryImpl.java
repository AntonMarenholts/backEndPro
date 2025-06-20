package cohort5860.dao;

import cohort5860.model.Student;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StudentRepositoryImpl implements StudentRepository {

    private Map<Long,Student> students = new ConcurrentHashMap<>();


    @Override
    public Student save(Student student) {
        students.put(student.getId(),student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public void deleteById(Long id) {

        students.remove(id);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}
