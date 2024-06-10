package br.com.fiap.carrinhodecompras.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@Getter
@Setter
public class Item {
    private UUID id;
    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private String urlImagem;
    private int quantidade;
}
