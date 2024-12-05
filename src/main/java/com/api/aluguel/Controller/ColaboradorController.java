package com.api.aluguel.Controller;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Entity.Colaborador;
import com.api.aluguel.Repository.ColaboradorRepository;
import com.api.aluguel.Service.ColaboradorService;
import com.api.aluguel.dto.NovaSenhaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador){
        Colaborador NovoColaborador = colaboradorService.salvar(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(NovoColaborador);
    }

    @GetMapping
    public Object listaColaboradores(){
        List<Colaborador> listaColaboradores = colaboradorRepository.findAll();
        return listaColaboradores;
    }

    @PatchMapping("/{idColaborador}")
    public ResponseEntity<Colaborador> alterarSenha(@PathVariable Long idColaborador, @RequestBody NovaSenhaDto dto) {
        Colaborador colaborador = colaboradorService.esqueciMinhaSenha(idColaborador, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.ok(colaborador);
    }
}
