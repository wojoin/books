package com.springinaction.training.service;

import java.util.HashSet;
import java.util.List;

import com.springinaction.training.dao.InstructorDao;
import com.springinaction.training.model.Authority;
import com.springinaction.training.model.Instructor;


public class InstructorServiceImpl implements InstructorService {

  public void createInstructor(Instructor instructor) {
    HashSet authorities = new HashSet();
    authorities.add(new Authority("ROLE_INSTRUCTOR"));
    authorities.add(new Authority("ROLE_USER"));
    instructor.setAuthorities(authorities);

    instructorDao.createInstructor(instructor);
  }
  
  public void updateInstructor(Instructor instructor) {
    instructorDao.updateInstructor(instructor);
  }
  
  public Instructor getInstructor(String login) {
    return instructorDao.getInstructor(login);
  }
  
  public List findAllInstructors() {
    return instructorDao.findAllInstructors();
  }
  
  public void deleteInstructor(String login) {
    Instructor instructor = instructorDao.getInstructor(login);
    instructorDao.deleteInstructor(instructor);
  }
  
  private InstructorDao instructorDao;
  public void setInstructorDao(InstructorDao instructorDao) {
    this.instructorDao = instructorDao;
  }
  
}
