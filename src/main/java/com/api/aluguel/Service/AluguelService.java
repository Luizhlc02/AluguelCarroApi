package com.api.aluguel.Service;
import com.api.aluguel.Entity.*;
import com.api.aluguel.Repository.*;
import com.api.aluguel.dto.AluguelDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
    public Aluguel salvar(AluguelDto aluguelDto){
        Aluguel aluguel = new Aluguel();
        aluguel.setDataAluguel(aluguelDto.getDataAluguel());
        aluguel.setDataVencimento(aluguelDto.getDataVencimento());
        aluguel.setStatusAluguel(aluguelDto.isStatusAluguel());

        Carro carro = aluguelDto.getIdCarro()!=null ? carroRepository.getCarroByIdCarro(aluguelDto.getIdCarro()) : null;
        Cliente cliente = aluguelDto.getIdCliente()!=null ? clienteRepository.getClienteByIdCliente(aluguelDto.getIdCliente()) : null;
        Colaborador colaborador = aluguelDto.getIdColaborador()!=null ? colaboradorRepository.getColaboradorByIdColaborador(aluguelDto.getIdColaborador()) : null;
        aluguel.setCarro(carro);
        aluguel.setCliente(cliente);
        aluguel.setColaborador(colaborador);
        return aluguelRepository.save(aluguel);
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

        if (aluguelDto.getDataVencimento() != null) {
            aluguel.setDataVencimento(aluguelDto.getDataVencimento());
        }

        if (aluguelDto.getDataAluguel() != null) {
            aluguel.setDataAluguel(aluguelDto.getDataAluguel());
        }

        if (aluguelDto.getIdCarro()!=null) {
            Optional<Carro> carroOptional = carroRepository.findById(aluguelDto.getIdCarro());
            if (!carroOptional.isEmpty()) {
                aluguel.setCarro(carroOptional.get());
            }
        }
        if (aluguelDto.getIdCliente()!=null) {
            Optional<Cliente> clienteOptional = clienteRepository.findById(aluguelDto.getIdCliente());
            if (!clienteOptional.isEmpty()) {
                aluguel.setCliente(clienteOptional.get());
            }
        }
        if (aluguelDto.getIdColaborador()!=null) {
            Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(aluguelDto.getIdColaborador());
            if (!colaboradorOptional.isEmpty()) {
                aluguel.setColaborador(colaboradorOptional.get());
            }
        }
        aluguel.setStatusAluguel(false);

        return ResponseEntity.ok(aluguelRepository.save(aluguel));
    }


    public void deleteById(Long idCliente){
        if (clienteRepository.existsById(idCliente)){
            aluguelRepository.deleteById(idCliente);
        }
    }

    public Optional<Aluguel>aluguelFindbyID(Long idAluguel) {

        return aluguelRepository.findById(idAluguel);
    }



}


