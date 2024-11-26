package com.api.aluguel.Controller;

import com.api.aluguel.Repository.AluguelRepository;
import com.api.aluguel.Repository.CarroRepository;
import com.api.aluguel.Repository.ClienteRepository;
import com.api.aluguel.Repository.ColaboradorRepository;
import com.api.aluguel.dto.AluguelDto;
import com.api.aluguel.dto.ClienteDto;
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
@RequestMapping(value = "/api")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/carro")
    public ResponseEntity<Carro> create(@RequestBody Carro carro){
    Carro NovoCarro = aluguelService.salvar(carro);
    return ResponseEntity.status(HttpStatus.CREATED).body(NovoCarro);
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        Cliente NovoCliente = aluguelService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(NovoCliente);
    }

    @PostMapping("/colaborador")
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador){
        Colaborador NovoColaborador = aluguelService.salvar(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(NovoColaborador);
    }

    @PostMapping("/aluguel")
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
    @GetMapping("/aluguel")
    public Object listaAlugueis(){
    List<Aluguel> listaAluguel = aluguelRepository.findAll();
    return listaAluguel;
    }

    @GetMapping("/carro")
    public Object listaCarros(){
        List<Carro> listaCarros = carroRepository.findAll();
        return listaCarros;
    }
    @GetMapping("/cliente")
    public Object listaClientes(){
        List<Cliente> listaClientes = clienteRepository.findAll();
        return listaClientes;
    }

    @GetMapping("/colaborador")
    public Object listaColaboradores(){
        List<Colaborador> listaColaboradores = colaboradorRepository.findAll();
        return listaColaboradores;
    }
    @PutMapping("/cliente/{idCliente}")
    public ResponseEntity<Cliente> alterarCadastroCliente(@PathVariable Long idCliente, @RequestBody ClienteDto clienteDto) {
        ResponseEntity response = aluguelService.alterarCadastroCliente(clienteDto, idCliente);
        return response;
    }

    @PutMapping("/aluguel/{idAluguel}")
    public ResponseEntity<Aluguel> alterarCadastroAluguel(@PathVariable Long idAluguel, @RequestBody AluguelDto aluguelDto) {
        ResponseEntity response = aluguelService.alterarCadastroAluguel(aluguelDto, idAluguel);
        return response;
    }

    @DeleteMapping("/aluguel/{idAluguel}")
    public void deleteById(@PathVariable Long idAluguel){
        aluguelService.deleteById(idAluguel) ;
    }

}