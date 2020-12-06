package com.infotransferserver.InfoTransferServer.controller;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class Security {

    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;

    public Security() throws NoSuchAlgorithmException {
        if(publicKey == null && privateKey == null) {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair pair = keyGen.generateKeyPair();

            publicKey = pair.getPublic();
            System.out.println(publicKey);
            privateKey = pair.getPrivate();
        }
    }

    public String encrypt(String toBeEncrypted)
    {
        String encrypted = "";

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

    public String decrypt(byte[] encrypted)
    {
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
