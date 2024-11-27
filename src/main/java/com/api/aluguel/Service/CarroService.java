package com.api.aluguel.Service;


import com.api.aluguel.Entity.Carro;
import com.api.aluguel.Repository.CarroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Transactional
    public Carro salvar(Carro carros) {
        return carroRepository.save(carros);
    }

}
