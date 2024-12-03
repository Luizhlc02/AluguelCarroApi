package com.api.aluguel.Service;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Repository.ClienteRepository;
import com.api.aluguel.dto.ClienteDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente clientes) {
        return clienteRepository.save(clientes);
    }

    @Transactional
    public ResponseEntity alterarCadastroCliente(ClienteDto clienteDto, Long idCliente) {
        Optional<Cliente> clienteSalvo;
        try {
            clienteSalvo = clienteRepository.findById(idCliente);
            if (clienteSalvo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        Cliente cliente = clienteSalvo.get();

        cliente.setTelefoneCliente(clienteDto.getTelefoneCliente());
        cliente.setNomeCliente(clienteDto.getNomeCliente());
        cliente.setEmailCliente(clienteDto.getEmailCliente());
        cliente.setCpfCliente(clienteDto.getCpfCliente());

        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    public Cliente buscarPorCliente(String nomeCliente) {
        return clienteRepository.findByCliente(nomeCliente).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente %s não encontrado", nomeCliente)));
    }

    public Cliente.Role buscarRolePorCliente(String nomeCliente) {
        return clienteRepository.findByRoleCliente(nomeCliente);
    }
}
