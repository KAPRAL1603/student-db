package ru.db.student.service;

import ru.db.student.model.Student;

import java.time.LocalDate;

public interface StudentService {

    String getStudent(String series, String number);

    boolean deleteStudent(String series, String number);

    boolean saveStudent(String name, String lastName, String series, String number,
                        LocalDate birthDate, String birthPlace, String facultyName,
                        String phoneNumber, String email, String address);


}
