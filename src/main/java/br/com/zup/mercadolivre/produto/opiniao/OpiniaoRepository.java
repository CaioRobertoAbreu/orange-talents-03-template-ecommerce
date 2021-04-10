package br.com.zup.mercadolivre.produto.opiniao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends CrudRepository<Opiniao, Long> {
}
