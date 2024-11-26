package com.api.aluguel.Entity;

import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Tb_Colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idColaborador")
    private Long idColaborador;

    @Column(name = "nomeColaborador")
    private String nomeColaborador;

    @Column(name = "emailColaborador")
    private String emailColaborador;

    @Column(name = "cargoColaborador")
    private String cargoColaborador;

}
