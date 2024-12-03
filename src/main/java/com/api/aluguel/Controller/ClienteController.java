package com.api.aluguel.Controller;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Repository.ClienteRepository;
import com.api.aluguel.Service.ClienteService;
import com.api.aluguel.dto.ClienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value= "/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        Cliente NovoCliente = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(NovoCliente);
    }

    @GetMapping
    public Object listaClientes(){
        List<Cliente> listaClientes = clienteRepository.findAll();
        return listaClientes;
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> alterarCadastroCliente(@PathVariable Long idCliente, @RequestBody ClienteDto clienteDto) {
        ResponseEntity response = clienteService.alterarCadastroCliente(clienteDto, idCliente);
        return response;
    }
}
