package com.springinaction.training.util;

import java.beans.PropertyEditorSupport;

import com.springinaction.training.model.Instructor;
import com.springinaction.training.service.InstructorService;


public class InstructorEditor extends PropertyEditorSupport {
  public String getAsText() {
    if(getValue() != null) {
      return ((Instructor)getValue()).getLogin();      
    }
    
    return "";
  }

  public void setAsText(String login) throws IllegalArgumentException {
    setValue(instructorService.getInstructor(login));
  }
  
  private InstructorService instructorService;
  public void setInstructorService(InstructorService instructorService) {
    this.instructorService = instructorService;
  }
}
