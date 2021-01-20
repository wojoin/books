package com.springinaction.training.dao.hibernate;

import java.util.List;

import com.springinaction.training.dao.InstructorDao;
import com.springinaction.training.model.Instructor;


public class InstructorDaoHibernate extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
    implements InstructorDao {

  private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
      .getLogger(InstructorDaoHibernate.class);
  
  public void createInstructor(Instructor instructor) {
    getHibernateTemplate().save(instructor);
  }
  
  public void updateInstructor(Instructor instructor) {
    getHibernateTemplate().update(instructor);
  }
    
  public List findAllInstructors() {
    return getHibernateTemplate().find(
        "from "+Instructor.class.getName() + " as i " +
        " order by i.lastName,i.firstName");
  }
  
  public Instructor getInstructor(String login) {
    return (Instructor) 
        getHibernateTemplate().get(Instructor.class, login);
  }
  
  public void deleteInstructor(Instructor instructor) {
    getHibernateTemplate().delete(instructor);
  }
}
