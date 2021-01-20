package com.springinaction.payment;


public interface PaymentService {
  public String authorizeCreditCard(String cardNumber,
      String cardHolderName, int expireMonth, int expireYear,
      float amount) throws AuthorizationException;
  
  public void settlePayment(String authCode, int merchantNumber,
      float amount) throws SettlementException;
}
