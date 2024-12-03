package com.api.aluguel.Service;

import com.api.aluguel.Entity.Colaborador;
import com.api.aluguel.Repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Transactional
    public Colaborador salvar(Colaborador colaboradores) {
        return colaboradorRepository.save(colaboradores);
    }

}
