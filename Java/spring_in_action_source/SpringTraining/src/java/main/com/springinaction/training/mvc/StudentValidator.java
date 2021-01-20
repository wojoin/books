package com.springinaction.training.mvc;

import org.apache.oro.text.perl.Perl5Util;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springinaction.training.model.Student;


public class StudentValidator implements Validator {
  public boolean supports(Class clazz) {
    return clazz.equals(Student.class);
  }

  public void validate(Object command, Errors errors) {
    Student student = (Student) command;

    ValidationUtils.rejectIfEmpty(
        errors, "login", "required.login", "Login is required");
    ValidationUtils.rejectIfEmpty(
        errors, "password", "required.password", 
        "Password is required");
    ValidationUtils.rejectIfEmpty(
        errors, "firstName", "required.firstName", 
        "First name is required");
    ValidationUtils.rejectIfEmpty(
        errors, "lastName", "required.lastName", 
        "Last name is required");
    ValidationUtils.rejectIfEmpty(
        errors, "address1", "required.address", 
        "Address is required");
    ValidationUtils.rejectIfEmpty(
        errors, "city", "required.city", "City is required.");
    ValidationUtils.rejectIfEmpty(
        errors, "state", "required.state", "State is required");
    ValidationUtils.rejectIfEmpty(
        errors, "zip", "required.zip", "Zip is required");
    ValidationUtils.rejectIfEmpty(
        errors, "phone", "required.phone", "Phone is required");
  }
  
  private static final String PHONE_REGEXP = 
    "/(\\({0,1})(\\d{3})(\\){0,1})(\\s|-)*(\\d{3})(\\s|-)*(\\d{4})/";

  private void validatePhone(String phone, Errors errors) {
    ValidationUtils.rejectIfEmpty(
        errors, "phone", "required.phone", "Phone is required");


    Perl5Util perl5Util = new Perl5Util();
    if(!perl5Util.match(PHONE_REGEXP, phone)) {
      errors.reject("invalid.phone", "Phone number is invalid");
    }    
  }

  private static final String EMAIL_REGEXP = 
    "/^[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\\.-][a-z0-9]+)*)+\\.[a-z]{2,}$/i";

  private void validateEmail(String email, Errors errors) {
    ValidationUtils.rejectIfEmpty(
        errors, "email", "required.email", "E-mail is required");

    Perl5Util perl5Util = new Perl5Util();
    if(!perl5Util.match(EMAIL_REGEXP, email)) {
      errors.reject("invalid.email", "E-mail is invalid");
    }
  }
}
