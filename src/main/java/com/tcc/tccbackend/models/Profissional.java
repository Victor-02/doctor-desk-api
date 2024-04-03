package com.tcc.tccbackend.models;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "profissionais")
@ToString(exclude = {"agendamentos"})
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column
    private String cnpj;

    @Column
    private String telefone;

    @Column
    @OneToMany(mappedBy = "profissional", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "profissional_id")
    private List<Agendamento> agendamentos;
}
