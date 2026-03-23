package ru.db.student.model;

public class Student {

    private String phoneNumber;
    private String email;
    private String address;
    private Faculty faculty;

    public Student() {
    }

    public Student(String phoneNumber, String email, String address, Faculty faculty) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.faculty = faculty;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}