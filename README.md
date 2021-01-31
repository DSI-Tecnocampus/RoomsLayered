# Individual Exercise 5: Security

In this repository you'll find the fourth exercise solved, that is, the use of AOP to monitor the application
controller (RoomController)
and getting the weather forecast from an external REST API. In this exercise we are going to secure our application with
Json Web Tokens.

We will have two roles: ADMIN and STUDENT.

#### STUDENT rol

It will be able to:

* list classrooms without allocations
* get the weather forecast
* see her/his own profile. For this you'll need to create a new Rest entry: "students/me"

#### ADMIN rol

It will be able to:

* list classrooms with and without allocations
* list all students
* list a student given her id
* create a new student
* create a new allocation
* get the weather forecast

#### Unregistered user

* get the weather forecast

## Some clues or advice

* You'll want to add a password to the student table. Alternatively, you could create a *user* table since admins are
  not students, but it would complicate things, and it isn't worth for this exercise.
* You'll need to make sure there is **not** any session in the server.
* You'll want to add a "authorities" table. For example, make Pepe and Mario be STUDENTs, Pepa be an ADMIN, and Maria be
  both roles.
* You may use the following password that corresponds to "password123":
  {bcrypt}$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2 for all users
* Keep all security configuration files in a separated package
* If I were you, I'd implement first the "basic auth" and once it works, I'd go for the JWT. Plan this exercise, you
  have two weeks. For example during the first week code the "basic auth" and in the second the JWT.
