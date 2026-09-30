package com.tickethub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Propiedades propias de la app (bloque "app:" en application.yml), tipadas en un solo lugar
 * en vez de @Value sueltos por todo el código.
 */
@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String frontendUrl,
        String mailFrom,
        Jwt jwt,
        Storage storage,
        Attachments attachments
) {

    public record Jwt(String secret, long expirationMinutes) {
    }

    public record Storage(String endpoint, String region, String bucket, String accessKey, String secretKey) {
    }

    public record Attachments(int maxFilesPerTicket, List<String> allowedTypes) {
    }
}
