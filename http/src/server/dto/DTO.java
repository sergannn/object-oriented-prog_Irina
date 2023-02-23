package server.dto;

import domain.models.Student;

import java.util.List;

public interface DTO<T> {
    void create(T object);
    boolean remove(int id);
    boolean update(int id, Student object);
    T get(int id);
    List<T> getAll();

}
