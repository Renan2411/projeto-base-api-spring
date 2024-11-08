package br.com.projetobase.domain.usecase.usuario.criar;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class CriarUsuarioOutput {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private OffsetDateTime dataNascimento;

}
