package br.com.fiap.carrinhodecompras.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Document(collection = "carrinhos")
@Data
public class Carrinho {
    @Id
    private UUID id;
    private UUID usuarioId;
    private List<ItemCarrinho> itens;
}
