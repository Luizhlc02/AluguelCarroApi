package com.api.aluguel.Repository;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Colaborador getColaboradorByIdColaborador(Long idColaborador);

    Colaborador.Role findByNomeColaborador(String nomeColaborador);

    Optional<Colaborador> findByEmailColaborador(String emailColaborador);
}
