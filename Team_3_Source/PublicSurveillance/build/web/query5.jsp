<%-- 
    Document   : query5
    Created on : Nov 23, 2014, 5:27:11 PM
    Author     : Ruchi.U
--%>

<%@page import="service.ServiceLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Town(s) where no accidents have been reported</title>
        <%
            String locationName = ServiceLogic.getQuery5();
        %>   
        <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
        </style>
    </head>
    <body>
    <center>
        <div>Town(s) where no accidents have been reported </div>
        <table>
            <tr><td>Location</td></tr>
            <tr><td><%=locationName%></td></tr>
        </table>
        <a href="getQueryResults.jsp">Back to Queries Home Page</a>
        <br>
        <a href="homepage.jsp">Back to home page</a>
        <br>
    </center>
    </body>
</html>
