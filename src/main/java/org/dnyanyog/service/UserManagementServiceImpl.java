package org.dnyanyog.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {
  @Autowired UsersRepository repo;

  @Autowired EncryptionService encryptionService;

  @Autowired AddUserResponse response;

  public AddUserResponse addUser(AddUserRequest request) throws Exception {

    String tempKey = generateAESKey();
    Users userTable =
        Users.getInstance()
            .setUserName(request.getUserName())
            .setPassword(encryptAES(request.getPassword(), tempKey))
            .setEmail_id(request.getEmailId())
            .setAge(request.getAge())
            .setUser_id(request.getUserId());

    try {
      userTable = repo.save(userTable);
    } catch (Exception e) {
      e.printStackTrace();
    }

    response.setStatus("Success");
    response.setMessage("User added successfully!!");
    response.setUserCode(userTable.getUser_code());
    return response;
  }

  public AddUserResponse searchUser(int userId) {
    AddUserResponse response = new AddUserResponse();
    Optional<Users> userTable = repo.findById(userId);

    if (userTable.isEmpty()) {
      response.setStatus("Fail");
      response.setMessage("User not found");
      response.setUserCode(0000);
    } else {
      Users user = userTable.get();
      response.setStatus("Success");
      response.setMessage("User found");
      response.setUserCode(user.getUser_code());
      response.setUserName(user.getUserName());
      response.setPassword(decryptAES(user.getPassword(), user.getAesKey()));
      response.setEmailId(user.getEmail_id());
      response.setAge(user.getAge());
      response.setUserId(user.getUser_id());
    }
    return response;
  }

  public List<Users> getUser() {
    return repo.findAll();
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
