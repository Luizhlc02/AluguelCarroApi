package com.api.aluguel.Security;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    //vai fazer uma consulta pelo nome do cliente se achar retorna no valor de UserDetails para o spring poder gerenciar a sessão
    public UserDetails loadUserByUsername(String emailCliente) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByemailCliente(emailCliente)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + emailCliente));
        return new ClienteAuthenticated(cliente);
    }

    /*//Metodo para fazer a autenticação, gera o token pelo createtoken
    public JwtToken getTokenAuthenticated(String emailCliente){
        log.info("Gerando token para email: {}", emailCliente);
        Cliente.Role role = clienteService.buscarRolePorCliente(emailCliente);
        log.info("Role recuperada: {}", role);
        return JwtUtils.createToken(emailCliente, role.name().substring("ROLE_".length()));
    }*/
}

