<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register form</title>
</head>
<body>
<form action="register" method="post">
    <div>${errorMessage}</div>

    <input type="submit" formaction="premium" value="Strona premium">
    <input type="submit" value="Panel administratora" formaction="admin">
    <input type="submit" value="Zaloguj" formaction="login">
    <input type="submit" value="Zarejestruj" formaction="register">
    <input type="submit" value="Profil użytkownika" formaction="profile">
</form>

</body>
</html>