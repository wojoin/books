package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.springinaction.training.model.Instructor;
import com.springinaction.training.service.InstructorService;


public class EditInstructorController extends SimpleFormController {
  private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
      .getLogger(EditInstructorController.class);
  
  public EditInstructorController() {
    setCommandClass(Instructor.class);
  }
  
  protected void initBinder(HttpServletRequest request,
      ServletRequestDataBinder binder) throws Exception {
    CustomNumberEditor numberEditor = new CustomNumberEditor(Integer.class, true);
    binder.registerCustomEditor(Integer.class, numberEditor);
  }
  
  protected Object formBackingObject(HttpServletRequest request) 
      throws Exception {
    return instructorService.getInstructor(request.getParameter("id"));
  }
  
  protected void doSubmitAction(Object command) throws Exception {
    Instructor i = (Instructor) command;
    LOGGER.debug("INSTRUCTOR:  "+ i.getLogin());
    instructorService.updateInstructor((Instructor) command);
  }
  
  // COLLABORATORS
  private InstructorService instructorService;
  public void setInstructorService(InstructorService instructorService) {
    this.instructorService = instructorService;
  }
}
