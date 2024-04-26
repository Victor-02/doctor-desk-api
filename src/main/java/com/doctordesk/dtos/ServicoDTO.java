package com.doctordesk.dtos;

import java.util.List;

import com.doctordesk.models.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO {
    private Integer id;
    private String nome;
    private Double preco;
    private List<Agendamento> agendamentos;
}
