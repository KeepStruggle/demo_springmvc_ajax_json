<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajax</title>
    <%-- 要是jQuery, 需要导入  注意点：路径问题 --%>
    <script src="statics/js/jquery-1.8.0.min.js" ></script>
    <script type="text/javascript">
        function a1() {
            var msg = {'name':$("#txtName").val()};
            $.ajax({
                url:"${pageContext.request.contextPath}/ajax1",
                type:"post",
                data: msg,
                success:function (data, status) {
                    console.log(data);
                    console.log(status);
                }
            });

        }
    </script>
</head>

<body>
<%--onblur失去焦点触发事件--%>
用户名：
<input type="text" id="txtName" onblur="a1()" />


</body>
</html>
