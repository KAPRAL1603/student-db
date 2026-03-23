package ru.db.student.controller;

import ru.db.student.dto.DeleteStudentDTO;
import ru.db.student.dto.GetStudentDTO;
import ru.db.student.dto.SaveStudentDTO;
import ru.db.student.service.StudentService;
import ru.db.student.service.impl.StudentServiceImpl;

public class StudentController {

    private final StudentService studentService = new StudentServiceImpl();

    public String getStudent(GetStudentDTO studentDTO) {
        return studentService.getStudent(studentDTO.getSeries(), studentDTO.getNumber());
    }

    public boolean deleteStudent(DeleteStudentDTO studentDTO) {
        return studentService.deleteStudent(studentDTO.getSeries(), studentDTO.getNumber());
    }

    public boolean saveStudent(SaveStudentDTO saveStudentDTO) {
        return studentService.saveStudent(
                saveStudentDTO.getName(),
                saveStudentDTO.getLastName(),
                saveStudentDTO.getSeries(),
                saveStudentDTO.getNumber(),
                saveStudentDTO.getBirthDate(),
                saveStudentDTO.getBirthPlace(),
                saveStudentDTO.getFacultyName(),
                saveStudentDTO.getPhoneNumber(),
                saveStudentDTO.getEmail(),
                saveStudentDTO.getAddress()
        );
    }
}