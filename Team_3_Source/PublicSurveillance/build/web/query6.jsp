<%-- 
    Document   : query6
    Created on : Nov 23, 2014, 5:27:21 PM
    Author     : Ruchi.U
--%>

<%@page import="service.ServiceLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Availability of health resources for Town C for whole observation period </title>
        <%
            Double ratio = ServiceLogic.getQuery6();
        %>   
        <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
        </style>
    </head>
    <body>
    <center>
        <div>Availability of health resources for Town C for whole observation period </div>
        <table>
            <tr><td>Ratio of health resources</td></tr>
            <tr><td><%=ratio%></td></tr>
        </table>
        
        
        <a href="getQueryResults.jsp">Back to Queries Home Page</a>
        <br>
        <a href="homepage.jsp">Back to home page</a>
        <br>
    </center>
    </body>
</html>
