package com.tcc.tccbackend.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "atendentes")
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String telefone;
}
