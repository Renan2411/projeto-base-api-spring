package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioInput;
import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioOutput;
import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/usuarios")
public class CriarUsuarioController {

    private final CriarUsuarioUseCase useCase;

    @PostMapping
    public ResponseEntity<CriarUsuarioOutput> criarUsuario(@RequestBody CriarUsuarioInput entrada) {
        CriarUsuarioOutput output = useCase.executar(entrada);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

}
