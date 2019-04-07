<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Privilege</title>

</head>
<body>
<h2>Here you can add privilege.</h2>

<form action="admin" method="post">
    <table class="table table-bordered" >
        <tr>
            <th>Username</th>
            <th>E-mail</th>
            <th>Premium</th>
        </tr>
        <c:forEach items="${userList}" var="element">
            <tr>
                <td>
                    <label>
                        <input type="text" class="form-control" type="text" readonly="readonly" name="name"
                               value="${element.uName}" />
                    </label></td>
                <td>
                    <label>
                        <input type="text" class="form-control" type="text" readonly="readonly"
                               value="${element.email}" />
                    </label></td>
                <td>
                    <label>
                        <input type="checkbox" name="isPremium" value="${element.uName}"
                               <c:if test="${element.isPremium}">checked</c:if> />
                    </label><br></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="saveData" value="Save">
</form>
</body>
</html>