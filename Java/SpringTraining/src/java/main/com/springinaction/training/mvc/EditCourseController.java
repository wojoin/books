package com.springinaction.training.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.springinaction.training.model.Course;
import com.springinaction.training.model.Instructor;
import com.springinaction.training.service.CourseService;
import com.springinaction.training.service.InstructorService;
import com.springinaction.training.util.InstructorEditor;


public class EditCourseController extends SimpleFormController {
  
  private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
      .getLogger(EditCourseController.class);
  
  public EditCourseController() {
    setCommandClass(Course.class);
  }
  
  protected void initBinder(HttpServletRequest request,
      ServletRequestDataBinder binder) throws Exception {
    CustomNumberEditor numberEditor = 
        new CustomNumberEditor(Integer.class, true);
    binder.registerCustomEditor(Integer.class, numberEditor);
    
    CustomDateEditor dateEditor = 
        new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true);
    binder.registerCustomEditor(Date.class, dateEditor);
    
    InstructorEditor instructorEditor = new InstructorEditor();
    instructorEditor.setInstructorService(instructorService);
    binder.registerCustomEditor(Instructor.class, instructorEditor);
  }
  
  protected Map referenceData(HttpServletRequest request) throws Exception {
    HashMap map = new HashMap();
    
    map.put("instructors", instructorService.findAllInstructors());
    
    return map;
  }

  protected Object formBackingObject(HttpServletRequest request) 
      throws ServletException {

    String id = request.getParameter("id");

    if(StringUtils.isNotEmpty(id)) {
      return courseService.getCourse(Integer.parseInt(id));
    } else {
      return new Course();
    }
  }

  protected void doSubmitAction(Object command) throws Exception {
    Course course = (Course) command;
    courseService.saveCourse(course);
  }
  
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
  
  private InstructorService instructorService;
  public void setInstructorService(InstructorService instructorService) {
    this.instructorService = instructorService;
  }
}
