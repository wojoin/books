package com.springinaction.training.mvc;


public class DisplayCourseCommand {
  public DisplayCourseCommand()  {}
  
  private Integer id;
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Integer getId() {
    return id;
  }
}
