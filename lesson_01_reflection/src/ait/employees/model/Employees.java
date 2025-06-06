package ait.employees.model;

import ait.utils.Table;

import java.time.LocalDate;
import java.util.Objects;


@Table(name = "employees")
public class Employees {

    private int id;
    private String name;
    private int salary;
    private LocalDate bithDay;
    private String email;

    public Employees(int id, String name, int salary, LocalDate bithDay, String email) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.bithDay = bithDay;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getBithDay() {
        return bithDay;
    }

    public void setBithDay(LocalDate bithDay) {
        this.bithDay = bithDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", bithDay=" + bithDay +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employees employees)) return false;
        return id == employees.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

