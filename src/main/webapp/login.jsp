<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login with username and password</h2>
<div>${errorMessage}</div>
<form action="login" method="post">
    <label>Username<input type="text" name="name" id="name" required/></label><br/>
    <label>Password<input type="password" name="pass" id="pass" required/></label><br/>
    <input type="submit" name="loginBtn" value="Login" />
</form>
If you don't heave an account, please <a href="register.jsp">register</a>.
</body>
</html>