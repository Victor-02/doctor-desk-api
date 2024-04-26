package com.doctordesk.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant dataAtual;
    private String erro;
    private Integer Status;
    private String mensagem;
}