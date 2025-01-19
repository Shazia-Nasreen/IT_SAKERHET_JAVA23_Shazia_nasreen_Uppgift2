package com.example.timecapsule.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.*;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    private final String SECRET_KEY = "wccffgbsdcvhhhvhycvcvfkj1111111cggchhvhvhvhhhcccchhchcchchchchchch";

    public String generateToken(String email) throws JOSEException {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) throws JOSEException {
        JWSSigner signer = new MACSigner(SECRET_KEY);

        JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder()
                .subject(subject)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)); // 10 hours

        claims.forEach(claimsSetBuilder::claim);

        // Create the JWT
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSetBuilder.build());
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public String extractEmail(String token) throws ParseException, java.text.ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            JWSVerifier verifier = new MACVerifier(SECRET_KEY);
            return signedJWT.verify(verifier);
        } catch (Exception e) {
            return false;
        }
    }
}
