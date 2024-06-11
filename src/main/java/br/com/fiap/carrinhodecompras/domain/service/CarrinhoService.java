package br.com.fiap.carrinhodecompras.domain.service;

//import br.com.fiap.carrinhodecompras.client.ItemServiceClient;
import br.com.fiap.carrinhodecompras.domain.entity.Carrinho;
import br.com.fiap.carrinhodecompras.domain.entity.Item;
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

   /* @Autowired
    private ItemServiceClient itemServiceClient;*/

    public Carrinho obterCarrinhoPorUsuarioId(UUID usuarioId) {
        return carrinhoRepository.findByUsuarioId(usuarioId);
    }

    public Carrinho adicionarItemAoCarrinho(UUID usuarioId, UUID itemId, int quantidade) {
        // Verificar se o item existe no serviço de itens
        //Item item = itemServiceClient.obterItemPorId(itemId);
        Item item = null;
        if (item == null) {
            throw new RuntimeException("Item não encontrado");
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

        return carrinhoRepository.save(carrinho);
    }

    public Carrinho removerItemDoCarrinho(UUID usuarioId, UUID itemId, int quantidade) {
        Carrinho carrinho = obterOuCriarCarrinho(usuarioId);
        Optional<ItemCarrinho> itemExistente = carrinho.getItens().stream().filter(itemCarrinho -> itemCarrinho.getItemId().equals(itemId)).findFirst();

        if (itemExistente.isPresent()) {
            ItemCarrinho item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() - quantidade);
            if (item.getQuantidade() <= 0) {
                carrinho.getItens().remove(item);
            }
        }

        return carrinhoRepository.save(carrinho);
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
}

