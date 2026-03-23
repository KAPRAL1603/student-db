package ru.db.student.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

    private static final String JDBC_URL = "jdbc:h2:./student-db-data/student_db";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void initDatabase() {
        createTables();
        seedFaculties();
    }

    private static void createTables() {
        String createFacultiesTable =
                "CREATE TABLE IF NOT EXISTS faculties (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL UNIQUE, " +
                        "phone_number VARCHAR(50) NOT NULL, " +
                        "email VARCHAR(255) NOT NULL" +
                        ")";

        String createStudentsTable =
                "CREATE TABLE IF NOT EXISTS students (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL, " +
                        "last_name VARCHAR(255) NOT NULL, " +
                        "series VARCHAR(20) NOT NULL, " +
                        "number VARCHAR(20) NOT NULL, " +
                        "birth_date DATE NOT NULL, " +
                        "birth_place VARCHAR(255) NOT NULL, " +
                        "phone_number VARCHAR(50) NOT NULL, " +
                        "email VARCHAR(255) NOT NULL, " +
                        "address VARCHAR(255) NOT NULL, " +
                        "faculty_id BIGINT NOT NULL, " +
                        "CONSTRAINT uk_students_passport UNIQUE (series, number), " +
                        "CONSTRAINT fk_students_faculty FOREIGN KEY (faculty_id) REFERENCES faculties(id)" +
                        ")";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(createFacultiesTable);
            statement.execute(createStudentsTable);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблиц базы данных", e);
        }
    }

    private static void seedFaculties() {
        insertFacultyIfNotExists("Математический", "+74733334546", "math@mail.ru");
        insertFacultyIfNotExists("Физический", "+74733331111", "fiz@mail.ru");
        insertFacultyIfNotExists("Экономический", "+74733330000", "econom@mail.ru");
    }

    private static void insertFacultyIfNotExists(String name, String phoneNumber, String email) {
        String sql =
                "MERGE INTO faculties (name, phone_number, email) " +
                        "KEY(name) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при заполнении таблицы faculties", e);
        }
    }
}