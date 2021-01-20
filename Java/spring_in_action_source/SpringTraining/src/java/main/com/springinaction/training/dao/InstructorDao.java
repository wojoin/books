package com.springinaction.training.dao;

import java.util.List;

import com.springinaction.training.model.Instructor;


public interface InstructorDao {
  public void createInstructor(Instructor instructor);
  public void updateInstructor(Instructor instructor);
  public List findAllInstructors();
  public Instructor getInstructor(String login);
  public void deleteInstructor(Instructor instructor);
}
