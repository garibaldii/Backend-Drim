package com.projeto.drim.exception;

import com.projeto.drim.dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
//ℹ️ notação diz ao Spring que esta classe é responsável por
// fornecer tratamento global para exceções em todos os controladores REST da aplicação.
public class GlobalExceptionHandler {
    // ℹ️ classe GlobalExceptionHandler é responsável por lidar com exceções (erros)
    // que ocorrem durante a validação dos dados recebidos em uma API.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // ℹ️ Esta anotação indica que o método "Lida com erro de validação" deve ser chamado quando ocorrer uma exceção do tipo MethodArgumentNotValidException. Essa exceção é lançada quando ocorre uma falha na validação de dados de entrada em métodos de controle (controllers) da API.
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex
                .getBindingResult()
                .getAllErrors()
                .forEach((error) -> {

                    String chave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();

                    errors.put(chave, valor);

                });
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "❌ Erro de Validação", errors);

        //ℹ️ Cria um objeto ValidationErrorResponse, que é uma classe personalizada para representar a resposta de erro de
        // validação. Este objeto contém o código HTTP 400 (Bad Request), uma mensagem genérica de erro e o mapa de erros detalhados.
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }








    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "😢  Objeto não encontrado no banco de dados ",
                Map.of("mensagem", ex.getMessage())
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "😯 Objeto já cadastrado",
                Map.of("mensagem", ex.getMessage())
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);


    }


}
