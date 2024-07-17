package com.projeto.drim.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // Gera um construtor com um argumento para cada campo

public class ErrorResponse {
    private int status;
    private String message;
    private Map<String, String> errors;

}
