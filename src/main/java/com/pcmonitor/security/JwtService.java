
package com.pcmonitor.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

import java.time.Instant;
import java.util.List;

@Service
public class JwtService {

        private final JwtEncoder jwtEncoder;

        public JwtService(JwtEncoder jwtEncoder) {
                this.jwtEncoder = jwtEncoder;
        }

        @Value("${jwt.expiration}")
        private long expiration;

        public String gerarToken(Authentication authentication) {

                Instant agora = Instant.now();

                List<String> roles = authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

                JwtClaimsSet claims = JwtClaimsSet.builder()
                        .issuer("pc-monitor-api")
                        .subject(authentication.getName())
                        .claim("roles", roles)
                        .issuedAt(agora)
                        .expiresAt(agora.plusMillis(expiration))
                        .build();

                JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

                return jwtEncoder.encode(
                        JwtEncoderParameters.from(header, claims)
                ).getTokenValue();
        }
}
