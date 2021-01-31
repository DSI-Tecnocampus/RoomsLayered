# Rooms in a Layered Architecture

See below the methods this REST API provides:
 *   @GetMapping("/students")
 *   @GetMapping("/students/{id}")
 *   @GetMapping("students/me")
 *   @GetMapping("/classrooms")
 *   @GetMapping("/classrooms/allocations")
 *   @GetMapping("/classrooms/{name}/allocations")
 *   @GetMapping("/classrooms/allocations/{dayOfWeek}")
 *   @GetMapping("/weather/{cityName}")
 *   @PostMapping("/students")
 *   @PostMapping("classrooms/{name}/allocations/{dayOfWeek}/students/{studentId}")

Note that most of the operation just read some information. Then there is a POST for creating a new student which is a CRUD operation whith
no domain logics. Finally there is a last operation that is an actual use case that consists on allocating a student in a classroom.



