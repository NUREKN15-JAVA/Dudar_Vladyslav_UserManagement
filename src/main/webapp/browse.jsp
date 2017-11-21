<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>User management/Browse</title></head>
<body>
<table id=”userTable” border=”1”>
    <c:forEach var="user" items="${sessionScope.users}">
    <tr>
        <th></th>
        <th>First name</th>
        <th>Last name</th>
        <th>Date of birth</th>
    </tr>
    <tr>
        <td><input type=”radio” name=”id” id=”id” value=””></td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.birthDate}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>