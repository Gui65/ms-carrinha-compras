package br.com.fiap.carrinhodecompras.infra.repository;

import br.com.fiap.carrinhodecompras.domain.entity.Carrinho;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarrinhoRepository extends MongoRepository<Carrinho, UUID> {
    Carrinho findByUsuarioId(String usuarioId);
}
