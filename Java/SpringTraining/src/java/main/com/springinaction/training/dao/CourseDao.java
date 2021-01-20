package com.springinaction.training.dao;

import java.util.Set;

import com.springinaction.training.model.*;

public interface CourseDao {
	public Course findById(Integer id);
	public void save(Course course);
  public Set findAll();
  public int getEnrollment(Course course);
}
