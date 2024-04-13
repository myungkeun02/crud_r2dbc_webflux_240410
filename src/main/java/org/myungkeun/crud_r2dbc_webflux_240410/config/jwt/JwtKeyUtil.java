package org.myungkeun.crud_r2dbc_webflux_240410.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component

public class JwtKeyUtil {
    @Value("${application.security.jwt.secretkey}")
    private String secretKey;

    public Key getPrivateKey() {
        try {
            byte[] privateKeyByte = Base64.getDecoder().decode(secretKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyByte);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}