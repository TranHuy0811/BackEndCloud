package com.example.demo.Component;

import com.example.demo.DB.Role;
import com.example.demo.DB.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class JWTHandler {
    @Value("${jwt.secretKey}")
    String SECRET_KEY;

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder() // Tạo body
                .subject(user.getEmail())
                .issuer("org.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildRoleClaim(user.getRoles()))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try{
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch(JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public String buildRoleClaim(Set<Role> roles) {
        StringJoiner strJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> {
                strJoiner.add(role.getName());
            });
        }
        return strJoiner.toString();
    }
}