<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://acegisecurity.sf.net/authz" prefix="authz" %>

    <authz:authorize ifAllGranted="ROLE_INSTRUCTOR">
      <a href="../faculty/editCourse.htm">[add course]</a><br>
    </authz:authorize>
    <table  style="font-size:10pt;" width="600" border="1" cellspacing="1" cellpadding="1">
      <tr bgcolor="#999999">
        <td>Course ID</td>
        <td>Name</td>
        <td>Instructor</td>
        <td>Start</td>
        <td>End</td>
      </tr>
    <c:forEach var="course" items="${courses}">
      <tr>
        <td>
          <a href="displayCourse.htm?id=${course.id}">
            <fmt:formatNumber value="${course.id}" 
                pattern="000000"/>
          </a>
        </td>
        <td>${course.name}</td>
        <td>${course.instructor.lastName}</td>
        <td><fmt:formatDate value="${course.startDate}" 
            type="date" dateStyle="full"/></td>
        <td><fmt:formatDate value="${course.endDate}" 
            type="date" dateStyle="full"/></td>
      </tr>
    </c:forEach>

    </table>
