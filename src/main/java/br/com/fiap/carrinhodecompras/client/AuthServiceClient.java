package br.com.fiap.carrinhodecompras.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {
    @GetMapping("/auth/validate")
    boolean validarToken(@RequestHeader("Authorization") String token);
}
