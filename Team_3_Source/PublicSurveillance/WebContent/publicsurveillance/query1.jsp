<%-- 
    Document   : query1
    Created on : Nov 23, 2014, 5:26:38 PM
    Author     : Ruchi.U
--%>

<%@page import="java.util.Map"%>
<%@page import="service.ServiceLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
    </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Surveyed female population for each town during the year 2012</title>
            <%
            Map<String, Integer> countPeopleTownMap = ServiceLogic.getQuery1();
            %>
    </head>
    
    <body>
    <center>
        <div>Surveyed female population for each town during the year 2012</div>
        <table>
            <tr>
                <td>Town</td>
                <td>Female population surveyed</td>
            </tr>
            <% for(String key: countPeopleTownMap.keySet()){
                %><tr><td><%=key%></td><td><%=countPeopleTownMap.get(key)%></td></tr>
            <%}%>
        </table>
        <a href="getQueryResults.jsp">Back to Queries Home Page</a>
        <br>
        <a href="homepage.jsp">Back to home page</a>
        <br>
    </center>
    </body>
</html>
