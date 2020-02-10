<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../easyui/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
          var param={
              'id':1,
              'userName':'小红',
              'sexCode':1,
              'note':'备注很长'
          }/*
          $.ajax({
              type:"post",
              url:"./user",
              contentType:"application/json",
              data:JSON.stringify(param),
              success:function (result) {
                  
              }
            });*/
            $.ajax({
                type:"get",
                url:"/user/1",
                contentType:"application/json",

                success:function (result) {

                }
            });
        });
    </script>
</head>
<body>
 <h1>restful页面</h1>
</body>
</html>
