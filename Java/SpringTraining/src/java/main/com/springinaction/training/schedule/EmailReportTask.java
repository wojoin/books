package com.springinaction.training.schedule;

import java.util.TimerTask;

import com.springinaction.training.service.CourseService;

public class EmailReportTask extends TimerTask {
  public EmailReportTask() {}
  
  public void run() {
    courseService.sendCourseEnrollmentReport();
  }
  
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
}
