<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cs553_pa3.server.*" %>
<html>
  <head>
    <title>Programming Assignment 3 - Sujithra Vivin Vijay</title>
    <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
  </head>
  <body>
  <%
  String answerStr = "Select any one of the above option and submit request.";
  String option = request.getParameter("option");
  if(option != null)
  {
	  GAEFormService gfs = new GAEFormService();
	  if(option.equals("insert")){
	  answerStr = gfs.uploadFile(request.getParameter("uploadFile"),"contents");
	  }
	  else if(option.equals("check")){
		  answerStr = gfs.checkFile(request.getParameter("checkFileName"));
	  }
	  else if(option.equals("find")){
		  answerStr = gfs.findFile(request.getParameter("findFileName")); 
	  }
	  else if(option.equals("remove")){
		  answerStr = gfs.removeFile(request.getParameter("removeFileName"));
	  }
	  else if(option.equals("getall")){
		  answerStr = gfs.getAllFiles();
	  }
  }
  %>
      <div class="main-form">
       <form action="/" method="get" enctype="multipart/form-data">
          <table>
              <tr>
                <td><input type="radio" name="option" value="insert"></td>
                <td>Insert:</td>
                <td><input type="file" name="uploadFile" size="chars"/></td>
              </tr>
              <tr>
                <td> <input type="radio" name="option" value="check"></td>
                <td>Check : </td>
                <td><input type="text" name="checkFileName"></td>
              </tr>
              <tr>
	              <td> <input type="radio" name="option" value="find"></td>
	              <td>Find : </td>
	              <td><input type="text" name="findFileName"></td>
              <tr>
                <td> <input type="radio" name="option" value="remove"> </td>
                <td>Remove: </td>
                <td><input type="text" name="removeFileName"></td>
              </tr>
              <tr>
                <td><input type="radio" name="option" value="getall"></td>
                <td>Get all File Name</td>
                <td></td>
              </tr>
              <tr>
                <td></td>
                <td><input type="submit" name="submit" value="Submit Request"/></td>
                <td></td>
              </tr>

          </table>
       </form>
      </div>
       <br/>
      <div class="answer">
      <%=answerStr%>
      </div>
  </body>
</html>