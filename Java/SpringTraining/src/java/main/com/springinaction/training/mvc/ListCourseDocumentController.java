package com.springinaction.training.mvc;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.Controller;

import com.springinaction.training.service.CourseService;


public class ListCourseDocumentController implements Controller {
  public ModelAndView handleRequest(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    Set allCourses = courseService.getAllCourses();

    return new ModelAndView(documentView, "courses", allCourses);
  }
  
  // COLLABORATORS  
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
  
  private View documentView;
  public void setDocumentView(View documentView) {
    this.documentView = documentView;
  }
}
