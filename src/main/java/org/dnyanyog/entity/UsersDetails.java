package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDetails {

  @GeneratedValue @Id @Column private int user_code;

  @Column private int user_id;

  @Column(name = "user_name")
  private String userName;

  @Column private String password;

  @Column private String email_id;

  @Column private int age;

  public static UsersDetails getInstance() {
    return new UsersDetails();
  }

  public String getUserName() {
    return userName;
  }

  public UsersDetails setUserName(String userName) {
    this.userName = userName;

    return this;
  }

  public String getPassword() {
    return password;
  }

  public UsersDetails setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail_id() {
    return email_id;
  }

  public UsersDetails setEmail_id(String email_id) {
    this.email_id = email_id;
    return this;
  }

  public int getAge() {
    return age;
  }

  public UsersDetails setAge(int age) {
    this.age = age;
    return this;
  }

  public int getUser_code() {
    return user_code;
  }

  public UsersDetails setUser_code(int user_code) {
    this.user_code = user_code;
    return this;
  }

  public int getUser_id() {
    return user_id;
  }

  public UsersDetails setUser_id(int i) {
    this.user_id = i;
    return this;
  }
}
