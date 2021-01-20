package com.springinaction.training.service;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.springinaction.training.CourseException;
import com.springinaction.training.dao.CourseDao;
import com.springinaction.training.model.Course;
import com.springinaction.training.model.Student;

public class CourseServiceImpl implements CourseService {
  
  private static Logger LOGGER = Logger.getLogger(CourseServiceImpl.class);
  
  public CourseServiceImpl(CourseDao dao) {
    this.courseDao = dao;
  }


  public Course getCourse(int id) {
    return getCourse(new Integer(id));
  }
  
  public Course getCourse(Integer id) {
    return courseDao.findById(id);
  }

  public void saveCourse(Course course) {
    courseDao.save(course);
  }

  public void enrollStudentInCourse(Course course, 
      Student student) throws CourseException {

    // TODO:    enforcePrerequisites(course, student);

    // TODO:    Check for schedule conflicts

    course.getStudents().add(student);
    courseDao.save(course);
  }
  
  public Set getAllCourses() {
    return courseDao.findAll();
  }

  public void sendCourseEnrollmentReport() {
    Set courseList = courseDao.findAll();

    SimpleMailMessage message = 
        new SimpleMailMessage(this.mailMessage);

    StringBuffer messageText = new StringBuffer();
    messageText.append("Current enrollment data is as follows:\n\n");
    
    for(Iterator iter = courseList.iterator(); iter.hasNext(); ) {
      Course course = (Course) iter.next();
      messageText.append(course.getId() + "    ");
      messageText.append(course.getName() + "    ");
      int enrollment = courseDao.getEnrollment(course);
      messageText.append(enrollment);
    }

    message.setText(messageText.toString());

    try {
      mailSender.send(message);
    } catch (MailException e) {
      LOGGER.error(e.getMessage());
    }
  }
  
  private void enforcePrerequisites(Course course, 
      Student student) throws CourseException {
    
    Set completed = studentService.getCompletedCourses(student);

    // Check for prerequesites
    Set prereqs = course.getPrerequisites();
    for(Iterator iter = prereqs.iterator(); iter.hasNext(); ) {
      if(!completed.contains(iter.next())) {
        throw new CourseException("Prerequisites are not met.");
      }
    }
    
    // Check for scheduling clash
    for(Iterator iter = completed.iterator(); iter.hasNext(); ) {
      Course completedCourse = (Course) iter.next();
      
    }
  }
  
  // COLLABORATORS
  private CourseDao courseDao;
  public void setCourseDao(CourseDao courseDao) {
    this.courseDao = courseDao;
  }
  
  private StudentService studentService;
  public void setStudentService(StudentService service) {
    this.studentService = service;
  }

  private MailSender mailSender;
  public void setMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }
  
  private SimpleMailMessage mailMessage;
  public void setMailMessage(SimpleMailMessage mailMessage) {
    this.mailMessage = mailMessage;
  }
}
