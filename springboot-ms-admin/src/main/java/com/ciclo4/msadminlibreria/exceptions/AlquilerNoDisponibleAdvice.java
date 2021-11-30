package com.ciclo4.msadminlibreria.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
@ResponseBody

public class AlquilerNoDisponibleAdvice {
    @ResponseBody
    @ExceptionHandler(AlquilerNoDisponibleException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String AlquilerNoDisponibleAdvice(AlquilerNoDisponibleException ex){
        return ex.getMessage();
    }


}
