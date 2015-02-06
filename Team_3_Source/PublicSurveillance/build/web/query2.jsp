<%-- 
    Document   : query2
    Created on : Nov 23, 2014, 5:26:45 PM
    Author     : Ruchi.U
--%>

<%@page import="java.util.Map"%>
<%@page import="service.ServiceLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Most common accident type for Town A</title>
        <%
            Map<String,Integer> carAccidentTypeNumberMap = ServiceLogic.getQuery2();
        %>
        <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
    </style>
    </head>
    <body>
    <center>
        <div>Most common accident type for Town A</div>
        <table>
            <tr>
                <td>Type of accidents</td>
                <td>No of accidents</td>
            </tr>
            <% for(String key: carAccidentTypeNumberMap.keySet()){
                %><tr><td><%=key%></td><td><%=carAccidentTypeNumberMap.get(key)%></td></tr>
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
