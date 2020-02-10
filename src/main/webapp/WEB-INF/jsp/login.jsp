<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自定义登录表单</title>
</head>
<body>
 <form action="/login/page" method="post">
   <p>名称：<input id="username" name="username" type="text" value=""/></p>
   <p>密码：<input id="password" name="password" type="password" value=""/></p>
   <p>记住我：<input id="remember_me" name="remember-me" type="checkbox" /></p>
   <p> <input type="submit" value="登录"></p>
     <input type="text" id="${_csrf.parameterName}" name="${_csrf.parameterName}" value="${_csrf.token}">
 </form>
</body>
</html>
