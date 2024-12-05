package com.api.aluguel.Service;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Repository.ClienteRepository;
import com.api.aluguel.dto.ClienteDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Cliente salvar(Cliente clientes) {
        clientes.setSenhaCliente(passwordEncoder.encode(clientes.getSenhaCliente()));
        return clienteRepository.save(clientes);
    }

    @Transactional
    public Cliente esqueciMinhaSenha(Long idCliente, String senhaAtual, String novaSenha, String confirmarSenha){
        if(!novaSenha.equals(confirmarSenha)){
            System.out.println("A senhas não são iguais");
        }
        Cliente cliente = buscarPorId(idCliente);
        if(!passwordEncoder.matches(senhaAtual, cliente.getSenhaCliente())){
            System.out.println("Sua senha não confere");
        }
        cliente.setSenhaCliente(passwordEncoder.encode(novaSenha));
        return cliente;
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

    public Cliente buscarPorId(Long idCliente) {
        return clienteRepository.getClienteByIdCliente(idCliente);
    }
    public Cliente buscarPorCliente(String emailCliente) {
        return clienteRepository.findByemailCliente(emailCliente).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente %s não encontrado", emailCliente)));
    }

    public Cliente.Role buscarRolePorCliente(String emailCliente) {
        return clienteRepository.findByRoleCliente(emailCliente);
    }
}
