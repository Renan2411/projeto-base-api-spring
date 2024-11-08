package br.com.projetobase.adapter.gateway.controller.exception;

import br.com.projetobase.domain.exception.generic.GenericValidationException;
import br.com.projetobase.domain.exception.generic.GenericValidationExceptionList;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GenericValidationExceptionList.class})
    public ResponseEntity<Object> handleGenericExceptionList(GenericValidationExceptionList exceptions, HttpServletRequest request) {
        String path = request.getRequestURI();
        List<ErrorMessage> response = exceptions.getExceptions().stream()
                .map(exception -> new ErrorMessage(exception.getClass().getName(), exception.getMessage(), path, exception.getArgs()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse(response), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({GenericValidationException.class})
    public ResponseEntity<Object> handleGenericException(GenericValidationException exception, HttpServletRequest request) {
        String path = request.getRequestURI();
        ErrorMessage response = new ErrorMessage(exception.getClass().getName(), exception.getMessage(), path, exception.getArgs());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    static class ErrorMessage {
        String name;
        String message;
        String path;
        List<String> args;
    }

    @Data
    @AllArgsConstructor
    static class ErrorResponse {
        List<ErrorMessage> mensagens;
    }

}