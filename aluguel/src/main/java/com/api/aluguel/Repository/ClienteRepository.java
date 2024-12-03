package com.api.aluguel.Repository;

import com.api.aluguel.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente getClienteByIdCliente(Long idCliente);

    Optional<Cliente> findByCliente(String emailCliente);

    @Query("Select c.role from Cliente c where c.emailCliente like :emailCliente")
    Cliente.Role findByRoleCliente(String emailCliente);
}
