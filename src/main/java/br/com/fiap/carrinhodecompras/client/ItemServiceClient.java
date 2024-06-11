package br.com.fiap.carrinhodecompras.client;

import br.com.fiap.carrinhodecompras.domain.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Repository
//@FeignClient(name = "item-service")
public interface ItemServiceClient {
    /*@GetMapping("/itens/{id}")
    Item obterItemPorId(@PathVariable UUID id);*/
}
