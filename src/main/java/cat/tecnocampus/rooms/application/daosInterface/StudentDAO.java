package cat.tecnocampus.rooms.application.daosInterface;

import cat.tecnocampus.rooms.application.dtos.StudentDTO;

import java.util.List;

public interface StudentDAO {
    public List<StudentDTO> getStudents();

    public StudentDTO getStudent(String id);

    public StudentDTO addStudent(StudentDTO studentDTO);

    public StudentDTO getStudentByName(String name);
}
