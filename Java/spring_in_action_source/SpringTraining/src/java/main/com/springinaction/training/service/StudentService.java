package com.springinaction.training.service;

import com.springinaction.training.model.*;

public interface StudentService {
  public Student getStudent(String login);
  public void registerStudent(Student student);
  public java.util.Set getCompletedCourses(Student student);
  public String getCurrentUserId();
}
