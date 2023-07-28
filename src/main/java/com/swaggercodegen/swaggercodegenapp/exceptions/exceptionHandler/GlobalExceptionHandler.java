package com.swaggercodegen.swaggercodegenapp.exceptions.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.swaggercodegen.swaggercodegenapp.exceptions.BadRequestException;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto.AntTypeEnum;
import com.swaggercodegen.swaggercodegenapp.model.ErrorDetails;

@ControllerAdvice // Allows handling exceptions across the whole application in one global
                  // handling component
public class GlobalExceptionHandler {
    // Handle global Exception
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorDetails> handleInternalException(Exception
    // resourceNotFoundException,
    // WebRequest webRequest) {
    // ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
    // resourceNotFoundException.getMessage(),
    // webRequest.getDescription(false),
    // HttpStatus.INTERNAL_SERVER_ERROR.toString());

    // return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    // 400 BadRequest
    // @ExceptionHandler(BadRequestException.class)
    // public ResponseEntity<ErrorDetails> handleAllException(BadRequestException
    // badRequestException,
    // WebRequest webRequest) {
    // ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
    // AntTypeEnum.ERROR,
    // badRequestException.getMessage(),
    // webRequest.getDescription(false), HttpStatus.BAD_REQUEST.toString());
    // return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    // }
}
