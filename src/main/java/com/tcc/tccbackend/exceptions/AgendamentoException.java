package com.tcc.tccbackend.exceptions;

import javax.persistence.PersistenceException;


public class AgendamentoException extends PersistenceException {
    public AgendamentoException(String message) {
        super(message);
    }

    public AgendamentoException(String message, Throwable e) {
        super(message, e);
    }
}
