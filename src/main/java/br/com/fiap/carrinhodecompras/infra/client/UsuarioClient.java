package br.com.fiap.carrinhodecompras.infra.client;

import br.com.fiap.carrinhodecompras.infra.client.dto.UsuarioDTO;
import br.com.fiap.carrinhodecompras.infra.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-usuario", path = "/usuario", configuration = FeignConfig.class)
public interface UsuarioClient {

//    @GetMapping("/login")
//    UsuarioResponse login(@RequestBody UsuarioRequest usuarioRequest);

    @GetMapping("/login")
    UsuarioDTO findByLogin(@RequestBody String login);
}
