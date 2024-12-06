package com.api.aluguel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteLoginDto {
    @JsonProperty("email")
    private String email;
    @JsonProperty("senha")
    private String senha;
}
