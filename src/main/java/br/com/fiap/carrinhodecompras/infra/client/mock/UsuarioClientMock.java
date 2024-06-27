package br.com.fiap.carrinhodecompras.infra.client.mock;

import br.com.fiap.carrinhodecompras.infra.client.dto.UsuarioDTO;
import br.com.fiap.carrinhodecompras.infra.client.UsuarioClient;
import br.com.fiap.carrinhodecompras.utils.enums.UsuarioRole;
import org.springframework.stereotype.Component;

@Component
public class UsuarioClientMock implements UsuarioClient {

    @Override
    public UsuarioDTO findByLogin(String login) {
        if(login.equals("gui@matos.com")) {
            return new UsuarioDTO(
                    "1",
                    login,
                    "$2y$10$aFoZefH/3.Ju3pF9RP5/UuDFXkpYZdJ2b3Sjps0IrreUMjYhrEKnu",
                    UsuarioRole.ADMIN
            );
        }

        return new UsuarioDTO(
                "2",
                login,
                "USER",
                UsuarioRole.USER
        );
    }
}
