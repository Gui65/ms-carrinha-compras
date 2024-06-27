package br.com.fiap.carrinhodecompras.application.controller;

import br.com.fiap.carrinhodecompras.application.controller.response.CarrinhoResponse;
import br.com.fiap.carrinhodecompras.domain.entity.Carrinho;
import br.com.fiap.carrinhodecompras.domain.security.TokenService;
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

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public CarrinhoResponse obterCarrinho(@RequestHeader("Authorization") String token) {
        //UUID usuarioId = UUID.fromString(token.getSubject());
        var tokenReplace = token.replace("Bearer ", "");
        String id = tokenService.validateToken(tokenReplace);
        return carrinhoService.obterCarrinhoPorUsuarioId(id);
    }

    @PostMapping("/adicionar")
    public CarrinhoResponse adicionarItemAoCarrinho(@RequestHeader("Authorization") String token,
                                            @RequestParam String itemId,
                                            @RequestParam int quantidade) {
        var tokenReplace = token.replace("Bearer ", "");
        String id = tokenService.validateToken(tokenReplace);
        return carrinhoService.adicionarItemAoCarrinho(id, itemId, quantidade);
    }

    @PostMapping("/remover")
    public CarrinhoResponse removerItemDoCarrinho(@RequestHeader("Authorization") String token,
                                          @RequestParam String itemId,
                                          @RequestParam int quantidade) {
        var tokenReplace = token.replace("Bearer ", "");
        String id = tokenService.validateToken(tokenReplace);
        return carrinhoService.removerItemDoCarrinho(id, itemId, quantidade);
    }
}