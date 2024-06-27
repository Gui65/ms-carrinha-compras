package br.com.fiap.carrinhodecompras.domain.entity;

import lombok.Generated;
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
    private String usuarioId;
    private List<ItemCarrinho> itens;

    public Carrinho() {
        this.id = UUID.randomUUID(); // Inicializa o UUID automaticamente
    }
}

