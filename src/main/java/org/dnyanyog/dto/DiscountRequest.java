package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class DiscountRequest {
  private int age;
  private String gender;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
