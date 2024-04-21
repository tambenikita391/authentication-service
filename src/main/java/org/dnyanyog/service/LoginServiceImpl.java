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
import org.dnyanyog.entity.UsersDetails;
import org.dnyanyog.repo.UsersDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginServiceImpl implements LoginService {

  @Autowired UsersDetailsRepository repo;

  @Autowired EncryptionService encryptionService;

  public LoginResponse validateUser(LoginRequest request) throws Exception {
    LoginResponse response = new LoginResponse();

    List<UsersDetails> user = repo.findByUserNameAndPassword(request.getUserName(), request.getPassword());

    if (user.size() == 1) {
    	response.setStatus("Success");
      response.setMessage("Login successful ");

    } else {
    	response.setStatus("Fail");
      response.setMessage("Login failed ");
    }
    return response;
  }

}