package com.infotransferserver.InfoTransferServer.controller;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class Security {

    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;


    private void setPublicKeyAndPrivateKey() throws NoSuchAlgorithmException {
        if(publicKey == null && privateKey == null) {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair pair = keyGen.generateKeyPair();

            this.publicKey = pair.getPublic();

            this.privateKey = pair.getPrivate();
        }
    }


    public String encrypt(String toBeEncrypted) throws NoSuchAlgorithmException {
        String encrypted = "";

        setPublicKeyAndPrivateKey();

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedRaw = cipher.doFinal(toBeEncrypted.getBytes());
            encrypted = Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedRaw);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return encrypted;
    }

    public String decrypt(byte[] encrypted) throws NoSuchAlgorithmException {
        setPublicKeyAndPrivateKey();
        String original = "";
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            original = new String(cipher.doFinal(Base64.getUrlDecoder().decode(encrypted)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return original;
    }
}
