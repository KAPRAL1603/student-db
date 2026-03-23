package ru.db.student.view;

import ru.db.student.controller.StudentController;
import ru.db.student.dto.DeleteStudentDTO;
import ru.db.student.dto.GetStudentDTO;
import ru.db.student.dto.SaveStudentDTO;

import java.time.LocalDate;
import java.util.Scanner;

import static ru.db.student.util.DateUtil.formatStringToDate;

public class StudentView {

    private static final StudentController studentController = new StudentController();
    private static final Scanner SCANNER = new Scanner(System.in);

    private StudentView() {
    }

    public static void runInterface() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = SCANNER.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println(getStudent());
                    break;
                case "2":
                    boolean isDeleted = deleteStudent();
                    if (isDeleted) {
                        System.out.println("Студент успешно удалён.");
                    } else {
                        System.out.println("Студент не найден или удалить его не удалось.");
                    }
                    break;
                case "3":
                    boolean isSaved = saveStudent();
                    if (isSaved) {
                        System.out.println("Студент успешно добавлен.");
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Введите корректный пункт меню.");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("--------------------------------------");
        System.out.println("1. Найти студента");
        System.out.println("2. Удалить студента");
        System.out.println("3. Добавить студента");
        System.out.println("0. Выход");
        System.out.print("Выбери пункт меню: ");
    }

    private static boolean saveStudent() {
        try {
            System.out.print("Введите имя: ");
            String name = SCANNER.nextLine().trim();

            System.out.print("Введите фамилию: ");
            String lastName = SCANNER.nextLine().trim();

            System.out.print("Введите серию паспорта: ");
            String series = SCANNER.nextLine().trim();

            System.out.print("Введите номер паспорта: ");
            String number = SCANNER.nextLine().trim();

            System.out.print("Введите дату рождения (dd.MM.yyyy): ");
            String birthDateString = SCANNER.nextLine().trim();
            LocalDate birthDate = formatStringToDate(birthDateString);

            System.out.print("Введите место рождения: ");
            String birthPlace = SCANNER.nextLine().trim();

            System.out.println("Доступные факультеты:");
            System.out.println("- Математический");
            System.out.println("- Физический");
            System.out.println("- Экономический");
            System.out.print("Введите факультет: ");
            String facultyName = SCANNER.nextLine().trim();

            System.out.print("Введите номер телефона: ");
            String phoneNumber = SCANNER.nextLine().trim();

            System.out.print("Введите email: ");
            String email = SCANNER.nextLine().trim();

            System.out.print("Введите адрес: ");
            String address = SCANNER.nextLine().trim();

            SaveStudentDTO saveStudentDTO = new SaveStudentDTO(
                    name,
                    lastName,
                    series,
                    number,
                    birthDate,
                    birthPlace,
                    facultyName,
                    phoneNumber,
                    email,
                    address
            );

            return studentController.saveStudent(saveStudentDTO);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
            return false;
        }
    }

    private static boolean deleteStudent() {
        System.out.print("Введите серию паспорта: ");
        String series = SCANNER.nextLine().trim();

        System.out.print("Введите номер паспорта: ");
        String number = SCANNER.nextLine().trim();

        DeleteStudentDTO deleteStudentDTO = new DeleteStudentDTO(series, number);
        return studentController.deleteStudent(deleteStudentDTO);
    }

    private static String getStudent() {
        System.out.print("Введите серию паспорта: ");
        String series = SCANNER.nextLine().trim();

        System.out.print("Введите номер паспорта: ");
        String number = SCANNER.nextLine().trim();

        GetStudentDTO getStudentDTO = new GetStudentDTO(series, number);
        return studentController.getStudent(getStudentDTO);
    }
}