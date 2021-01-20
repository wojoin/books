package com.springinaction.training.model;

import java.util.Set;

/**
 * @hibernate.joined-subclass
 * 
 * @hibernate.joined-subclass-key
 *   column = "login"
 */
public class Student extends User {
  private String firstName;
  private String middleName;
  private String lastName;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String phone;
  private String email;
  private Set courses;
  
  public Student() {}
  
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
   * @hibernate.property
   *     length="20"
   *     not-null="true"
   */
  public String getAddress1() {
    return address1;
  }
  
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  
  /**
   * @hibernate.property
   *     length="20"
   */
  public String getAddress2() {
    return address2;
  }
  
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  
  /**
   * @hibernate.property
   *     length="15"
   *     not-null="true"
   */
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @hibernate.property
   *     length="2"
   *     not-null="true"
   */
  public String getState() {
    return state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  /**
   * @hibernate.property
   *     length="5"
   *     not-null="true"
   */
  public String getZip() {
    return zip;
  }
  
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * @hibernate.property
   *     length="10"
   *     not-null="true"
   */
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
    
  /**
   * @hibernate.property
   *     length="25"
   *     not-null="true"
   */
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * @hibernate.set
   *     lazy="true"
   *     cascade="none"
   *     table="transcript"
   *
   * @hibernate.collection-key
   *     column="student_id"
   * 
   * @hibernate.collection-many-to-many
   *     class="com.springinaction.training.model.Course"
   *     column="course_id"
   */
  public Set getCourses() {
    return courses;
  }
  
  public void setCourses(Set courses) {
    this.courses = courses;
  }
}
