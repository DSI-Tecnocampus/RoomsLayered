package cat.tecnocampus.rooms.application.daosInterface;

import cat.tecnocampus.rooms.application.dtos.ClassroomDTO;

import java.time.DayOfWeek;
import java.util.List;

public interface ClassroomDAO {

    public List<ClassroomDTO> getClassroomsAllocations();

    public List<ClassroomDTO> getClassroomsNoAllocations();

    public ClassroomDTO getClassroom(String name);

    public List<ClassroomDTO> getFullClassrooms(String dayofweek);

    public List<ClassroomDTO> getNotFullClassrooms(String dayofweek);

    public void allocateStudentInClassroom(String studentId, String className, String dayOfWeek);
}
