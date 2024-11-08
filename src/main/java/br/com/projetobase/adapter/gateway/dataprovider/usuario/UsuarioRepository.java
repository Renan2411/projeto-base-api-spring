package br.com.projetobase.adapter.gateway.dataprovider.usuario;

import br.com.projetobase.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

}
