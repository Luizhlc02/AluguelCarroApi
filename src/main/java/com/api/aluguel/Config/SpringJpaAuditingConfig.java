package com.api.aluguel.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class SpringJpaAuditingConfig implements AuditorAware<String> {

//Usado para pegar informações como horario do aluguel, mas precisa da alteração de string para long para pegar por id
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            return Optional.of(authentication.getName());
        }
        return null;
    }
}
