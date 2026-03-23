package ru.db.student.model;

import java.time.LocalDate;
import java.util.Objects;

public class Passport {

    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String birthPlace;
    private String series;
    private String number;

    public Passport() {
    }

    public Passport(String name, String lastName, LocalDate birthDate, String birthPlace, String series, String number) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.series = series;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;
        return Objects.equals(name, passport.name)
                && Objects.equals(lastName, passport.lastName)
                && Objects.equals(birthDate, passport.birthDate)
                && Objects.equals(birthPlace, passport.birthPlace)
                && Objects.equals(series, passport.series)
                && Objects.equals(number, passport.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, birthDate, birthPlace, series, number);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}