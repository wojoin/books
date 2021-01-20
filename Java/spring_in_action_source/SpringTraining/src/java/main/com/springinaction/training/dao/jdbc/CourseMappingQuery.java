package com.springinaction.training.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import com.springinaction.training.model.Course;


public class CourseMappingQuery extends MappingSqlQuery {

  CourseMappingQuery(DataSource dataSource, String sql) {
    super(dataSource, sql);
    compile();
  }

  protected Object mapRow(ResultSet rs, int rowNum) 
      throws SQLException {
  	Course course = new Course();
    course.setId(new Integer(rs.getInt("id")));
    course.setName(rs.getString("name"));
    course.setDescription(rs.getString("description"));
    course.setStartDate(rs.getDate("startDate"));
    course.setEndDate(rs.getDate("endDate"));
    return course;
  }

}