package com.tcc.tccbackend.dtos;

import java.util.List;

import com.tcc.tccbackend.models.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalDTO {
    private Integer id;
    private String email;
    private String nome;
    private String cpf;
    private String cnpj;
    private String telefone;
    private List<Agendamento> agendamentos;
}
