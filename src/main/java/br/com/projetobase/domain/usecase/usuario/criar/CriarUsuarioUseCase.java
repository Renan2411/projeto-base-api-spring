package br.com.projetobase.domain.usecase.usuario.criar;

import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.exception.generic.GenericValidationException;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.usuario.criar.converter.CriarUsuarioOutputConverter;
import br.com.projetobase.domain.validation.Validator;
import lombok.Builder;

import java.util.Objects;

@Builder
public class CriarUsuarioUseCase {

    private final IUsuarioDataProvider iUsuarioDataProvider;
    private final CriarUsuarioOutputConverter outputConverter;

    public CriarUsuarioOutput executar(CriarUsuarioInput entrada) {
        validarEntrada(entrada);
        validarSeCpfJaExiste(entrada.getCpf());
        validarSeEmailJaExiste(entrada.getEmail());

        UsuarioEntity usuarioEntity = criarUsuarioEntity(entrada);
        usuarioEntity = salvarUsuario(usuarioEntity);

        return outputConverter.converter(usuarioEntity);
    }

    private void validarEntrada(CriarUsuarioInput entrada) {
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getCpf()), "Ausência do cpf.")
                .validate(Objects.nonNull(entrada.getNome()), "Ausência do nome.")
                .validate(Objects.nonNull(entrada.getEmail()), "Ausência do email.")
                .get();
    }

    private void validarSeCpfJaExiste(String cpf) {
        if (!iUsuarioDataProvider.existePorCpf(cpf)) {
            return;
        }

        throw new GenericValidationException(String.format("Usuário com CPF %s já cadastrado.", cpf));
    }

    private void validarSeEmailJaExiste(String email) {
        if (!iUsuarioDataProvider.existePorEmail(email)) {
            return;
        }

        throw new GenericValidationException(String.format("Usuário com email %s já cadastrado.", email));
    }

    private UsuarioEntity criarUsuarioEntity(CriarUsuarioInput entrada) {
        return UsuarioEntity.builder()
                .nome(entrada.getNome())
                .cpf(entrada.getCpf())
                .email(entrada.getEmail())
                .dataNascimento(entrada.getDataNascimento())
                .build();
    }

    private UsuarioEntity salvarUsuario(UsuarioEntity usuarioEntity) {
        return iUsuarioDataProvider.criar(usuarioEntity);
    }

}
