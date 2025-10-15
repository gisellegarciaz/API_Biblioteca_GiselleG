package org.serratec.h2biblioteca.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        
        ErroResposta erroResposta = new ErroResposta();
        erroResposta.setStatus(HttpStatus.NOT_FOUND.value());
        erroResposta.setTitulo("Recurso Não Encontrado");
        erroResposta.setDataHora(LocalDateTime.now());
        erroResposta.setErros(List.of(ex.getMessage()));

        return new ResponseEntity<>(erroResposta, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            erros.add(error.getField() + ": " + error.getDefaultMessage());
        }
        
        ErroResposta erroResposta = new ErroResposta();
        erroResposta.setStatus(status.value());
        erroResposta.setTitulo("Erro de Validação");
        erroResposta.setDataHora(LocalDateTime.now());
        erroResposta.setErros(erros);

        return new ResponseEntity<>(erroResposta, headers, status);
    }
}