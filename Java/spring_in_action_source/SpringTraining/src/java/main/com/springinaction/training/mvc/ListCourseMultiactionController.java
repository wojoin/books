package com.springinaction.training.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.springinaction.training.model.Course;
import com.springinaction.training.service.CourseService;


public class ListCourseMultiactionController {
  public ListCourseMultiactionController() {}
  
  public ModelAndView coursesUnsorted(HttpServletRequest request,
      HttpServletResponse response) {

    Set courses = courseService.getAllCourses();
    return new ModelAndView("courseList", "courses", courses);
  }

  public ModelAndView coursesSortedByStartDate(
      HttpServletRequest request, HttpServletResponse response) {

    List courses = new ArrayList(courseService.getAllCourses());    
    Collections.sort(courses, new ByNameComparator());

    return new ModelAndView("courseList", "courses", courses);
  }
  
  public ModelAndView coursesSortedByName(HttpServletRequest request,
      HttpServletResponse response) {

    List courses = new ArrayList(courseService.getAllCourses());    
    Collections.sort(courses, new ByNameComparator());
    
    return new ModelAndView("courseList", "courses", courses);
  }
  
  // COLLABORATORS  
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }

  public class ByDateComparator implements Comparator {
    public int compare(Object o1, Object o2) {
      Course c1 = (Course) o1;
      Course c2 = (Course) o2;
      
      return c1.getStartDate().compareTo(c2.getStartDate());
    }
  }
  
  public class ByNameComparator implements Comparator {
    public int compare(Object o1, Object o2) {
      Course c1 = (Course) o1;
      Course c2 = (Course) o2;
      
      return c1.getName().compareTo(c2.getName());
    }
  }
}
