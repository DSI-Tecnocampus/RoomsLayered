package cat.tecnocampus.rooms.api;

import cat.tecnocampus.rooms.application.RoomController;
import cat.tecnocampus.rooms.application.WeatherController;
import cat.tecnocampus.rooms.application.dtos.ClassroomDTO;
import cat.tecnocampus.rooms.application.dtos.StudentDTO;
import cat.tecnocampus.rooms.application.dtos.WeatherDTO;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.security.Principal;
import java.util.List;

@Validated
@RestController
public class roomsRESTController {
    RoomController roomController;
    WeatherController weatherController;

    public roomsRESTController(RoomController roomController, WeatherController weatherController) {
        this.roomController = roomController;
        this.weatherController = weatherController;
    }

    @GetMapping("/students")
    public List<StudentDTO> getStudents() {
        return roomController.getStudents();
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudent(@PathVariable String id) {
        return roomController.getStudent(id);
    }

    @GetMapping("students/me")
    public StudentDTO getMyProfile(Principal principal) {
        return roomController.getStudentByNameEager(principal.getName());
    }

    @GetMapping("/classrooms")
    public List<ClassroomDTO> getClassrooms() {
        return roomController.getClassroomsNoAllocations();
    }

    @GetMapping("/classrooms/allocations")
    public List<ClassroomDTO> getClassroomsAllocations() {
        return roomController.getClassrooms();
    }

    @GetMapping("/classrooms/{name}/allocations")
    public ClassroomDTO getClassroom(@PathVariable String name) {
        return roomController.getClassroom(name);
    }

    @GetMapping("/classrooms/allocations/{dayOfWeek}")
    public List<ClassroomDTO> fullyOccupiedOrNotClassrooms(@RequestParam(defaultValue = "true") boolean full,
                                                           @PathVariable @Pattern(regexp = "\\bmonday|\\btuesday|\\bwednesday|\\bthursday|\\bfriday|\\bsaturday|\\bsunday/i", flags = Pattern.Flag.CASE_INSENSITIVE) String dayOfWeek) {
        if (full)
            return roomController.getFullyOccupiedClassrooms(dayOfWeek);
        else
            return roomController.getNotFullyOccupiedClassrooms(dayOfWeek);
    }

    @PostMapping("/students")
    public void postStudent(@RequestBody @Valid StudentDTO student) {
        roomController.setNewStudent(student);
    }

    @PostMapping("classrooms/{name}/allocations/{dayOfWeek}/students/{studentId}")
    public void postAllocation(@PathVariable String name, @PathVariable String studentId,
                               @PathVariable @Pattern(regexp = "\\bmonday|\\btuesday|\\bwednesday|\\bthursday|\\bfriday|\\bsaturday|\\bsunday/i", flags = Pattern.Flag.CASE_INSENSITIVE) String dayOfWeek) {
        StudentDTO studentDTO = roomController.getStudent(studentId);
        roomController.allocateStudentInClassroom(studentDTO, name, dayOfWeek.toUpperCase());
    }

    @GetMapping("/weather/{cityName}")
    public WeatherDTO getWeather(@PathVariable String cityName) {
        return weatherController.getWeather(cityName);
    }


}
