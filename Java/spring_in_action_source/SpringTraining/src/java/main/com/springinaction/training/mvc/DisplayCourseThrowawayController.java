package com.springinaction.training.mvc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import com.springinaction.training.model.Course;
import com.springinaction.training.service.CourseService;


public class DisplayCourseThrowawayController 
    implements ThrowawayController {

  private Integer id;
  public void setId(Integer id) { this.id = id; }
  
  public ModelAndView execute() throws Exception {
    Course course = courseService.getCourse(id);
 
    return new ModelAndView("courseDetail", "course", course);
  }
  
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
}
