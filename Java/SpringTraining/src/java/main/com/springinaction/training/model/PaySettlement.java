package com.springinaction.training.model;


public class PaySettlement {
  private String authCode;
  private String customerName;
  private String creditCardNumber;
  private int expirationMonth;
  private int expirationYear;
  
  public PaySettlement() {}
  
  public PaySettlement(String authCode, String customerName,
      String creditCardNumber, int expirationMonth, 
      int expirationYear) {
    this.authCode = authCode;
    this.customerName = customerName;
    this.creditCardNumber = creditCardNumber;
    this.expirationMonth = expirationMonth;
    this.expirationYear = expirationYear;
  }

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }
  
  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }
  
  public String getCustomerName() {
    return customerName;
  }
  
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  
  public int getExpirationMonth() {
    return expirationMonth;
  }
  
  public void setExpirationMonth(int expirationMonth) {
    this.expirationMonth = expirationMonth;
  }
  
  public int getExpirationYear() {
    return expirationYear;
  }
  
  public void setExpirationYear(int expirationYear) {
    this.expirationYear = expirationYear;
  }
}
