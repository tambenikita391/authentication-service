package org.dnyanyog.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginServiceImpl implements LoginService {

  @Autowired UsersRepository repo;

  @Autowired EncryptionService encryptionService;

  public LoginResponse validateUser(LoginRequest request) throws Exception {
    LoginResponse response = new LoginResponse();

    List<Users> user =
        repo.findByUserNameAndPassword(
            request.getUserName(), decryptAES(request.getPassword(), request.getTempPassword()));

    if (user.size() == 1) {
      response.setMessage("Login successful ");

    } else {
      response.setMessage("Login failed ");
    }
    return response;
  }

  public LoginResponse addUser(LoginRequest request) throws Exception {
    LoginResponse response = new LoginResponse();
    Users users = new Users();

    String tempKey = generateAESKey();
    users.setUserName(request.getUserName());
    users.setPassword(encryptAES(request.getPassword(), tempKey));

    users = repo.save(users);
    response.setMessage("Sign up successfully !! ");
    return response;
  }

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
