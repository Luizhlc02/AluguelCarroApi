package com.api.aluguel.Repository;

import com.api.aluguel.Entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Colaborador getColaboradorByIdColaborador(Long idColaborador);
}
