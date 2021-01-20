package com.springinaction.training.dao;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.springinaction.training.model.Student;


public class StudentDaoImpl extends HibernateDaoSupport 
    implements StudentDao {

  Logger LOGGER = Logger.getLogger(StudentDaoImpl.class);
  
  public StudentDaoImpl() {}
  
  public Student findById(String id) {
    return (Student) getHibernateTemplate().get(Student.class, id);
  }

  public void create(Student student) {
    getHibernateTemplate().save(student);
  }

  public Set getCompletedCourses(Student student) {
    getHibernateTemplate().find(
        "from com.springinaction.training.model.Course as course " +
        "where course."
        );
    return null;
  }
}
