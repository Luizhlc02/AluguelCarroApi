package com.api.aluguel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.aluguel.Entity.Carro;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {

    Carro getCarroByIdCarro(Long idCarro);
}
