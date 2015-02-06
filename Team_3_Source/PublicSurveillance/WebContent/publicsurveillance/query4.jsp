<%-- 
    Document   : query4
    Created on : Nov 23, 2014, 5:27:02 PM
    Author     : Ruchi.U
--%>

<%@page import="service.ServiceLogic"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Town with lowest diabetes prevalence for the year 2012</title>
        <%
            Map<String,Double> townMinPrevalence = ServiceLogic.getQuery4();
        %>
        <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
    </style>
    </head>
    <body>
    <center>
        <div>Town with lowest diabetes prevalence for the year 2012</div>
        <table>
            <tr>
                <td>Town</td>
                <td>Diabetes Prevalence</td>
            </tr>
            <% for(String key: townMinPrevalence.keySet()){
                %><tr><td><%=key%></td><td><%=townMinPrevalence.get(key)%></td></tr>
            <%}%>
        </table>
        <br>
        <a href="getQueryResults.jsp">Back to Queries Home Page</a>
        <br>
        <a href="homepage.jsp">Back to home page</a>
        <br>
    </center>
    </body>
</html>
