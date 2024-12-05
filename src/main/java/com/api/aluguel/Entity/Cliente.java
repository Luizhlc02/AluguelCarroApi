package com.api.aluguel.Entity;

import com.api.aluguel.dto.LoginClienteRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tb_Cliente")
public class Cliente {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Long idCliente;

    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "cpfCliente")
    private String cpfCliente;

    @Column(name = "emailCliente")
    private String emailCliente;

    @Column(name = "telefoneCliente")
    private String telefoneCliente;

    @Column(name = "senhaCliente")
    private String senhaCliente;

    @Column(name = "role")
    private Role role = Role.ROLE_CLIENTE;

    public boolean isLoginCorrect(LoginClienteRequest clienteRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(clienteRequest.senhaCliente(), this.getSenhaCliente());
    }

    public enum Role{
        ROlE_ADMIN, ROLE_CLIENTE
    }
}
