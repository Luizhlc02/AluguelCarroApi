package com.api.aluguel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Tb_Carro")
@ToString(exclude = {"alugueis"})
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarro")
    private Long idCarro;

    @Column(name = "modeloCarro")
    private String modeloCarro;

    @Column(name = "motorCarro")
    private String motorCarro;

    @Column(name = "kmRodado")
    private String kmRodado;

    @Column(name = "anoFabricado")
    private String anoFabricado;

    @JsonIgnore
    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL)
    private List<Aluguel> alugueis;
}
