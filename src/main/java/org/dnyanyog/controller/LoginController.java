package org.dnyanyog.controller;

import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  @Autowired LoginService service;

  @PostMapping("/public/auth/validate")
  public LoginResponse usersLogin(@RequestBody LoginRequest request) throws Exception {
    return service.validateUser(request);
  }
}
