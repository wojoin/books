package com.springinaction.training.dao;

import java.util.Set;

import com.springinaction.training.model.*;

public interface StudentDao {
  public Student findById(String id);
  public void create(Student student);
  public Set getCompletedCourses(Student student);
}
