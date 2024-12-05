/*
package com.api.aluguel.Config;

import com.api.aluguel.Entity.Colaborador;
import com.api.aluguel.Repository.ClienteRepository;
import com.api.aluguel.Repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var roleAdmin = colaboradorRepository.findByNomeColaborador(Colaborador.Role.ROlE_ADMIN.name());

        var userAdmin = colaboradorRepository.findByEmailColaborador("admin");

        userAdmin.ifPresentOrElse(
                 colaborador -> System.out.println("admin ja existe"),
                () -> {
                    var colaborador = new Colaborador();
                    colaborador.setEmailColaborador("admin");
                    colaborador.setSenhaColaborador(bCryptPasswordEncoder.encode("123"));
                    colaborador.setRole(Colaborador.Role.ROlE_ADMIN);
                    colaboradorRepository.save(colaborador);
                }
        );

    }
}
*/
