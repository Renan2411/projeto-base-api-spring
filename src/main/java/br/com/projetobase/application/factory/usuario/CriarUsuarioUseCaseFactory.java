package br.com.projetobase.application.factory.usuario;

import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioUseCase;
import br.com.projetobase.domain.usecase.usuario.criar.converter.CriarUsuarioOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class CriarUsuarioUseCaseFactory {

    @Bean
    @DependsOn("criarCriarUsuarioOutputConverter")
    public CriarUsuarioUseCase criarUsuarioUseCase(IUsuarioDataProvider iUsuarioDataProvider,
                                                   CriarUsuarioOutputConverter outputConverter) {
        return CriarUsuarioUseCase.builder()
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean
    public CriarUsuarioOutputConverter criarCriarUsuarioOutputConverter() {
        return CriarUsuarioOutputConverter.builder().build();
    }

}
