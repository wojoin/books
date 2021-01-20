package com.springinaction.training.dao.sqlmap;

import java.util.Set;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.springinaction.training.dao.StudentDao;
import com.springinaction.training.model.Student;



public class StudentDaoSqlMap extends SqlMapClientDaoSupport 
    implements StudentDao {
  
  public Student findById(final String id) {
    throw new RuntimeException("Method not implemented yet.");
  }

  public void create(Student student) {
    getSqlMapClientTemplate().update("insertStudent", student);
  }
  
  public Set getCompletedCourses(Student student) {
    throw new RuntimeException("Method not implemented yet.");
  }

}