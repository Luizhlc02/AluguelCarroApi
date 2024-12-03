package com.api.aluguel.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {
    @JsonProperty("nomeCliente")
    private String nomeCliente;

    @JsonProperty("cpfCliente")
    private String cpfCliente;

    @JsonProperty("emailCliente")
    private String emailCliente;

    @JsonProperty("telefoneCliente")
    private String telefoneCliente;

    @JsonProperty("senhaCliente")
    private String senhaCliente;

    @JsonProperty("role")
    private String role;
}
