package br.com.fiap.carrinhodecompras.infra.client;

import br.com.fiap.carrinhodecompras.infra.client.dto.ItemDTO;
import br.com.fiap.carrinhodecompras.infra.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Repository
@FeignClient(name = "ms-gestao-itens", configuration = FeignConfig.class)
public interface ItemServiceClient {
    @GetMapping("/itens/{id}")
    ItemDTO obterItemPorId(@PathVariable UUID id);
}
