package com.springinaction.training.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @hibernate.class
 */
public class Course {
  private Integer id;
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private Instructor instructor;
  private Set students;
  private Set prerequisites;
  
  public Course() {
    students = new HashSet();
  }
  

  /**
   * @hibernate.id
   *     generator-class="native"
   */
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @hibernate.property
   *     length="25"
   *     not-null="true"
   */
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * @hibernate.property
   *     length="200"
   *     not-null="true"
   */
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @hibernate.many-to-one
   *     class = "com.springinaction.training.model.Instructor"
   */
  public Instructor getInstructor() {
    return instructor;
  }
  
  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }
  
  /**
   * @hibernate.property
   *     not-null="true"
   */
  public Date getStartDate() {
    return startDate;
  }
  
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /**
   * @hibernate.property
   *     not-null="true"
   */
  public Date getEndDate() {
    return endDate;
  }
  
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  
  
  /**
   * @hibernate.set
   *     lazy="true"
   *     cascade="none"
   * 
   * @hibernate.collection-key
   *     column="course_id"
   * 
   * @hibernate.collection-many-to-many
   *     class="com.springinaction.training.model.Course"
   *     column="prereq_id"
   */
  public Set getPrerequisites() {
    return null;
  }
  
  public void setPrerequisites(Set prerequisites) {
    this.prerequisites = prerequisites;
  }
  
  /**
   * @hibernate.set
   *     lazy="true"
   *     cascade="none"
   *     table="transcript"
   *
   * @hibernate.collection-key
   *     column="course_id"
   * 
   * @hibernate.collection-many-to-many
   *     class="com.springinaction.training.model.Student"
   *     column="student_id"
   */
  public Set getStudents() {
    return students;
  }
  
  public void setStudents(Set students) {
    this.students = students;
  }
}
