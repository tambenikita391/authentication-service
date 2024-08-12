package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncDec {
  public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(256);

    return keyGenerator.generateKey();
  }

  public static String encrypt(String plainText, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key); // initialization

    byte[] plainTextByte = plainText.getBytes(StandardCharsets.UTF_8);
    byte[] encrytedBytes = cipher.doFinal(plainTextByte);

    String encryptedDataInString = Base64.getEncoder().encodeToString(encrytedBytes);

    return encryptedDataInString;
  }

  public static String decrypt(String encryptedData, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);

    byte[] encryptedByteArrayData = Base64.getDecoder().decode(encryptedData);
    byte[] decryptedBytes = cipher.doFinal(encryptedByteArrayData);

    return new String(decryptedBytes, StandardCharsets.UTF_8);
  }

  public static void main(String[] args) throws Exception {
    // step 1: Generate key and store in some secure place
    SecretKey key = generateAESKey();

    String plainText = "admin123";

    // step 2: Encrypt data using generate key
    String encryptedText = encrypt(plainText, key);
    System.out.println("Encrypted : " + encryptedText);
    System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ ");

    // step 3: Decrypt data using generated key
    String decryptedText = decrypt(encryptedText, key);
    System.out.println("Decrypted : " + decryptedText);
  }
}
