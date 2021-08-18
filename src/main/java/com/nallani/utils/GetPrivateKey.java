package com.nallani.utils;

import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Service
public class GetPrivateKey {

    //WORKING
    public PrivateKey createPrivateKeyFromFileWithBouncy(){
        PEMParser reader = null;
        try{
            reader = new PEMParser(new FileReader("key.pem"));
            PEMKeyPair keys =(PEMKeyPair) reader.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            PrivateKey privateKey = converter.getPrivateKey(keys.getPrivateKeyInfo());
            System.out.println(privateKey);
            return privateKey;
        }catch (Exception e){
            System.out.println("private key error");
        }
        return null;
    }
    // works if secret is PKSC8 format
    // only PKSC8
    public PrivateKey getPrivateKeyForPKCS8() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        KeyFactory factory = KeyFactory.getInstance("RSA");
        File file = new File("key-pkcs8.txt");
        try (FileReader keyReader = new FileReader(file); PemReader pemReader = new PemReader(keyReader)) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(content);
            PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
            System.out.println(privateKey);
            return privateKey;
        }
    }
}
