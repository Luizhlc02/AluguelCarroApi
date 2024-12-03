package com.api.aluguel.Entity;

import jakarta.persistence.*;
import lombok.*;


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

    public enum Role{
        ROlE_ADMIN, ROLE_CLIENTE
    }
}
