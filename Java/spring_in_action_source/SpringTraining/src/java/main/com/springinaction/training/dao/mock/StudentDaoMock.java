package com.springinaction.training.dao.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.springinaction.training.dao.StudentDao;
import com.springinaction.training.model.Student;


public class StudentDaoMock implements StudentDao {
  
  private int id = 1;
  private Map idMap = new HashMap();
  private Map loginMap = new HashMap();
  
  public Student findById(String id) {
    return (Student) loginMap.get(id);
  }

  public void create(Student student) {
//    student.setId(new Integer(id++));
//    idMap.put(student.getId(), student);
//    loginMap.put(student.getLogin(), student);
  }

  public Set getCompletedCourses(Student student) {
    throw new RuntimeException("Method not implemented yet.");
  }
}