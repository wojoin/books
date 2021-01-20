package com.springinaction.training.dao.jdbc;

import java.util.Set;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.springinaction.training.dao.StudentDao;
import com.springinaction.training.model.Student;


public class StudentDaoJdbc extends JdbcDaoSupport 
    implements StudentDao {
  
  public Student findById(String id) {
    throw new RuntimeException("Method not implemented yet.");
  }

  public void create(Student student) {
    
    Integer id = new Integer(incrementer.nextIntValue());
    
    getJdbcTemplate().update("insert into student (id, login, " +
            "password, firstName, middleName, lastName, " +
						"address1, address2, city, state, zip, phone, email) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        new Object[] {id, student.getLogin(), 
    		student.getPassword(), student.getFirstName(), 
//        student.getMiddleName(), student.getLastName(), 
//        student.getAddress1(), student.getAddress2(), 
//        student.getCity(), student.getState(), student.getZip(), 
//        student.getPhone(), student.getEmail()
        });
    
  }

  public Set getCompletedCourses(Student student) {
    throw new RuntimeException("Method not implemented yet.");
  }
  
  private DataFieldMaxValueIncrementer incrementer;

  public void setIncrementer(DataFieldMaxValueIncrementer incrementer) {
	  this.incrementer = incrementer;
  }
}