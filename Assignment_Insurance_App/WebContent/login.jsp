<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

</head>
<style>
.field{
text-align: center;
margin:auto;
width:40%

}
</style>
<body>


	<fieldset class="field">
	<h1>LogIn</h1>
		<legend>Log In</legend>
		<form action = "MainController?page=login_form" method="post">
			
			<label>Enter Username: </label><input type="text" name="username" required="required" /><br /><br />
			<label>Enter Password: &nbsp;</label><input type="password" name="password" required="required" /><br /><br />
			<input type="submit" value="Login"/><br /><br />
		</form>
		<p>Don't have a account? <a href="MainController?page=sign_up">Sign Up</a></p>
		
		
	</fieldset>
</body>
</html>