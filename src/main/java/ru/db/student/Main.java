package ru.db.student;

import ru.db.student.database.Database;
import ru.db.student.view.StudentView;

public class Main {

    public static void main(String[] args) {
        Database.initDatabase();
        StudentView.runInterface();
    }
}