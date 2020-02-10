<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自定义登录表单</title>
</head>
<body>
 <form action="/logout/page" method="post">

   <p> <input type="submit" value="登出"></p>
     <input type="text" id="${_csrf.parameterName}" name="${_csrf.parameterName}" value="${_csrf.token}">
 </form>
</body>
</html>
