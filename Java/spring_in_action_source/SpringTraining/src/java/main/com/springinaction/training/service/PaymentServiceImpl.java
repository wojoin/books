package com.springinaction.training.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.springinaction.training.model.PaySettlement;


public class PaymentServiceImpl {
  private JmsTemplate jmsTemplate;
  public void setJmsTemplate(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }
  
  public void sendSettlementMessage(final PaySettlement settlement) {
    jmsTemplate.send(
        new MessageCreator() {
          public Message createMessage(Session session) throws JMSException {

            MapMessage message = session.createMapMessage();
            message.setString("authCode", settlement.getAuthCode());
            message.setString("customerName", settlement.getCustomerName());
            message.setString("creditCardNumber", settlement.getCreditCardNumber());
            message.setInt("expirationMonth", settlement.getExpirationMonth());
            message.setInt("expirationYear", settlement.getExpirationYear());

            return message;
          }
        }
    );
  }
  
  public void sendSettlementMessage_2(PaySettlement settlement) {
    jmsTemplate.convertAndSend(settlement);
  }
}
