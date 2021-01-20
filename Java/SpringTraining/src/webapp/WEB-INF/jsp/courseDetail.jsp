<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://acegisecurity.sf.net/authz" prefix="authz" %>

    <h2>${course.name}</h2>
    <authz:authorize ifAllGranted="ROLE_INSTRUCTOR">
      <a href="../faculty/editCourse.htm?id=${course.id}">[edit]</a>
      <br>
    </authz:authorize>
        
    <b>ID: </b> 
      <fmt:formatNumber value="${course.id}" pattern="000000"/><br>
    <b>Instructor: </b> 
      ${course.instructor.firstName} ${course.instructor.lastName}
      <br>
    <b>Starts: </b> 
      <fmt:formatDate value="${course.startDate}" type="date" 
          dateStyle="full"/><br>
    <b>Ends: </b> 
      <fmt:formatDate value="${course.endDate}" type="date" 
          dateStyle="full"/><br>
    <br>
    ${course.description}
    <br>
    <br>
     <authz:authorize ifAllGranted="ROLE_STUDENT">          
    <a href="enroll.htm?courseId=${course.id}">
        Enroll in course
    </a><br>
    </authz:authorize>
    