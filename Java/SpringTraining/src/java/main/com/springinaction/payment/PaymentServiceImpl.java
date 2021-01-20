package com.springinaction.payment;

import org.apache.log4j.Logger;


public class PaymentServiceImpl implements PaymentService {
  Logger LOGGER  = Logger.getLogger(PaymentServiceImpl.class);

  public String authorizeCreditCard(String cardNumber, 
      String cardHolderName, int expireMonth, int expireYear, 
      float amount) throws AuthorizationException {

    // Perform authorization logic
    LOGGER.info("PERFORMING AUTHORIZATION");
    LOGGER.info("   - card number: " + cardNumber);
    LOGGER.info("   - card holder: " + cardHolderName);
    LOGGER.info("   - expires: " + expireMonth + "/" + expireYear);
    LOGGER.info("   - amount:  " + amount);
    
    return System.currentTimeMillis()+""; // return auth code
  }

  public void settlePayment(String authCode, int merchantNumber, 
      float amount) throws SettlementException {

    // Perform settlement logic
    LOGGER.info("PERFORMING SETTLEMENT");
    LOGGER.info("   - authorization code: " + authCode);
    LOGGER.info("   - merchantNumber: " + merchantNumber);
    LOGGER.info("   - amount: " + amount);
  }
}
