package br.com.fiap.carrinhodecompras.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class ItemCarrinho {
    private UUID itemId;
    private int quantidade;
}
