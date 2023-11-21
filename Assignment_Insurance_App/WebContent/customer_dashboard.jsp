<%@page import="com.model.Policy"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard:</title>
<style type="text/css">
.product_card {
	border: 2px solid;
	width: 40%;
	padding: 10px;
	margin: 20px;
	float: left;
	box-shadow: grey 5px 7px;
}


</style>
</head>
<body>
	<h1>Dashboard:</h1>
	<fieldset>
		<div>
			<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Policies :</h4>
		</div>

		<%
			List<Policy> list = (List<Policy>) request.getAttribute("list_products");
		for (Policy p : list) {
		%><div class="product_card">
			<h3><%=p.getTitle()%></h3>

			<span>Premium : INR.<%=p.getPremiumAmount()%></span> <br />
			<p><%=p.getDescription()%></p>
			<span>Term Duration : <%=p.getTermDuration()%> years</span> <br /> <br />
			<span><a href="MainController?page=details&id=<%=p.getId()%>">Show Policy</a></span>
		</div>
		<%
			}
		%>
	</fieldset>
</body>
</html>