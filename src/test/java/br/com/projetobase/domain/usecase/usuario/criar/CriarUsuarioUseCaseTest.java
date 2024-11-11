package br.com.projetobase.domain.usecase.usuario.criar;

import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.exception.generic.GenericValidationException;
import br.com.projetobase.domain.exception.generic.GenericValidationExceptionList;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.usuario.criar.converter.CriarUsuarioOutputConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CriarUsuarioUseCaseTest {

    @Mock
    private IUsuarioDataProvider iUsuarioDataProvider;

    CriarUsuarioUseCase useCase;

    @Before
    public void prepararTest() {
        useCase = criarUseCase();
    }

    @Test
    public void deveCriarUsuarioComSucessoTest() {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        when(iUsuarioDataProvider.existePorCpf(anyString()))
                .thenReturn(Boolean.FALSE);

        when(iUsuarioDataProvider.existePorEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .cpf("51459191749")
                .nome("Teste")
                .email("teste@teste.com")
                .dataNascimento(dataNascimento)
                .build();

        when(iUsuarioDataProvider.criar(any()))
                .thenReturn(UsuarioEntity.builder()
                        .id(1L)
                        .nome(entrada.getNome())
                        .cpf(entrada.getCpf())
                        .email(entrada.getEmail())
                        .dataNascimento(dataNascimento)
                        .build());

        CriarUsuarioOutput output = useCase.executar(entrada);

        assertEquals("1", output.getId().toString());
        assertEquals("51459191749", output.getCpf());
        assertEquals("Teste", output.getNome());
        assertEquals(dataNascimento, output.getDataNascimento());
    }

    @Test(expected = GenericValidationException.class)
    public void deveFalharAoCriarUsuarioComCpfJaCadastradoTest() {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        when(iUsuarioDataProvider.existePorCpf(anyString()))
                .thenReturn(Boolean.TRUE);

        when(iUsuarioDataProvider.existePorEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .cpf("51459191749")
                .nome("Teste")
                .email("teste@teste.com")
                .dataNascimento(dataNascimento)
                .build();

        when(iUsuarioDataProvider.criar(any()))
                .thenReturn(UsuarioEntity.builder()
                        .id(1L)
                        .nome(entrada.getNome())
                        .cpf(entrada.getCpf())
                        .email(entrada.getEmail())
                        .dataNascimento(dataNascimento)
                        .build());

        try {
            useCase.executar(entrada);
        } catch (GenericValidationException e) {
            assertEquals("Usuário com CPF 51459191749 já cadastrado.", e.getMessage());
            throw e;
        }
    }

    @Test(expected = GenericValidationException.class)
    public void deveFalharAoCriarUsuarioComEmailJaCadastradoTest() {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        when(iUsuarioDataProvider.existePorCpf(anyString()))
                .thenReturn(Boolean.FALSE);

        when(iUsuarioDataProvider.existePorEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .cpf("51459191749")
                .nome("Teste")
                .email("teste@teste.com")
                .dataNascimento(dataNascimento)
                .build();

        when(iUsuarioDataProvider.criar(any()))
                .thenReturn(UsuarioEntity.builder()
                        .id(1L)
                        .nome(entrada.getNome())
                        .cpf(entrada.getCpf())
                        .email(entrada.getEmail())
                        .dataNascimento(dataNascimento)
                        .build());

        try {
            useCase.executar(entrada);
        } catch (GenericValidationException e) {
            assertEquals("Usuário com email teste@teste.com já cadastrado.", e.getMessage());
            throw e;
        }
    }

    @Test(expected = GenericValidationExceptionList.class)
    public void deveFalharAoCriarUsuarioComEntradaInvalidaTest() {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        when(iUsuarioDataProvider.existePorCpf(anyString()))
                .thenReturn(Boolean.FALSE);

        when(iUsuarioDataProvider.existePorEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .cpf(null)
                .nome(null)
                .email(null)
                .dataNascimento(dataNascimento)
                .build();

        when(iUsuarioDataProvider.criar(any()))
                .thenReturn(UsuarioEntity.builder()
                        .id(1L)
                        .nome(entrada.getNome())
                        .cpf(entrada.getCpf())
                        .email(entrada.getEmail())
                        .dataNascimento(dataNascimento)
                        .build());

        try {
            useCase.executar(entrada);
        } catch (GenericValidationExceptionList e) {
            assertEquals("Ausência do cpf.", e.getExceptions().get(0).getMessage());
            assertEquals("Ausência do nome.", e.getExceptions().get(1).getMessage());
            assertEquals("Ausência do email.", e.getExceptions().get(2).getMessage());
            throw e;
        }
    }

    public CriarUsuarioUseCase criarUseCase() {
        return CriarUsuarioUseCase.builder()
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .outputConverter(CriarUsuarioOutputConverter.builder().build())
                .build();
    }

}