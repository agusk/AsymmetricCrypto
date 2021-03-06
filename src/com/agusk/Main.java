package com.agusk;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Main {

    public static void main(String[] args) throws Exception {
        RSA rsa = new RSA();
        System.out.println("generating public and private keys");
        rsa.generateKey("d:/temp/private.key", "d:/temp/public.key");

        String message = "Welcome to Java";
        System.out.println("encrypting");
        PublicKey publicKey = rsa.getPublicKey("d:/temp/public.key");
        byte[] encrypted = rsa.encrypt(message, publicKey);

        System.out.println("decrypting");
        PrivateKey privateKey = rsa.getPrivateKey("d:/temp/private.key");
        String plainText = rsa.decrypt(encrypted, privateKey);

        System.out.println("Message: " + message);
        System.out.println("Encrypted: " + new String(encrypted));
        System.out.println("Encrypted Hex: " + StringUtils.getHexString(encrypted));
        System.out.println("Plaintext: " + plainText);
    }
}
