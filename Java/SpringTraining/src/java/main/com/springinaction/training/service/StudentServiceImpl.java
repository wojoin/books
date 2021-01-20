package com.springinaction.training.service;

import java.util.HashSet;

import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.security.SecureContext;
import net.sf.acegisecurity.providers.dao.User;

import org.apache.log4j.Logger;

import com.springinaction.training.dao.StudentDao;
import com.springinaction.training.model.Authority;
import com.springinaction.training.model.Student;

public class StudentServiceImpl implements StudentService {
  Logger LOGGER = Logger.getLogger(StudentServiceImpl.class);
  
  private StudentDao studentDao;

  public StudentServiceImpl(StudentDao dao) {
    studentDao = dao;
  }

  public Student getStudent(String login) {
    return studentDao.findById(login);
  }

  public void registerStudent(Student student) {
    HashSet authorities = new HashSet();
    authorities.add(new Authority("ROLE_STUDENT"));
    authorities.add(new Authority("ROLE_USER"));
    student.setAuthorities(authorities);

    studentDao.create(student);
  }

  public java.util.Set getCompletedCourses(Student student) {
    return studentDao.getCompletedCourses(student);
  }
  
  /*
   * TODO - This could be used more generically for Instructors as well.
   * But since it's only used for Students right now and because it would
   * be silly to create a UserService for this simple method, we'll just
   * put it here for now.
   */
  public String getCurrentUserId() {    
    User acegiUser = (User) ((SecureContext)ContextHolder.getContext()).getAuthentication().getPrincipal();
    return acegiUser.getUsername();
  }
}
