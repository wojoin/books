package com.springinaction.training.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.springinaction.training.service.CourseService;


public class EmailReportJob extends QuartzJobBean {

  public EmailReportJob() {}
  
  protected void executeInternal(JobExecutionContext context)
      throws JobExecutionException {
    
    courseService.sendCourseEnrollmentReport();
  }
  
  private CourseService courseService;
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }
}
