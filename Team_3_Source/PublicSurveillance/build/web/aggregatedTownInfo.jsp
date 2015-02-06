<%@page import="service.ServiceLogic"%>
<%@page import="java.util.Map"%>
<%@page import="dto.LocationDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select the town of survey</title>
<style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
    </style>
</head>
<body>
    <%
        List<LocationDTO> locationDTOs = ServiceLogic.getLocationDetails();
        Map<Integer, String> aggregateInfo = null;
        if(request.getParameter("submitbutton") != null){
            String townIdValue = request.getParameter("townDropDown");
            if("0".equals(townIdValue)){
                %><h6>Please select a town</h6><%
            } else {
               aggregateInfo = ServiceLogic.getAggregateInformation(townIdValue);
            }
        }
%>
    <center>
    <h2>Select the town of Survey</h2>
    <form name="townSelectForm" method="POST">
        <select id="townDropDown" name="townDropDown">
            <option value="0">Select a location</option>
            <% for(LocationDTO locationDTO: locationDTOs){%>
            <option value="<%=locationDTO.getLocationId()%>"><%=locationDTO.getLocationName()%></option>
            <% } %>
        </select>
        <input name="submitbutton" type="submit" value="Submit"/>
    </form>
    <%
            if(request.getParameter("submitbutton") != null){
                %><table>
                    <tr><td>Year</td><td>Average Population</td></tr>
                <%
                for(Integer key: aggregateInfo.keySet()){
                    %><tr><td><%=key%></td><td><%=aggregateInfo.get(key)%></td></tr><%
                }
                %></table><%
            }
    %>
    <a href="homepage.jsp">Back to home page</a>
    </center>
    
</body>
</html>