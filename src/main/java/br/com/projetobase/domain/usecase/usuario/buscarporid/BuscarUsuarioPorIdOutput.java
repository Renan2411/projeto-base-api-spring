package br.com.projetobase.domain.usecase.usuario.buscarporid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class BuscarUsuarioPorIdOutput {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private OffsetDateTime dataNascimento;

}
