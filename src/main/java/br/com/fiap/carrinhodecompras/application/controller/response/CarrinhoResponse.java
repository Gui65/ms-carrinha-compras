package br.com.fiap.carrinhodecompras.application.controller.response;

import java.util.List;
import java.util.UUID;

public record CarrinhoResponse(
    UUID id,
    UUID usuarioId,
    List<ItemCarrinhoResponse> itens) {
}
