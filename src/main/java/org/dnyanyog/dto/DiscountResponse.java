package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class DiscountResponse {
  private double discountPercentage;

  public double getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(double discountPercentage) {
    this.discountPercentage = discountPercentage;
  }
}
