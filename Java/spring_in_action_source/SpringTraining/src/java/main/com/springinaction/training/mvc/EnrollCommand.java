package com.springinaction.training.mvc;

public class EnrollCommand {
  public EnrollCommand() {}
  
  private Integer courseId;
  public Integer getCourseId() {
    return courseId;
  }
  
  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

}
