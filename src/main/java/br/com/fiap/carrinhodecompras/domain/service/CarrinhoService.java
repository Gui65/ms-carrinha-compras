package br.com.fiap.carrinhodecompras.domain.service;

import br.com.fiap.carrinhodecompras.application.controller.response.CarrinhoResponse;
import br.com.fiap.carrinhodecompras.application.controller.response.ItemCarrinhoResponse;
import br.com.fiap.carrinhodecompras.exceptions.NaoEncontradoException;
import br.com.fiap.carrinhodecompras.infra.client.ItemServiceClient;
import br.com.fiap.carrinhodecompras.domain.entity.Carrinho;
import br.com.fiap.carrinhodecompras.infra.client.dto.ItemDTO;
import br.com.fiap.carrinhodecompras.domain.entity.ItemCarrinho;
import br.com.fiap.carrinhodecompras.infra.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemServiceClient itemServiceClient;

    public CarrinhoResponse obterCarrinhoPorUsuarioId(UUID usuarioId) {
        Carrinho carrinho = carrinhoRepository.findByUsuarioId(usuarioId);
        return toCarrinhoResponse(carrinho);
    }

    public CarrinhoResponse adicionarItemAoCarrinho(UUID usuarioId, UUID itemId, int quantidade) {
        // Verificar se o item existe no serviço de itens
        ItemDTO item = itemServiceClient.obterItemPorId(itemId);
        //Item item = null;
        if (item == null) {
            throw new NaoEncontradoException(
                    String.format("Item com o id '%s' nao encontrado", itemId));
        }

        Carrinho carrinho = obterOuCriarCarrinho(usuarioId);
        Optional<ItemCarrinho> itemExistente = carrinho.getItens().stream().filter(itemCarrinho -> itemCarrinho.getItemId().equals(itemId)).findFirst();

        if (itemExistente.isPresent()) {
            itemExistente.get().setQuantidade(itemExistente.get().getQuantidade() + quantidade);
        } else {
            ItemCarrinho novoItem = new ItemCarrinho();
            novoItem.setItemId(itemId);
            novoItem.setQuantidade(quantidade);
            carrinho.getItens().add(novoItem);
        }
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);
        return toCarrinhoResponse(carrinhoSalvo);
    }

    public CarrinhoResponse removerItemDoCarrinho(UUID usuarioId, UUID itemId, int quantidade) {
        Carrinho carrinho = obterOuCriarCarrinho(usuarioId);
        Optional<ItemCarrinho> itemExistente = carrinho.getItens().stream().filter(itemCarrinho -> itemCarrinho.getItemId().equals(itemId)).findFirst();

        if (itemExistente.isPresent()) {
            ItemCarrinho item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() - quantidade);
            if (item.getQuantidade() <= 0) {
                carrinho.getItens().remove(item);
            }
        }

        carrinho = carrinhoRepository.save(carrinho);
        return toCarrinhoResponse(carrinho);
    }

    private Carrinho obterOuCriarCarrinho(UUID usuarioId) {
        Carrinho carrinho = carrinhoRepository.findByUsuarioId(usuarioId);
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setUsuarioId(usuarioId);
            carrinhoRepository.save(carrinho);
        }
        return carrinho;
    }

    private CarrinhoResponse toCarrinhoResponse(Carrinho carrinho) {
        return new CarrinhoResponse(
            carrinho.getId(),
            carrinho.getUsuarioId(),
            carrinho.getItens().stream().map(itemCarrinho -> new ItemCarrinhoResponse(
                itemCarrinho.getItemId(),
                itemCarrinho.getQuantidade(),
                itemCarrinho.getPreco(),
                itemCarrinho.getNome()
            )).toList()
        );
    }
}

