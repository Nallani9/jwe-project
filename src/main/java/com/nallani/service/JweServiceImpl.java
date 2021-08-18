package com.nallani.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nallani.model.*;
import com.nallani.utils.GetPrivateKey;
import com.nallani.utils.GetPublicKey;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JweServiceImpl {

    @Autowired private GetPrivateKey getPrivateKey;
    @Autowired private GetPublicKey getPublicKey;


    private static final String SUBJECT = "sri";
    @Autowired private ObjectMapper mapper;    private static final String ISSUER = "nallani";

    public GenResponse generateToken(Map<String, List<String>> payload, GenRequest genRequest) throws JOSEException {
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        if (payload != null) {
            for (Map.Entry<String, List<String>> entry : payload.entrySet()) {
                builder.claim(entry.getKey(), entry.getValue());
            }
        }
        builder.expirationTime(Date.from(Instant.now().plus(30, ChronoUnit.SECONDS)));

        builder.subject(SUBJECT);
        builder.issueTime(Date.from(Instant.now()));
        builder.issuer(ISSUER);
        builder.claim("target_url", genRequest.getTarget_url());
        builder.claim("target_scope", genRequest.getTarget_scope());

        JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);
        EncryptedJWT jwt = new EncryptedJWT(header, builder.build());
        RSAEncrypter encrypter = new RSAEncrypter((RSAPublicKey) getPublicKey.publicKeyFromFile());
        jwt.encrypt(encrypter);
        GenResponse genResponse = new GenResponse();
        genResponse.setJweString(jwt.serialize());
        return genResponse;
    }

    public ValResponse validateToken(GenResponse request) throws ParseException, JOSEException {
        EncryptedJWT jwt = null;
        jwt = EncryptedJWT.parse(request.getJweString());
        RSADecrypter decrypter = new RSADecrypter(getPrivateKey.createPrivateKeyFromFileWithBouncy());
        jwt.decrypt(decrypter);

        ValResponse response = new ValResponse();
        response.setAdditionalClaims(getAdditionalClaims(jwt));
        response.setSub(jwt.getJWTClaimsSet().getSubject());
        response.setTarget_scope(getOtherClaims(jwt).getTarget_scope());
        response.setTarget_url(getOtherClaims(jwt).getTarget_url());
        return response;
    }

    private AdditionalClaims getAdditionalClaims(JOSEObject jose) {
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return mapper.convertValue(jose.getPayload().toJSONObject(), AdditionalClaims.class);
    }

    private OtherClaims getOtherClaims(JOSEObject jose) {
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return mapper.convertValue(jose.getPayload().toJSONObject(), OtherClaims.class);
    }
}
