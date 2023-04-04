

<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org" xmlns:form="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8">
  <title>Registration</title>
</head>

<body>
<div>
  <form:form method="POST" modelAttribute="userForm">
    <h2>Registration</h2>
    <div>
      <form:input type="text" path="username" placeholder="Username"
                  autofocus="true"></form:input>
      <form:errors path="username"></form:errors>
        ${usernameError}
    </div>
    <div>
      <form:input type="password" path="password" placeholder="Password"></form:input>
    </div>
    <div>
      <form:input type="password" path="passwordConfirm"
                  placeholder="Confirm your password"></form:input>
      <form:errors path="password"></form:errors>
        ${passwordError}
    </div>
    <button type="submit">Registration</button>
  </form:form>
  <a href="/">Main</a>
</div>
</body>
</html>