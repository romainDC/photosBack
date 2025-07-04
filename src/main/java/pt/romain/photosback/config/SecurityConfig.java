package pt.romain.photosback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
                // API publique accessible à tous
                .csrf(AbstractHttpConfigurer::disable);
        // Désactive CSRF pour simplifier les tests avec curl.
        // Pour une vraie API, il faudrait un mécanisme de token CSRF ou JWT.
        return http.build();
    }
}
