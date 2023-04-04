<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
  <table>
    <thead>
    <th>ID</th>
    <th>UserName</th>
    <th>Application</th>
    <th>Roles</th>
    </thead>

    <forEach items="${allUsers}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.application}</td>
        <td>
          <forEach items="${user.roles}" var="role">${role.name}; </forEach>
        </td>
        <td>
          <form action="${pageContext.request.contextPath}/admin" method="post">
            <input type="hidden" name="userId" value="${user.id}"/>
            <input type="hidden" name="username" value="${username}"/>
            <input type="hidden" name="application" value="${application}"/>

            <input type="hidden" name="action" value="delete"/>
            <button type="submit">Delete</button>
          </form>

        </td>

      </tr>
    </forEach>
  </table>
  <a href="/">Главная</a>
</div>
</body>
</html>
