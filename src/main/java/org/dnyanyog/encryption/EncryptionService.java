package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class EncryptionService {
  private String encryptAES(String input, String key) {
    try {
      Cipher cipher = Cipher.getInstance("AES");
      SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
      byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(encryptedBytes);
    } catch (Exception e) {
      throw new RuntimeException("Error encrypting with AES", e);
    }
  }

  private String generateAESKey() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      SecretKey secretKey = keyGen.generateKey();
      byte[] encodedKey = secretKey.getEncoded();
      return Base64.getEncoder().encodeToString(encodedKey);
    } catch (Exception e) {
      throw new RuntimeException("Error generating AES key", e);
    }
  }

  public String decryptAES(String encryptedInput, String key) {
    try {
      Cipher cipher = Cipher.getInstance("AES");
      SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
      byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedInput));
      return new String(decryptedBytes, StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Error decrypting with AES", e);
    }
  }
}
