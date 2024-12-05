package com.api.aluguel.Service;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Entity.Colaborador;
import com.api.aluguel.Repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Colaborador salvar(Colaborador colaboradores) {
        colaboradores.setSenhaColaborador(passwordEncoder.encode(colaboradores.getSenhaColaborador()));
        return colaboradorRepository.save(colaboradores);
    }

    @Transactional
    public Colaborador esqueciMinhaSenha(Long idColaborador, String senhaAtual, String novaSenha, String confirmarSenha){
        if(!novaSenha.equals(confirmarSenha)){
            System.out.println("A senhas não são iguais");
        }
        Colaborador colaborador = buscarPorId(idColaborador);
        if(!passwordEncoder.matches(senhaAtual, colaborador.getSenhaColaborador())){
            System.out.println("Sua senha não confere");
        }
        colaborador.setSenhaColaborador(passwordEncoder.encode(novaSenha));
        return colaborador;
    }

    public Colaborador buscarPorId(Long idColaborador) {
        return colaboradorRepository.getColaboradorByIdColaborador(idColaborador);
    }
}
