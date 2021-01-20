package com.springinaction.training.tiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import com.springinaction.training.model.Student;
import com.springinaction.training.service.StudentService;


public class CourseCountTileController extends ComponentControllerSupport {
  protected void doPerform(ComponentContext componentContext,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ApplicationContext context = getApplicationContext();
    StudentService studentService = 
        (StudentService) context.getBean("studentService");
    
    Student student = 
        (Student) request.getSession().getAttribute("student");    
    int courseCount = 
        studentService.getCompletedCourses(student).size();
    
    componentContext.putAttribute("courseCount", 
        new Integer(courseCount));
  } 
}
