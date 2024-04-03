package com.tcc.tccbackend.exceptions;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException e, HttpServletRequest requisicao) {
        ErrorMessage err = new ErrorMessage();
        err.setDataAtual(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setErro(HttpStatus.NOT_FOUND.toString());
        err.setMensagem("Paciente não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorMessage> entityExistsException(EntityExistsException e, HttpServletRequest requisicao) {
        ErrorMessage err = new ErrorMessage();
        err.setDataAtual(Instant.now());
        err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        err.setErro(HttpStatus.UNPROCESSABLE_ENTITY.toString());
        err.setMensagem("Um ou mais Pacientes já existem!");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }
    @ExceptionHandler(HorarioIgualException.class)
    public ResponseEntity<ErrorMessage> HorarioIgualException(HorarioIgualException e, HttpServletRequest requisicao) {
        ErrorMessage err = new ErrorMessage();
        err.setDataAtual(Instant.now());
        err.setStatus(HttpStatus.BAD_GATEWAY.value());
        err.setErro(HttpStatus.BAD_GATEWAY.toString());
        err.setMensagem("Horário já está ocupado!");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorMessage> signatureException(SignatureException e, HttpServletRequest requisicao) {
        ErrorMessage err = new ErrorMessage();
        err.setDataAtual(Instant.now());
        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setErro(HttpStatus.UNAUTHORIZED.toString());
        err.setMensagem("Token inválido!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
}
