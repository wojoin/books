
package com.springinaction.training.dao.ojb;

import java.util.Set;

import org.springframework.orm.ojb.support.PersistenceBrokerDaoSupport;

import com.springinaction.training.dao.StudentDao;
import com.springinaction.training.model.Student;


public class StudentDaoOjb extends PersistenceBrokerDaoSupport
    implements StudentDao {
  
  public Student findById(final String id) {
    throw new RuntimeException("Method not implemented yet.");
  }

  public void create(Student student) {
    getPersistenceBrokerTemplate().store(student);
  }
  
  public Set getCompletedCourses(Student student) {
    throw new RuntimeException("Method not implemented yet.");
  }

}