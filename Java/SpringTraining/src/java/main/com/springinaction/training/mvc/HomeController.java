package com.springinaction.training.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class HomeController implements Controller {
  public ModelAndView handleRequest(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return new ModelAndView("home", "message", greeting);
  }
  
  private String greeting;
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
}
