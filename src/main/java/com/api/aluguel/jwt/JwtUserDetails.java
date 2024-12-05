/*

package com.api.aluguel.jwt;

import com.api.aluguel.Entity.Cliente;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private Cliente cliente;

    public JwtUserDetails(Cliente cliente){
        super(cliente.getEmailCliente(), cliente.getSenhaCliente(), AuthorityUtils.createAuthorityList(cliente.getRole().name()));
        this.cliente = cliente;
    }

    public Long getIdCliente(){
        return cliente.getIdCliente();
    }

    public String getRoleCliente(){
        return this.cliente.getRole().name();
    }
}

*/
