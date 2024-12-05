package com.api.aluguel.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
