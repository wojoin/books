package com.springinaction.training.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.springinaction.training.model.PaySettlement;


public class PaySettlementConverter implements MessageConverter {
  public PaySettlementConverter() {}
  
  public Object fromMessage(Message message) throws MessageConversionException {
    MapMessage mapMessage = (MapMessage) message;
    PaySettlement settlement = new PaySettlement();

    try {
      settlement.setAuthCode(mapMessage.getString("authCode"));
      settlement.setCreditCardNumber(mapMessage.getString("creditCardNumber"));
      settlement.setCustomerName(mapMessage.getString("customerName"));
      settlement.setExpirationMonth(mapMessage.getInt("expirationMonth"));
      settlement.setExpirationYear(mapMessage.getInt("expirationYear"));
    } catch (JMSException e) {
      throw new MessageConversionException(e.getMessage());
    }
    
    return settlement;
  }
  
  public Message toMessage(Object object, Session session) throws JMSException,
      MessageConversionException {
    
    PaySettlement settlement = (PaySettlement) object;
    MapMessage message = session.createMapMessage();
    message.setString("authCode", settlement.getAuthCode());
    message.setString("customerName", settlement.getCustomerName());
    message.setString("creditCardNumber", settlement.getCreditCardNumber());
    message.setInt("expirationMonth", settlement.getExpirationMonth());
    message.setInt("expirationYear", settlement.getExpirationYear());

    return message;
  }
}
