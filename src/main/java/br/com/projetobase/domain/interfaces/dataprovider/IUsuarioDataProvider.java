package br.com.projetobase.domain.interfaces.dataprovider;

import br.com.projetobase.domain.entity.UsuarioEntity;

public interface IUsuarioDataProvider {

    UsuarioEntity criar(UsuarioEntity usuarioEntity);

    UsuarioEntity editar(UsuarioEntity usuarioEntity);

    boolean existePorCpf(String cpf);

    boolean existePorEmail(String email);
}
