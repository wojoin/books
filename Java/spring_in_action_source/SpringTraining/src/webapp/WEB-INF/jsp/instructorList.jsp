<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://acegisecurity.sf.net/authz" prefix="authz" %>

<p>At Spring Training, Inc., we hire only the best and brightest
instructors to help boost your technical skills.</p>

    <authz:authorize ifAllGranted="ROLE_INSTRUCTOR">
      <a href="../faculty/createInstructor.htm">Add instructor</a><br>
    </authz:authorize>
    <ul>
    <c:forEach var="instructor" items="${instructors}">
      <li><a href="displayInstructor.htm?id=${instructor.login}">${instructor.firstName} ${instructor.middleName} ${instructor.lastName}</a>
      <authz:authorize ifAllGranted="ROLE_INSTRUCTOR">
        <a href="../faculty/editInstructor.htm?id=${instructor.login}">[edit]</a>
        <a href="../faculty/deleteInstructor.htm?login=${instructor.login}">[delete]</a>
      </authz:authorize>
    </c:forEach>
    </ul>
