package com.springinaction.training.service;

import com.springinaction.training.CourseException;
import com.springinaction.training.model.*;

public interface CourseService {
  public Course getCourse(int id);
  public Course getCourse(Integer id);
  public void saveCourse(Course course);
  public java.util.Set getAllCourses();
  public void enrollStudentInCourse(Course course, 
      Student student) throws CourseException;
  
  public void sendCourseEnrollmentReport();
}
