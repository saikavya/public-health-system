<%-- 
    Document   : query3
    Created on : Nov 23, 2014, 5:26:54 PM
    Author     : Ruchi.U
--%>

<%@page import="service.ServiceLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prevalence of diabetes in Town B for year 2014</title>
        <%
            Double prevalence = ServiceLogic.getQuery3();
        %>   
        <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
        </style>
    </head>
    <body>
    <center>
        <div>Prevalence of diabetes in Town B for year 2014</div>
        <table>
            <tr><td>Diabetes Prevalence</td></tr>
            <tr><td><%=prevalence%></td></tr>
        </table>
        
        
        <a href="getQueryResults.jsp">Back to Queries Home Page</a>
        <br>
        <a href="homepage.jsp">Back to home page</a>
        <br>
    </center>
    </body>
</html>
