package br.com.fiap.carrinhodecompras.application.controller.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemCarrinhoResponse(
    UUID id,
    int quantidade,
    BigDecimal preco,
    String nome
) {
}
