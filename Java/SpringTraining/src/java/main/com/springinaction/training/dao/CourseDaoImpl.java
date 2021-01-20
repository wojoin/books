package com.springinaction.training.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.springinaction.training.model.*;

public class CourseDaoImpl extends HibernateDaoSupport
    implements CourseDao {

  public CourseDaoImpl() {}

	public void save(Course course) {
    getHibernateTemplate().saveOrUpdate(course);
	}

	public Course findById(Integer id) {
		return (Course) getHibernateTemplate().get(Course.class, id);
	}

  public Set findAll() {
    return new HashSet(getHibernateTemplate().find("from com.springinaction.training.model.Course"));
  }
  
  public int getEnrollment(Course course) {
    // TODO - implement this for real
    return 0;
  }
}
