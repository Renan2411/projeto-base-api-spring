package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.domain.usecase.usuario.excluir.ExcluirUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@AllArgsConstructor
@RequestMapping("/usuarios/{id}")
public class ExcluirUsuarioController {

    private final ExcluirUsuarioUseCase useCase;

    @DeleteMapping
    public ResponseEntity<Void> excluir(@PathVariable("id") Long idUsuario) {
        useCase.executar(idUsuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
