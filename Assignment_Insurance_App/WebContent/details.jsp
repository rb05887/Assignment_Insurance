<%@page import="com.model.PolicyDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.field{
text-align: center;
margin:auto;
width:40%

}
</style>
<script type="text/javascript">
function fun() {
	alert("Sucssesfully bought Policy!! \n Please check the DB")
	window.location.reload()//remove it if you want buy one policy once only
}
</script>
<body >
	<fieldset class="field">
		<%PolicyDetails p=(PolicyDetails)request.getAttribute("details"); %>
		<div>
			<h3><%=p.getTitle() %></h3>
		</div>
		<div style="text-align: left; padding-left: 15px;">
		<p> Premium : <input type="text" value="<%=p.getPremiumAmount() %>" /></p>
		<p> Duration : <input type="text" value="<%=p.getTermDuration() %>" /></p>
		<p> Short Desc : <%=p.getDescription() %></p>
		<p> Long Desc : <%=p.getLongDesc() %></p>
		</div>
		<input type="submit" value="BUY" onclick="fun()" />
		
	</fieldset>
</body>
</html>