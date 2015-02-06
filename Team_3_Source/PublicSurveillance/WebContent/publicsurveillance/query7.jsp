<%-- 
    Document   : query7
    Created on : Nov 23, 2014, 5:27:27 PM
    Author     : Ruchi.U
--%>

<%@page import="service.ServiceLogic"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change in smoking habit between 2014 to 2012 for all towns</title>
        <%
            Map<String,Double> changeInSmokingHabit = ServiceLogic.getQuery7();
        %>
        <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
    </style>
    </head>
    <body>
    <center>
        <div>Change in smoking habit between 2014 to 2012 for all towns</div>
        <table>
            <tr>
                <td>Town</td>
                <td>Change in smoking habit</td>
            </tr>
            <% for(String key: changeInSmokingHabit.keySet()){
                %><tr><td><%=key%></td><td><%=changeInSmokingHabit.get(key)%></td></tr>
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
