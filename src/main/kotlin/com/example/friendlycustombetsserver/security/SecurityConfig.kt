package com.example.friendlycustombetsserver.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Value("\${auth0.audience}")
    private val audience: String? = null

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuer: String? = null

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET","POST")
        configuration.allowedHeaders = listOf("Origin", "Content-Type", "Authorization", "Access-Control-Allow-Credentials")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
            .mvcMatchers("/tournament/myTournaments").authenticated()
            .and().cors()
            .and().oauth2ResourceServer().jwt().decoder(jwtDecoder())

        return http.build()
    }

    fun jwtDecoder(): JwtDecoder? {
        //val withAudience: OAuth2TokenValidator<Jwt> = AudienceValidator(audience) TODO: j'ai pas tout compris de comment ça marche ça
        val withIssuer: OAuth2TokenValidator<Jwt> = JwtValidators.createDefaultWithIssuer(issuer)
        val validator: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer)
        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation<JwtDecoder>(issuer) as NimbusJwtDecoder
        jwtDecoder.setJwtValidator(validator)
        return jwtDecoder
    }
}