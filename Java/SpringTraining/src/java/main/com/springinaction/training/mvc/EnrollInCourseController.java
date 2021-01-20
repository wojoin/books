package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.springinaction.training.model.Course;
import com.springinaction.training.model.Student;
import com.springinaction.training.service.CourseService;
import com.springinaction.training.service.StudentService;


public class EnrollInCourseController extends AbstractCommandController {
  Logger LOGGER = Logger.getLogger(EnrollInCourseController.class);
  
  public EnrollInCourseController() {
    setCommandClass(EnrollCommand.class);
  }
  
  protected ModelAndView handle(HttpServletRequest request, 
      HttpServletResponse response, Object command, BindException bindException) 
      throws Exception {

    String studentId = studentService.getCurrentUserId();
    Student student = studentService.getStudent(studentId);
    
    EnrollCommand enroll = (EnrollCommand) command;
    Course course = courseService.getCourse(enroll.getCourseId());
    
    courseService.enrollStudentInCourse(course, student);
    
    return new ModelAndView("redirect:/student/displayTranscript.htm");
  }
  
  // INJECTED
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
  
  private StudentService studentService;
  public void setStudentService(StudentService studentService) {
    this.studentService = studentService;
  }
  
}
