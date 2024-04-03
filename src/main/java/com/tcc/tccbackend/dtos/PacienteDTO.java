package com.tcc.tccbackend.dtos;

import com.tcc.tccbackend.models.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    private Integer id;
    private String nome;
    private String email;
    private String dataNascimento;
    private String telefone;
    private String cpf;
    private Agendamento agendamento;

    public PacienteDTO(Integer id, String nome, String email, String dataNascimento, String telefone, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.cpf = cpf;
    }
}

