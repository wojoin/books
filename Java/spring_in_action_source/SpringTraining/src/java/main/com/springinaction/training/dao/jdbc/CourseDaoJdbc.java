package com.springinaction.training.dao.jdbc;

import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.springinaction.training.dao.CourseDao;
import com.springinaction.training.model.Course;

public class CourseDaoJdbc extends JdbcDaoSupport
    implements CourseDao {
	
  public void save(Course course) {
    throw new RuntimeException("Method not implemented yet.");
	}

	public Course findById(Integer id) {
    throw new RuntimeException("Method not implemented yet.");
	}

	public void update(Course course) {
    throw new RuntimeException("Method not implemented yet.");
	}

  public Set findAll() {
    String sql = 
        "select id, name, description, startDate, endDate " +
        "from course";
    CourseMappingQuery query = 
        new CourseMappingQuery(getDataSource(), sql);
    return new HashSet(query.execute());
  }

  public int getEnrollment(Course course) {
    throw new RuntimeException("Method not implemented yet.");
  }
}