<%-- 
    Document   : collectData
    Created on : Nov 22, 2014, 7:59:41 PM
    Author     : Ruchi.U
--%>

<%@page import="service.ServiceLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            int surveyId = ServiceLogic.getMaxSurveyId((String) request.getParameter("townId"));
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Survey Details</title>
    </head>
    <style type="text/css">
        
        tr, td {
            border: solid silver;
        }
        
    </style>
    <script type="text/javascript">
        
        function validateFields(){
            
            var dob = document.getElementById("dob");
            if("" == dob.value){
                alert("Please enter citizen's date of birth");
                return false;
            }
            
            var fastfood = document.getElementById("fastfood");
            if("" == fastfood.value){
                alert("Please enter value for weekly fast food consumption");
                return false;
            }
            
            if(fastfood.value < 0 || fastfood.value > 7){
                alert("Please enter value for weekly fast food consumption between 0 and 7");
                return false;
            }
            
            var fruits = document.getElementById("fruits");
            if("" == fruits.value){
                alert("Please enter value for weekly fruit consumption");
                return false;
            }
            
            if(fruits.value < 0 || fruits.value > 7){
                alert("Please enter value for weekly fruit consumption between 0 and 7");
                return false;
            }
            
            var farmer = document.getElementById("farmer");
            var labor = document.getElementById("labor");
            var office = document.getElementById("office");
            
            if(!farmer.checked && !labor.checked && !office.checked){
                alert("Please select atleast one occupation");
                return false;
            }
            document.forms[0].submit();
        }
        
    </script>
    <body>
    <center>
        <h2>Please Insert Survey Details</h2>
        <form action="HealthServlet" onsubmit="validateFields()" id="citizenSurveyForm" method="POST">
            <input type="hidden" name="surveyId" value="<%=surveyId%>"/>
            <table>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <select name="gender">
                            <option value="m">Male</option>
                            <option value="f">Female</option>
                        </select>
                    </td>                
                <tr>
                    <td>Date of Birth (yyyy-mm-dd):</td>
                    <td><input type="text" id="dob" name="dob" ></td>
                </tr>
                
                <tr>
                    <td>Ethnicity:</td>
                    <td>
                        <select name="ethnicity">
                            <option value="american">American</option>
                            <option value="african american">African American</option>
                            <option value="native hawaiian">Native Hawaiian</option>
                            <option value="white">White</option>
                            <option value="asian">Asian</option>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Marital Status:</td>
                    <td>
                        <select name="marital">
                            <option value="s">Single</option>
                            <option value="m">Married</option>
                            <option value="d">Divorced</option>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>No of weekly fast food consumption:</td>
                    <td><input id="fastfood" name="fastfood" type="text"></td>
                </tr>
                <tr>
                    <td>No of weekly fruit consumption:</td>
                    <td><input id="fruits" name="fruits" type="text"></td>
                </tr>
                
                <tr>
                    <td>Select the citizen's occupation(s):</td>
                    <td>
                        
                        <input type="checkbox" name="occupation" id="farmer" value="farmer" id="farmer">
                        <label for="farmer">Farmer</label>
                        <br>
                        
                        <input type="checkbox" name="occupation" id="labor" value="industrial labor work">
                        <label for="labor">Industrial Laborer</label>
                        <br>
                        
                        <input type="checkbox" name="occupation" id="office" value="office employee">
                        <label for="office">Office Employee</label>
                    </td>
                </tr>
                
                <tr>
                    <td>Select the disease(s) the citizen is suffering from (if any):</td>
                    <td>
                        <input type="checkbox" id="diabetes" name="disease" value="diabetes">
                        <label for="diabetes">Diabetes</label>
                        <br>
                        
                        <input type="checkbox" id="copd" name="disease" value="copd">
                        <label for="copd">COPD</label>
                        <br>
                        
                        <input type="checkbox" id="myocardial" name="disease" value="myocardial infraction">
                        <label for="myocardial">Myocardial Infraction</label>
                        <br>
                        
                        <input type="checkbox" id="hypertension" name="disease" value="hyper tension">
                        <label for="hypertension">Hypertension</label>
                    </td>
                </tr>
    
                <tr>
                    <td>Does the citizen smoke?</td>
                    <td>
                        <select name="smoking">
                            <option value="1">Yes</option>
                            <option value="0">No</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Is the citizen an alcoholic?</td>
                    <td>
                        <select name="alcohol">
                            <option value="1">Yes</option>
                            <option value="0">No</option>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="button" value="Add Survey Details" onclick="validateFields()"/>
        </form>
    </center>
    </body>
</html>
