<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ad Campaign</title>
</head>
<body>
<script>
function callURL() {
	
	var partner = document.getElementById("pid").value;
	
	var href = document.getElementById("submiturl").href;
	
	document.getElementById("submiturl").href = href + partner;

	document.getElementById("submiturl").click();

}
</script>
<h2>Create Ad Campaign</h2>
     <form:form method="POST" action="/createad" modelAttribute="adCampaign" >    
        <table >    
         <tr>    
          <td>partner_id : </td>   
          <td><form:input path="partner_id"  /></td>  
         </tr>    
         <tr>    
          <td>duration :</td>    
          <td><form:input path="duration" /></td>  
         </tr>   
         <tr>    
          <td>ad_content :</td>    
          <td><form:input path="ad_content" /></td>  
         </tr>   
         <tr>    
          <td colspan="2"><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form> 
       <BR>
       <br>
       <h2>Search Active Ad Campaign</h2>
         Partner ID:<br>
  		<input type="text" id="pid"><br>

       <button onclick="callURL()">Find Partner ID</button>
       <a href="http://localhost:8080/getad/" id="submiturl"></a>
       <BR>
       <BR>
       <BR>
       <h2>Retrieve partner_id=testid</h2>
       <a href="http://localhost:8080/ad/testid" id="submiturl">Get Ad Campaign (testid)</a>
              <BR>
       <BR>
       <BR>
       <h2>Retrieve All Ad Campaigns</h2>
       <a href="http://localhost:8080/getallad" id="submiturl">Get All Ad Campaign</a>
       
</body>
</html>
