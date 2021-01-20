package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.springinaction.training.model.Instructor;
import com.springinaction.training.service.InstructorService;


public class DisplayInstructorController 
    extends AbstractController {

  public DisplayInstructorController() {}

  protected ModelAndView handleRequestInternal(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    String login = request.getParameter("id");
    
    Instructor instructor = instructorService.getInstructor(login);

    return new ModelAndView("instructorDetail", "instructor", instructor);
  }
  
  // INJECTED
  private InstructorService instructorService;
  public void setInstructorService(InstructorService instructorService) {
    this.instructorService = instructorService;
  }
}
