package br.com.fiap.model.repository;

import br.com.fiap.model.entity.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    Habilidade findByCodigo(Long codigo);
}
