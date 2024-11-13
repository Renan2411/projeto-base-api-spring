package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioInput;
import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioOutput;
import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Transactional
@RestController
@RequestMapping("/usuarios/{id}")
public class EditarUsuarioController {

    private final EditarUsuarioUseCase useCase;

    @PutMapping
    public ResponseEntity<EditarUsuarioOutput> editarUsuario(@PathVariable("id") Long idUsuario, @RequestBody EditarUsuarioInput input) {
        EditarUsuarioOutput output = useCase.executar(idUsuario, input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
