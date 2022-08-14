package com.tecnotree.rwagent.exceptions;


import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exp){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND);
        response.setMessage("error message: "+exp.getMessage());
        return buildResponseEntity(response);

    }

    @ExceptionHandler(value = ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleConflictException(ConflictException exp){
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT);
        response.setMessage("error message: "+exp.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exp){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST);
        response.setMessage("error message: "+exp.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(value = DataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleDataAccessException(DataAccessException exp){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST);
        response.setMessage("error message: "+exp.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exp){
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setMessage("error message: "+exp.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handleSQLIntegrity(SQLIntegrityConstraintViolationException ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE);
        response.setMessage("error message: "+ex.getMessage());
        return buildResponseEntity(response);
    }


    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){
        return new ResponseEntity<Object>(errorResponse,errorResponse.getStatus());
    }

}
