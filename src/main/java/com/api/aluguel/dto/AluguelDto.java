package com.api.aluguel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AluguelDto {

    @JsonProperty("dataAluguel")
    private Date dataAluguel;

    @JsonProperty("dataVencimento")
    private Date dataVencimento;

    @JsonProperty("idCarro")
    private Long idCarro ;

    @JsonProperty("idCliente")
    private Long idCliente;

    @JsonProperty("idColaborador")
    private Long idColaborador;

    @JsonProperty("statusAluguel")
    private boolean statusAluguel;
}
