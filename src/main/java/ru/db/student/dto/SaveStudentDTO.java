package ru.db.student.dto;

import java.time.LocalDate;

public class SaveStudentDTO {

    private String name;
    private String lastName;
    private String series;
    private String number;
    private LocalDate birthDate;
    private String birthPlace;
    private String facultyName;
    private String phoneNumber;
    private String email;
    private String address;

    public SaveStudentDTO() {
    }

    public SaveStudentDTO(String name,
                          String lastName,
                          String series,
                          String number,
                          LocalDate birthDate,
                          String birthPlace,
                          String facultyName,
                          String phoneNumber,
                          String email,
                          String address) {
        this.name = name;
        this.lastName = lastName;
        this.series = series;
        this.number = number;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.facultyName = facultyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}