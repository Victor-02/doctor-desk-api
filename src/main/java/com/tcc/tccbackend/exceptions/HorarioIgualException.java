package com.tcc.tccbackend.exceptions;

import javax.persistence.PersistenceException;


public class HorarioIgualException  extends PersistenceException {
    public HorarioIgualException() {
    }

    public HorarioIgualException(String message) {
        super(message);
    }
}
