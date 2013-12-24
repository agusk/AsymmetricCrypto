package com.agusk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class RSA {
    public void generateKey(String privateKeyFile, String publicKeyFile) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair key = keyGen.generateKeyPair();

            File privateFile = new File(privateKeyFile);
            File publicFile = new File(publicKeyFile);

            privateFile.createNewFile();
            publicFile.createNewFile();

            // Saving the Public key in a file
            ObjectOutputStream fPub = new ObjectOutputStream(
                    new FileOutputStream(publicFile));
            fPub.writeObject(key.getPublic());
            fPub.close();

            // Saving the Private key in a file
            ObjectOutputStream fPriv = new ObjectOutputStream(
                    new FileOutputStream(privateKeyFile));
            fPriv.writeObject(key.getPrivate());
            fPriv.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public PublicKey getPublicKey(String keyFile) {
        PublicKey publicKey = null;

        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(keyFile));
            publicKey = (PublicKey) in.readObject();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return publicKey;

    }

    public PrivateKey getPrivateKey(String keyFile) {
        PrivateKey privateKey = null;

        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(keyFile));
            privateKey = (PrivateKey) in.readObject();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return privateKey;
    }

    public byte[] encrypt(String message, PublicKey key) {
        byte[] cipherText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(message.getBytes());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return cipherText;
    }

    public String decrypt(byte[] encrypted, PrivateKey key) {
        byte[] dectyptedText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(encrypted);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return new String(dectyptedText);
    }
}
