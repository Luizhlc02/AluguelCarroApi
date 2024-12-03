package com.api.aluguel.Controller;

import com.api.aluguel.Repository.CarroRepository;
import com.api.aluguel.Entity.Carro;
import com.api.aluguel.Service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/carro")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<Carro> create(@RequestBody Carro carro){
        Carro NovoCarro = carroService.salvar(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(NovoCarro);
    }

    @GetMapping
    public Object listaCarros(){
        List<Carro> listaCarros = carroRepository.findAll();
        return listaCarros;
    }
}
