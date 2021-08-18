package com.nallani.utils;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

@Service
public class GetPublicKey {

    // Certificate should be PEM
    // CSR will not work
    public PublicKey publicKeyFromFile(){
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            FileInputStream fileInputStream = new FileInputStream("request.pem");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
            //System.out.println(certificate);
            System.out.println(certificate.getPublicKey());
            return certificate.getPublicKey();
        }catch (Exception e){
            System.out.println("public key error");
        }
        return null;
    }
    // Certificate should be PEM
    // CSR will not work
    public X509Certificate publicKeyFromString(String certString) throws CertificateException {
        byte[] decoded = Base64.getDecoder().decode(certString);
        X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(decoded));
        System.out.println(certificate);
        return certificate;
    }

}
