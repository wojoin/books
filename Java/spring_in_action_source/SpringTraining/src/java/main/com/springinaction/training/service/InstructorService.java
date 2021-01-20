package com.springinaction.training.service;

import java.util.List;

import com.springinaction.training.model.Instructor;


public interface InstructorService {
  public void createInstructor(Instructor instructor);
  public void updateInstructor(Instructor instructor);
  public Instructor getInstructor(String login);
  public List findAllInstructors();
  public void deleteInstructor(String login);
}
