package com.api.aluguel.Controller;

import com.api.aluguel.Repository.AluguelRepository;
import com.api.aluguel.dto.AluguelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.aluguel.Entity.*;
import com.api.aluguel.Service.AluguelService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/aluguel")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private AluguelRepository aluguelRepository;

    @PostMapping
    public ResponseEntity<Aluguel> create(@RequestBody AluguelDto aluguelDto){
        Aluguel NovoAluguel = aluguelService.salvar(aluguelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(NovoAluguel);
    }

    @GetMapping
    public Page<Aluguel> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "idAluguel") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    )
    {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return aluguelService.findAll(pageable);
    }

    @GetMapping("/{idAluguel}")
    public Object aluguelFindById(@PathVariable Long idAluguel){
        Optional<Aluguel> aluguel = aluguelService.aluguelFindbyID(idAluguel);
        if (aluguel.isPresent()) {
            return aluguel.get();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idAluguel}")
     public ResponseEntity<Aluguel> alterarCadastroAluguel(@PathVariable Long idAluguel, @RequestBody AluguelDto aluguelDto) {
        ResponseEntity response = aluguelService.alterarCadastroAluguel(aluguelDto, idAluguel);
        return response;
    }
    //@PutMapping("/{idAluguel}")
    //public ResponseEntity<Aluguel> alterarStatusAluguel(@PathVariable Long idAluguel, @RequestBody AluguelDto alugueldto) {
      //  ResponseEntity response = aluguelService.alterarStatusAluguel(alugueldto, idAluguel);
        //return response;
    //}

    @DeleteMapping("/{idAluguel}")
    public void deleteById(@PathVariable Long idAluguel){
        aluguelService.deleteById(idAluguel) ;
    }

}