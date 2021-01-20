package com.springinaction.training.dao.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;


public class SimpleDataFieldMaxValueIncrementer
    implements DataFieldMaxValueIncrementer {
    
  private int value = 1;

	public int nextIntValue() throws DataAccessException {
    return value++;
  }

  public long nextLongValue() throws DataAccessException {
    return nextIntValue();
  }

  public String nextStringValue() throws DataAccessException {
    return "" + nextIntValue();
  }

}
