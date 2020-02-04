<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../easyui/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
          var pojo={
              id:null,
              date:'2020-02-02',
              doubleValue:99999.09,
              integer:100,
              range:1000,
              email:'email',
              size:'1ws3qsa',
              regexp:'a,b,c'
          }
          $.ajax({
              type:"post",
              url:"./validate",
             // contentType:"application/json",
              data:{
                  id:123,
                  date:'2020-01-02',
                  doubleValue:99999.09,
                  integer:100,
                  range:1000,
                  email:'email',
                  size:'一个字二个字三个字四个字五个字一个字二个字三个字四个字五个字'
                 // regexp:'a,b,c'
              },//JSON.stringify(pojo),
              success:function (result) {
                  
              }
            });
        });
    </script>
</head>
<body>
 <h1>第一个页面</h1>
</body>
</html>
