package br.com.projetobase.domain.usecase.usuario;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class UsuarioInput {

    private String nome;
    private String email;
    private String cpf;
    private OffsetDateTime dataNascimento;

}
