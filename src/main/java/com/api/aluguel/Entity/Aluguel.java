package com.api.aluguel.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Tb_Aluguel")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAluguel")
    private Long idAluguel;

    @Column(name = "dataAluguel")
    private Date dataAluguel;

    @Column(name = "dataVencimento")
    private Date dataVencimento;

    @ManyToOne
    @JoinColumn(name = "idCarro", referencedColumnName = "idCarro")
    public Carro carro;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    public Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idColaborador", referencedColumnName = "idColaborador")
    public Colaborador colaborador;
    

    
}
