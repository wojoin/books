package com.springinaction.training.model;

public class Authority {
  private String authority;
  
  public Authority() {}
  
  public Authority(String authority) {
    this.authority = authority;
  }
  
  /**
   * @hibernate.property
   *     length="30"
   */
  public String getAuthority() {
    return authority;
  }
  
  public void setAuthority(String authority) {
    this.authority = authority;
  }
}
