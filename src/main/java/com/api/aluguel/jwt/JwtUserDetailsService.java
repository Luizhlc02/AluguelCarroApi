package com.api.aluguel.jwt;

import com.api.aluguel.Entity.Cliente;
import com.api.aluguel.Service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private ClienteService clienteService;

    @Override
    //vai fazer uma consulta pelo nome do cliente se achar retorna no valor de UserDetails para o string poder gerenciar a sessão
    public UserDetails loadUserByUsername(String emailCliente) throws UsernameNotFoundException {
        Cliente cliente = clienteService.buscarPorCliente(emailCliente);
        return new JwtUserDetails(cliente);
    }
    //Metodo para fazer a autenticação, gera o token pelo createtoken
    public JwtToken getTokenAuthenticated(String emailCliente){
        Cliente.Role role = clienteService.buscarRolePorCliente(emailCliente);
        return JwtUtils.createToken(emailCliente, role.name().substring("ROLE_".length()));
    }
}
