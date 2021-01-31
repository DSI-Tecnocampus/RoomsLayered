package cat.tecnocampus.rooms.application;

import cat.tecnocampus.rooms.application.daosInterface.ClassroomDAO;
import cat.tecnocampus.rooms.application.daosInterface.StudentDAO;
import cat.tecnocampus.rooms.application.dtos.AllocationDTO;
import cat.tecnocampus.rooms.application.dtos.ClassroomDTO;
import cat.tecnocampus.rooms.application.dtos.StudentDTO;
import cat.tecnocampus.rooms.domain.Allocation;
import cat.tecnocampus.rooms.domain.Classroom;
import cat.tecnocampus.rooms.domain.Student;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomController {
    StudentDAO studentDAO;
    ClassroomDAO classroomDAO;

    public RoomController(StudentDAO studentDAO, ClassroomDAO classroomDAO) {
        this.studentDAO = studentDAO;
        this.classroomDAO = classroomDAO;
    }

    public List<StudentDTO> getStudents() {
        return studentDAO.getStudents();
    }

    public StudentDTO getStudent(String id) {
        return studentDAO.getStudent(id);
    }

    public StudentDTO getStudentByNameEager(String name) {
        return studentDAO.getStudentByName(name);
    }

    public List<ClassroomDTO> getClassrooms() {
        return classroomDAO.getClassroomsAllocations();
    }

    public ClassroomDTO getClassroom(String name) {
        return classroomDAO.getClassroom(name);
    }

    public List<ClassroomDTO> getClassroomsNoAllocations() {
        return classroomDAO.getClassroomsNoAllocations();
    }

    public List<ClassroomDTO> getFullyOccupiedClassrooms(String dayOfWeek) {
        return classroomDAO.getFullClassrooms(dayOfWeek);
/*
        return getClassrooms().stream()
                .filter(c -> c.isFull(DayOfWeek.valueOf(dayOfWeek.toUpperCase())))
                .map(this::classroom2classroomDTO)
                .collect(Collectors.toList());
*/
    }

    public List<ClassroomDTO> getNotFullyOccupiedClassrooms(String dayOfWeek) {
        return classroomDAO.getNotFullClassrooms(dayOfWeek);
/*
        return getClassrooms().stream()
                .filter(c -> !c.isFull(DayOfWeek.valueOf(dayOfWeek.toUpperCase())))
                .map(this::classroom2classroomDTO)
                .collect(Collectors.toList());
*/
    }

    public void setNewStudent(StudentDTO studentDTO) {
        studentDAO.addStudent(studentDTO);
    }

    public void allocateStudentInClassroom(StudentDTO student, String classroomName, String dayOfWeek) {
        Classroom classroom = classroomDTO2classroom(classroomDAO.getClassroom(classroomName));
        classroom.allocate(studentDTO2Student(student), DayOfWeek.valueOf(dayOfWeek.toUpperCase()));
        classroomDAO.allocateStudentInClassroom(student.getId(), classroomName, dayOfWeek.toUpperCase());
    }

    /********************************************************************
     * Translations between DTOs and domain objects
     ********************************************************************/

    private StudentDTO student2StudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setName(student.getName());
        studentDTO.setSecondName(student.getSecondName());

        return studentDTO;
    }

    private Student studentDTO2Student(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setEmail(studentDTO.getEmail());
        student.setName(studentDTO.getName());
        student.setSecondName(studentDTO.getSecondName());

        return student;
    }

    private ClassroomDTO classroom2classroomDTOnoAllocations(Classroom classroom) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setName(classroom.getName());
        classroomDTO.setCapacity(classroom.getCapacity());
        classroomDTO.setOrientation(classroom.getOrientation());
        classroomDTO.setPlugs(classroom.isPlugs());

        return classroomDTO;
    }

    private ClassroomDTO classroom2classroomDTO(Classroom classroom) {
        ClassroomDTO classroomDTO = classroom2classroomDTOnoAllocations(classroom);
        classroomDTO.setAllocations(classroomGetAllocationsDTO(classroom));

        return classroomDTO;
    }

    private Classroom classroomDTO2classroom(ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();

        classroom.setName(classroomDTO.getName());
        classroom.setCapacity(classroomDTO.getCapacity());
        classroom.setPlugs(classroomDTO.isPlugs());
        classroom.setOrientation(classroomDTO.getOrientation());
        classroomDTO.getAllocations().stream()
                .forEach(a -> classroom.allocate(studentDTO2Student(a.getStudent()), DayOfWeek.valueOf(a.getDayOfWeek())));
        return classroom;
    }

    private List<AllocationDTO> classroomGetAllocationsDTO(Classroom classroom) {
        return classroom.getAllocations().stream().map(this::allocation2AllocationDTO).collect(Collectors.toList());
    }

    private AllocationDTO allocation2AllocationDTO(Allocation allocation) {
        AllocationDTO allocationDTO = new AllocationDTO();
        allocationDTO.setStudent(student2StudentDTO(allocation.getStudent()));
        allocationDTO.setDayOfWeek(allocation.getDayOfWeek().name());

        return allocationDTO;
    }
}
