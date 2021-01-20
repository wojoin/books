package com.springinaction.training.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @hibernate.class
 */
public class User {
  private String login;
  private String password;
  private Set authorities;
  
  public User() {
    authorities = new HashSet();
  }
  
  /**
   * @hibernate.id
   *     generator-class="assigned"
   *     length="20"
   */
  public String getLogin() {
    return login;
  }
  
  public void setLogin(String userName) {
    this.login = userName;
  }
  
  /**
   * @hibernate.property
   *     length="20"
   */
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  
  /**
   * @hibernate.set
   *     table="authorities"
   *     lazy="true"
   *     cascade="all"
   * 
   * @hibernate.collection-key
   *     column="username"
   * 
   * @hibernate.collection-composite-element
   *     class="com.springinaction.training.model.Authority"
   */
  public Set getAuthorities() {
    return authorities;
  }
  
  public void setAuthorities(Set authorities) {
    this.authorities = authorities;
  }
}
