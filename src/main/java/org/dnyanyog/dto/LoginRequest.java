package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginRequest {
  private String userName;
  private String password;
  private String tempPassword;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTempPassword() {
    return tempPassword;
  }

  public void setTempPassword(String tempPassword) {
    this.tempPassword = tempPassword;
  }
}
