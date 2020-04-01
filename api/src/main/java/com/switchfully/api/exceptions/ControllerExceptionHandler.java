package com.switchfully.api.exceptions;


//import com.switchfully.api.endpoints.BookController;
//import com.switchfully.api.endpoints.MemberController;
//import com.switchfully.domain.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
//    private final Logger loggerMember = LoggerFactory.getLogger(MemberController.class);
//    private final Logger bookLogger = LoggerFactory.getLogger(BookController.class);
//
//    @ExceptionHandler(EmailNotValidException.class)
//    protected void emailNotValidException(EmailNotValidException ex, HttpServletResponse response) throws IOException {
//        loggerMember.error("Email is not valid!", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//
//    @ExceptionHandler(EmailAlreadyRegisteredException.class)
//    protected void emailAlreadyRegisteredException(EmailAlreadyRegisteredException ex, HttpServletResponse response) throws IOException {
//        loggerMember.error("Email is already registered!", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//
//    @ExceptionHandler(MemberNotFoundException.class)
//    protected void memberNotFoundException(MemberNotFoundException ex, HttpServletResponse response) throws IOException {
//        loggerMember.error("Could not find any member.", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//
//    @ExceptionHandler(InssNotValidException.class)
//    protected void inssNotValidException(InssNotValidException ex, HttpServletResponse response) throws IOException {
//        loggerMember.error("Inss is not valid!", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//
//    @ExceptionHandler(BookIsNotValidException.class)
//    protected void bookIsNotValidException(BookIsNotValidException ex, HttpServletResponse response) throws IOException {
//        bookLogger.error("Book is not valid.", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//
//    @ExceptionHandler(BookNotFoundException.class)
//    protected void bookNotFoundException(BookNotFoundException ex, HttpServletResponse response) throws IOException {
//        bookLogger.error("Book is not found", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//    @ExceptionHandler(FieldMustBeProvidedException.class)
//    protected void fieldMustBeProvidedException(FieldMustBeProvidedException ex, HttpServletResponse response) throws IOException {
//        bookLogger.error("Required field is not provided", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//    @ExceptionHandler(BookDoesNotExistException.class)
//    protected void bookDoesNotExistException(BookDoesNotExistException ex, HttpServletResponse response) throws IOException {
//        bookLogger.error("Required field is not provided", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//    @ExceptionHandler(BookAlreadyExistsException.class)
//    protected void bookAlreadyExistsException(BookAlreadyExistsException ex, HttpServletResponse response) throws IOException {
//        bookLogger.error("Required field is not provided", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//    @ExceptionHandler(ISBNNotValidException.class)
//    protected void ISBNNotValidException(ISBNNotValidException ex, HttpServletResponse response) throws IOException {
//        bookLogger.error("Required field is not provided", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
//    @ExceptionHandler(InputCanNotBeNullException.class)
//    protected void inputCanNotBeNullException(InputCanNotBeNullException ex, HttpServletResponse response) throws IOException {
//        bookLogger.error("Required field is not provided", ex);
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }

}
