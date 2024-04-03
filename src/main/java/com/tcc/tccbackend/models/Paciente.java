package com.tcc.tccbackend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"agendamentos"})
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String cpf;

    @Column
    private String telefone;

    @Column
    private Date dataNascimento;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "paciente_id")
    private List<Agendamento> agendamentos;
}