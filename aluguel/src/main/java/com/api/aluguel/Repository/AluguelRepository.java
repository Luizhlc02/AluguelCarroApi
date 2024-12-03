package com.api.aluguel.Repository;

import com.api.aluguel.Entity.Aluguel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {



    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO aluguelcarros.tb_aluguel\n" +
            "(dataAluguel, dataVencimento, idCarro, idCliente, idColaborador)\n" +
            "VALUES( :dataAluguel, :dataVencimento, :idCarro, :idCliente, :idColaborador)")
    void inserirAluguel(@Param("dataAluguel") String dataAluguel,@Param("dataVencimento") String dataVencimento,@Param("idCliente") Long idCliente, @Param("idCarro") Long idCarro,@Param("idColaborador") Long idColaborador);
}