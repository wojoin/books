package com.springinaction.training.model;

import java.util.Set;

/**
 * @hibernate.joined-subclass
 * 
 * @hibernate.joined-subclass-key
 *   column = "login"
 */
public class Instructor extends User{
  private String firstName;
  private String middleName;
  private String lastName;
  private String bio;
  
  private Set courses;
  
  public Instructor() {
  }

  
  /**
   * @hibernate.property
   *     length="15"
   *     not-null="true"
   */
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }  

  /**
   * @hibernate.property
   *     length="15"
   */
  public String getMiddleName() {
    return middleName;
  }
  
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  
  /**
   * @hibernate.property
   *     length="15"
   *     not-null="true"
   */
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * @hibernate.set
   *     lazy="true"
   *     cascade="none"
   * 
   * @hibernate.collection-key
   *     column="instructor"
   * 
   * @hibernate.collection-one-to-many
   *     class="com.springinaction.training.model.Course"
   */
  public Set getCourses() {
    return courses;
  }
  
  public void setCourses(Set courses) {
    this.courses = courses;
  }
  
  /**
   * @hibernate.property
   *     length="4000"
   */
  public String getBio() {
    return bio;
  }
  
  public void setBio(String bio) {
    this.bio = bio;
  }
}
