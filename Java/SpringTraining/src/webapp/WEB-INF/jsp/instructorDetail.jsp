<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://acegisecurity.sf.net/authz" prefix="authz" %>

    <authz:authorize ifAllGranted="ROLE_INSTRUCTOR">
      <a href="../faculty/editInstructor.htm?id=${instructor.login}">[edit]</a>
      <br>
    </authz:authorize>
    <h2>${instructor.firstName} ${instructor.middleName} ${instructor.lastName}</h2>
    
    <p><c:out value="${instructor.bio}"/></p>