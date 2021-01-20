package com.springinaction.training.mvc;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.springinaction.training.service.CourseService;


public class ListCourseController extends  AbstractController {
  public ModelAndView handleRequestInternal(
      HttpServletRequest request, HttpServletResponse response) 
      throws Exception {

    Set allCourses = courseService.getAllCourses();
    
    return new ModelAndView("courseList", "courses", allCourses);
  }
  
  // INJECTED
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
}
