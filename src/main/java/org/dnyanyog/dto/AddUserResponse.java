package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class AddUserResponse {

  private String status;
  private String message;
  ;
  private String userName;
  private String password;
  private String emailId;
  private int age;
  private int userId;
  private int userCode;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

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

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getUserCode() {
    return userCode;
  }

  public void setUserCode(int userCode) {
    this.userCode = userCode;
  }
}
