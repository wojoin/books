package com.springinaction.training.mvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class DisplayCourseCommandValidator implements Validator {
  public boolean supports(Class clazz) {
    return clazz.equals(DisplayCourseCommand.class);
  }
  
  public void validate(Object obj, Errors errors) {
    DisplayCourseCommand command = (DisplayCourseCommand) obj;
    
    if(command.getId() == null) {
      errors.reject("id.required", "ID is a required parameter");
    }
  }
}
