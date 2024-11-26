package com.api.aluguel.Service;
import com.api.aluguel.Entity.*;
import com.api.aluguel.Repository.*;
import com.api.aluguel.dto.AluguelDto;
import com.api.aluguel.dto.ClienteDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.Optional;

@Service
public class AluguelService {

    public Page<Aluguel> findAll(Pageable pageable) {
        return aluguelRepository.findAll(pageable);
    }

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private AluguelRepository aluguelRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public Cliente salvar(Cliente clientes) {
        return clienteRepository.save(clientes);
    }
    @Transactional
    public Colaborador salvar(Colaborador colaboradores) {
        return colaboradorRepository.save(colaboradores);
    }
    @Transactional
    public Carro salvar(Carro carros) {
        return carroRepository.save(carros);
    }
    @Transactional
    public Aluguel salvar(AluguelDto aluguelDto){
        Aluguel aluguel = new Aluguel();
        aluguel.setDataAluguel(aluguelDto.getDataAluguel());
        aluguel.setDataVencimento(aluguelDto.getDataVencimento());

        Carro carro = aluguelDto.getIdCarro()!=null ? carroRepository.getCarroByIdCarro(aluguelDto.getIdCarro()) : null;
        Cliente cliente = aluguelDto.getIdCliente()!=null ? clienteRepository.getClienteByIdCliente(aluguelDto.getIdCliente()) : null;
        Colaborador colaborador = aluguelDto.getIdColaborador()!=null ? colaboradorRepository.getColaboradorByIdColaborador(aluguelDto.getIdColaborador()) : null;
        aluguel.setCarro(carro);
        aluguel.setCliente(cliente);
        aluguel.setColaborador(colaborador);
        return aluguelRepository.save(aluguel);
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

    public ResponseEntity alterarCadastroAluguel(AluguelDto aluguelDto, Long idAluguel) {
        Optional<Aluguel> aluguelSalvo;
        try {
            aluguelSalvo = aluguelRepository.findById(idAluguel);
            if (aluguelSalvo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        Aluguel aluguel = aluguelSalvo.get();

        aluguel.setDataVencimento(aluguelDto.getDataVencimento());
        aluguel.setDataAluguel(aluguelDto.getDataAluguel());
        Optional<Carro> carroOptional = carroRepository.findById(aluguelDto.getIdCarro());
        Optional<Cliente> clienteOptional = clienteRepository.findById(aluguelDto.getIdCliente());
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(aluguelDto.getIdColaborador());
    
        if (carroOptional.isEmpty() || clienteOptional.isEmpty() || colaboradorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro, Cliente ou Colaborador não encontrado");
        }
        aluguel.setCarro(carroOptional.get());
        aluguel.setCliente(clienteOptional.get());
        aluguel.setColaborador(colaboradorOptional.get());

        return ResponseEntity.ok(aluguelRepository.save(aluguel));
    }


    public void deleteById(Long idCliente){
        if (clienteRepository.existsById(idCliente)){
            aluguelRepository.deleteById(idCliente);
        }
    }
}
    

