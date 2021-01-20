<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://acegisecurity.sf.net/authz" prefix="authz" %>

<html>
  <head>
    <title>Spring Training: <tiles:getAsString name="title"/></title>
  </head>

  <body style="font-family:verdana;">
    <table width="742" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" style="color:#FFFFFF;background-color:#666699;height:100px;"><span style="font-size:24pt;">Spring Training</span><br>
          <span style="font-size:8pt;font-style:italics;">Put a bounce in your career.</span>
        </td>
      </tr>
      <tr>
        <td width="150" valign="top" style="font-size:9pt;background-color:#CCCCFF;line-height:1.5;height:400px;">
          <a href="/SpringTraining/home.htm">Home</a><br>
    <authz:authorize ifNotGranted="ROLE_STUDENT,ROLE_INSTRUCTOR">
          <a href="/SpringTraining/jsp/login.jsp">Login</a><br>
          <a href="/SpringTraining/register.htm">Register</a><br>
    </authz:authorize>
    <authz:authorize ifAnyGranted="ROLE_INSTRUCTOR,ROLE_STUDENT">
          <a href="/SpringTraining/student/listCourses.htm">View courses</a><br>
          <a href="/SpringTraining/student/listInstructors.htm">Our instructors</a><br>
    </authz:authorize>
    <authz:authorize ifAllGranted="ROLE_STUDENT">          
          <a href="/SpringTraining/student/displayTranscript.htm">View transcript</a><br>
    </authz:authorize>
    <authz:authorize ifAllGranted="ROLE_INSTRUCTOR">
          <a href="/SpringTraining/faculty/editCourse.htm">Add course</a><br>
          <a href="/SpringTraining/faculty/createInstructor.htm">Add instructor</a><br>
    </authz:authorize>
        </td>
        <td width="598" valign="top">
          <table width="598" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td style="font-size:14pt;color:#FFFFFF;background-color:#9999CC;" align="right"><tiles:getAsString name="title"/></td>
            </tr><tr>
              <td valign="top" style="font-size:10pt;">
                <tiles:insert attribute="body"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center" style="color:#FFFFFF;background-color:#9999CC;font-size:8pt;">Copyright &copy; 2005 Craig Walls &amp; Ryan Breidenbach</td>
      </tr>
    </table>
  </body>
</html>
