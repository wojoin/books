<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
  
    <spring:bind path="command.*">
        <c:forEach items="${status.errorMessages}" var="error">
<h2>        Error code: <c:out value="${error}"/></h2>
        </c:forEach>
    </spring:bind>
  
    <form method="POST" action="editCourse.htm">
      <spring:bind path="command.id">  
        <input type="hidden" name="id" value="<c:out value="${status.value}"/>">
      </spring:bind>
    <table border="0" cellspacing="5" cellpadding="0">
      <tr><td valign="middle" align="right">
        <b>Course name: </b> 
      </td><td valign="middle" align="left">
      <spring:bind path="command.name">  
        <input type="text" maxlength="15" name="name" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      
      <tr><td valign="top" align="right">
        <b>Description: </b> 
      </td><td valign="top" align="left">
      <spring:bind path="command.description">  
        <textarea rows="5" cols="50" name="description"><c:out value="${status.value}"/></textarea>
      </spring:bind>
      </td></tr>
      
      <tr><td valign="middle" align="right">
        <b>Start date: </b> 
      </td><td valign="middle" align="left">      
      <spring:bind path="command.startDate">
        <input type="text" maxlength="15" name="startDate" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      
      <tr><td valign="middle" align="right">
        <b>End date: </b> 
      </td><td valign="middle" align="left">      
      <spring:bind path="command.endDate">
        <input type="text" maxlength="15" name="endDate" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      
      <tr><td valign="middle" align="right">
        <b>Instructor: </b> 
      </td><td valign="middle" align="left">      
      <spring:bind path="command.instructor">
        <select name="instructor">
          <c:forEach items="${instructors}" var="instructor">
            <option value="${instructor.login}" <c:if test="${instructor.login == status.value}">selected</c:if>>${instructor.firstName} ${instructor.lastName}
          </c:forEach>
        </select>
      </spring:bind>
      </td></tr>
      <tr><td colspan="2" align="center">            
      <input type="submit" value=" Save ">
      </td></tr>
    </table>
    </form>
  