package com.tcc.tccbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "importacoes")
public class Import implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column
    private String nome;

    @Column
    private String data;

    public Import(String nome, String data) {
        this.nome = nome;
        this.data = data;
    }

    public Import() {
    }

}
