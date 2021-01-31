package cat.tecnocampus.rooms.api.errorHandling;

import cat.tecnocampus.rooms.application.exceptions.ClassroomDoesNotExistException;
import cat.tecnocampus.rooms.application.exceptions.StudentDoesNotExistException;
import cat.tecnocampus.rooms.domain.exceptions.ClassroomOccupiedException;
import cat.tecnocampus.rooms.domain.exceptions.StudentAlreadyAllocatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ResponseBody
    @ExceptionHandler({ClassroomOccupiedException.class, StudentAlreadyAllocatedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    String objectNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({ClassroomDoesNotExistException.class, StudentDoesNotExistException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String objectAlreadyExists(Exception exception) {
        return exception.getMessage();
    }

}
