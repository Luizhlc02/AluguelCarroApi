package com.api.aluguel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NovaSenhaDto {
    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;
}
