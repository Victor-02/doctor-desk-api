package com.tcc.tccbackend.builder;

import com.tcc.tccbackend.dtos.PacienteDTO;
import lombok.Builder;

@Builder
public class PacienteDTOBuilder {

    @Builder.Default
    private Integer id = 1;

    @Builder.Default
    private String nome = "Victor";

    @Builder.Default
    private String email = "victor@gmail.com";

    @Builder.Default
    private String dataNascimento = "1998-05-05";

    @Builder.Default
    private String telefone = "99999999999";

    @Builder.Default
    private String cpf = "99999999999";

    public PacienteDTO toPacienteDTO() {
        return new PacienteDTO(id, nome, email, dataNascimento, telefone, cpf);
    }
}