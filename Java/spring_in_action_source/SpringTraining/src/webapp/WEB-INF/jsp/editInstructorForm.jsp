<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://acegisecurity.sf.net/authz" prefix="authz" %>

  <spring:bind path="command">
  <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
  </spring:bind>
  
    <form method="POST">
    <table border="0" cellspacing="5" cellpadding="0">
      <tr><td valign="middle" align="right">
        <b>Login: </b> 
      </td><td valign="middle" align="left">
      <spring:bind path="command.login">  
        <c:choose>
          <c:when test="${status.value != null}">
             ${status.value} <input type="hidden" name="login" value="<c:out value="${status.value}"/>"><br>
          </c:when>
          <c:otherwise>
            <input type="text" maxlength="15" name="login" value="<c:out value="${status.value}"/>"><br>
          </c:otherwise>
        </c:choose>
      </spring:bind>
      </td></tr>
      <tr><td valign="middle" align="right">      
        <b>Password: </b> 
      </td><td valign="middle" align="left">
      <spring:bind path="command.password">  
        <input type="password" maxlength="15" name="password" value="<c:out value="${status.value}"/>"><br>
      </spring:bind>
      </td></tr>

      <tr><td valign="middle" align="right">      
        <b>First name: </b> 
      </td><td valign="middle" align="left">
      <spring:bind path="command.firstName">  
         <input type="text" maxlength="15" name="firstName" value="<c:out value="${status.value}"/>"><br>
      </spring:bind>
      </td></tr>

      <tr><td valign="middle" align="right">      
        <b>Middle name: </b> 
      </td><td valign="middle" align="left">
      <spring:bind path="command.middleName">  
        <input type="text" maxlength="15" name="middleName" value="<c:out value="${status.value}"/>"><br>
      </spring:bind>
      </td></tr>
      
      <tr><td valign="middle" align="right">      
        <b>Last name: </b> 
      </td><td valign="middle" align="left">
      <spring:bind path="command.lastName">  
        <input type="text" maxlength="15" name="lastName" value="<c:out value="${status.value}"/>"><br>
      </spring:bind>
      </td></tr>
      
      <tr><td valign="top" align="right">            
        <b>Bio: </b> 
      </td><td valign="middle" align="left">        
      <spring:bind path="command.bio">
        <textarea name="bio" rows="10 cols="50"><c:out value="${status.value}"/></textarea><br>
      </spring:bind>
      </td></tr>
      <tr><td colspan="2" align="center">

      <spring:bind path="command.login">  
        <c:choose>
          <c:when test="${status.value != null}">
      <input type="submit" value=" Save Changes ">
          </c:when>
          <c:otherwise>
      <input type="submit" value=" Create ">
          </c:otherwise>
        </c:choose>
      </spring:bind>
      </td></tr>
    </table>
    </form>
