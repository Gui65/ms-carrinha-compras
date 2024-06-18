package br.com.fiap.carrinhodecompras.application.controller;

import br.com.fiap.carrinhodecompras.application.controller.response.CarrinhoResponse;
import br.com.fiap.carrinhodecompras.domain.entity.Carrinho;
import br.com.fiap.carrinhodecompras.domain.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public CarrinhoResponse obterCarrinho(@AuthenticationPrincipal Jwt principal) {
        UUID usuarioId = UUID.fromString(principal.getSubject());
        return carrinhoService.obterCarrinhoPorUsuarioId(usuarioId);
    }

    @PostMapping("/adicionar")
    public CarrinhoResponse adicionarItemAoCarrinho(@AuthenticationPrincipal Jwt principal,
                                            @RequestParam UUID itemId,
                                            @RequestParam int quantidade) {
        UUID usuarioId = UUID.fromString(principal.getSubject());
        return carrinhoService.adicionarItemAoCarrinho(usuarioId, itemId, quantidade);
    }

    @PostMapping("/remover")
    public CarrinhoResponse removerItemDoCarrinho(@AuthenticationPrincipal Jwt principal,
                                          @RequestParam UUID itemId,
                                          @RequestParam int quantidade) {
        UUID usuarioId = UUID.fromString(principal.getSubject());
        return carrinhoService.removerItemDoCarrinho(usuarioId, itemId, quantidade);
    }
}