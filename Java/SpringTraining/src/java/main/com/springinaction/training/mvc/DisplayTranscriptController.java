package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.springinaction.training.model.Student;
import com.springinaction.training.service.StudentService;


public class DisplayTranscriptController extends AbstractController {
  protected ModelAndView handleRequestInternal(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    
    String studentId = studentService.getCurrentUserId();
    Student student = studentService.getStudent(studentId);

    return new ModelAndView("transcript", "student", student);
  }
  
  private StudentService studentService;
  public void setStudentService(StudentService studentService) {
    this.studentService = studentService;
  }
}
