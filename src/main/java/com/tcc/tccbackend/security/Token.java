package com.tcc.tccbackend.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    private String token;
    private Integer id;

    public Token(String token, Integer id) {
        this.token = token;
        this.id = id;
    }
}
