package com.interview.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExcepitonHandler {

    @ExceptionHandler(value = NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleAccountNotFoundException(NoDataFoundException exception)
    {
        return new ResponseEntity<>("The record does not exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoUniqueResultFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleNoUniqueResultFoundException(NoUniqueResultFoundException exception)
    {
        return new ResponseEntity<>("No Unique Result found for Given Account Details", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidJsonWebTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<Object> handleInvalidTokenException(InvalidJsonWebTokenException exception)
    {
        return new ResponseEntity<>("Jwt is not valid or it's expired", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserAlreadyLoggedInException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ResponseEntity<Object> handleInvalidTokenException(UserAlreadyLoggedInException exception)
    {
        return new ResponseEntity<>("User is already logged in!", HttpStatus.NO_CONTENT);
    }
}
