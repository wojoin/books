package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.springinaction.training.model.Course;
import com.springinaction.training.service.CourseService;


public class DisplayCourseController 
    extends AbstractCommandController {

  public DisplayCourseController() {
    setCommandClass(DisplayCourseCommand.class);
  }
  
  protected ModelAndView handle(HttpServletRequest request,
      HttpServletResponse response, Object command, 
      BindException errors) throws Exception {

    DisplayCourseCommand displayCommand = 
        (DisplayCourseCommand) command;

    Course course = courseService.getCourse(displayCommand.getId());

    return new ModelAndView("courseDetail", "course", course);
  }
  
  // INJECTED
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
}
