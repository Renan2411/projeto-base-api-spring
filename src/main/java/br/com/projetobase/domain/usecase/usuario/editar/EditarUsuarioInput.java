package br.com.projetobase.domain.usecase.usuario.editar;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class EditarUsuarioInput {

    private String nome;
    private String email;
    private OffsetDateTime dataNascimento;

}
