package server.dto;

import domain.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO implements DTO<Student> {

    private List<Student> students;

    public StudentDTO() {
        students = new ArrayList<>();
    }

    public void initialize() {
        Student studentFirst = new Student("Ivan", "Ivanov", 20, 3432);
        Student studentSecond = new Student("Igor", "Jirkov", 28, 9542);
        Student studentThird = new Student("Imya", "Familiya", 19, 4352);
        create(studentFirst);
        create(studentSecond);
        create(studentThird);
    }

    @Override
    public void create(Student object) {
        object.setId(students.size() + 1);
        students.add(object);
    }

    @Override
    public boolean remove(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(int id, Student object) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id){
                students.set(i, object);
                return true;
            }
        }
        return false;
    }

    @Override
    public Student get(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id){
                return students.get(i);
            }
        }
        return null;

    }

    @Override
    public List<Student> getAll() {
        return students;
    }
}
