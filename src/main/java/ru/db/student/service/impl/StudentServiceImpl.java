package ru.db.student.service.impl;

import ru.db.student.database.Database;
import ru.db.student.service.StudentService;

import java.sql.*;
import java.time.LocalDate;

public class StudentServiceImpl implements StudentService {

    @Override
    public String getStudent(String series, String number) {
        String sql =
                "SELECT s.name, s.last_name, s.series, s.number, s.birth_date, s.birth_place, " +
                        "s.phone_number, s.email, s.address, f.name AS faculty_name, " +
                        "f.phone_number AS faculty_phone, f.email AS faculty_email " +
                        "FROM students s " +
                        "JOIN faculties f ON s.faculty_id = f.id " +
                        "WHERE s.series = ? AND s.number = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, series);
            ps.setString(2, number);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return "Студент не найден.";
                }

                return "Студент найден:\n" +
                        "Имя: " + rs.getString("name") + "\n" +
                        "Фамилия: " + rs.getString("last_name") + "\n" +
                        "Паспорт: " + rs.getString("series") + " " + rs.getString("number") + "\n" +
                        "Дата рождения: " + rs.getDate("birth_date") + "\n" +
                        "Место рождения: " + rs.getString("birth_place") + "\n" +
                        "Телефон: " + rs.getString("phone_number") + "\n" +
                        "Email: " + rs.getString("email") + "\n" +
                        "Адрес: " + rs.getString("address") + "\n" +
                        "Факультет: " + rs.getString("faculty_name");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении студента", e);
        }
    }

    @Override
    public boolean deleteStudent(String series, String number) {
        String sql = "DELETE FROM students WHERE series = ? AND number = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, series);
            ps.setString(2, number);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Студент не найден.");
                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении студента", e);
        }
    }

    @Override
    public boolean saveStudent(String name, String lastName, String series, String number,
                               LocalDate birthDate, String birthPlace, String facultyName,
                               String phoneNumber, String email, String address) {

        if (studentExists(series, number)) {
            System.out.println("Студент с таким паспортом уже существует.");
            return false;
        }

        Long facultyId = findFacultyIdByName(facultyName);

        if (facultyId == null) {
            System.out.println("Факультет не найден.");
            return false;
        }

        String sql =
                "INSERT INTO students (" +
                        "name, last_name, series, number, birth_date, birth_place, " +
                        "phone_number, email, address, faculty_id" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setString(3, series);
            ps.setString(4, number);
            ps.setDate(5, Date.valueOf(birthDate));
            ps.setString(6, birthPlace);
            ps.setString(7, phoneNumber);
            ps.setString(8, email);
            ps.setString(9, address);
            ps.setLong(10, facultyId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {

            if (e.getMessage() != null && e.getMessage().contains("uk_students_passport")) {
                System.out.println("Студент с таким паспортом уже существует (ограничение БД).");
                return false;
            }

            throw new RuntimeException("Ошибка при сохранении студента", e);
        }
    }

    private boolean studentExists(String series, String number) {
        String sql = "SELECT 1 FROM students WHERE series = ? AND number = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, series);
            ps.setString(2, number);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка проверки существования студента", e);
        }
    }

    private Long findFacultyIdByName(String facultyName) {
        String sql = "SELECT id FROM faculties WHERE name = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, facultyName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("id");
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске факультета", e);
        }
    }
}