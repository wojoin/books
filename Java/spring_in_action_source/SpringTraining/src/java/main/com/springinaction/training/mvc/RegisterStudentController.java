
package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.springinaction.training.model.Email;
import com.springinaction.training.model.EmailPropertyEditor;
import com.springinaction.training.model.Student;
import com.springinaction.training.service.StudentService;


public class RegisterStudentController extends SimpleFormController {
  Logger LOGGER = Logger.getLogger(RegisterStudentController.class);
  
  public RegisterStudentController() {
    setCommandClass(Student.class);
    setValidator(new StudentValidator());
  }
  
  protected Object formBackingObject(HttpServletRequest request)
      throws Exception {
    LOGGER.debug("Called formBackingObject");
    return super.formBackingObject(request);
	}
  
  protected void initBinder(HttpServletRequest request,
      ServletRequestDataBinder binder) throws Exception {
    LOGGER.debug("Called initBinder");
    binder.registerCustomEditor(
        Email.class, new EmailPropertyEditor());
    super.initBinder(request, binder);
  }
  
  protected void doSubmitAction(Object command) 
      throws Exception {
    
    Student student = (Student) command;
    
    LOGGER.debug("firstName=[" + student.getFirstName() + "]");
    
    LOGGER.debug("**************************** Enrolling student");
    
    studentService.registerStudent(student);
    
    LOGGER.debug("**************************** Student enrolled.");
  } 
  
  private StudentService studentService;
  
  public void setStudentService(StudentService studentService) {
    this.studentService = studentService;
  }
}
