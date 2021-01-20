package com.springinaction.training.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.springinaction.training.service.InstructorService;


public class ListInstructorsController extends AbstractController {

  protected ModelAndView handleRequestInternal(
      HttpServletRequest request, HttpServletResponse response) 
      throws Exception {
    
    List instructors = instructorService.findAllInstructors();
    
    return new ModelAndView("instructorList", "instructors", instructors);
  }
  
  private InstructorService instructorService;
  public void setInstructorService(InstructorService instructorService) {
    this.instructorService = instructorService;
  }
}
