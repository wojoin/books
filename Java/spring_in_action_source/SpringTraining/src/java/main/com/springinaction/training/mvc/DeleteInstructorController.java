package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

import com.springinaction.training.service.InstructorService;


public class DeleteInstructorController extends AbstractController {
  private static final org.apache.log4j.Logger LOGGER = 
      org.apache.log4j.Logger.getLogger(DeleteInstructorController.class);

  protected ModelAndView handleRequestInternal(
      HttpServletRequest request, HttpServletResponse response) 
      throws Exception {

    instructorService.deleteInstructor(request.getParameter("login"));
    
    return new ModelAndView(new RedirectView("../student/listInstructors.htm"));
  } 
  
  private InstructorService instructorService;
  public void setInstructorService(InstructorService instructorService) {
    this.instructorService = instructorService;
  }
}
