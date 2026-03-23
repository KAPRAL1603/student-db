package ru.db.student.dto;

public class DeleteStudentDTO {

    private String series;
    private String number;

    public DeleteStudentDTO() {
    }

    public DeleteStudentDTO(String series, String number) {
        this.series = series;
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}