package br.com.projetobase.domain.exception;

import br.com.projetobase.domain.exception.generic.GenericNotFoundException;

public class UsuarioNaoEncontradoException extends GenericNotFoundException {

    public UsuarioNaoEncontradoException(Long idUsuario) {
        super(String.format("Usuário de id %d não encontrado.", idUsuario));
    }

}
