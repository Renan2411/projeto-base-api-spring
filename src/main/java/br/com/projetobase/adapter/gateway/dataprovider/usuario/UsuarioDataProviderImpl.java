package br.com.projetobase.adapter.gateway.dataprovider.usuario;

import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioDataProviderImpl implements IUsuarioDataProvider {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity criar(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public UsuarioEntity editar(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }
}
