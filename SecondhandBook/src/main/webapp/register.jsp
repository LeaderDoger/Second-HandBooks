<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册界面</title>
<style>
@CHARSET "UTF-8";
* {
            margin: 0;
            padding: 0;
        }
         
        html {
            height: 100%;
            width: 100%;
            overflow: hidden;
            margin: 0;
            padding: 0;
            background: url(./backgroundimg.jpg) no-repeat 0px 0px;
            background-repeat: no-repeat;
            background-size: 100% 100%;
            -moz-background-size: 100% 100%;
        }
         
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
        }
         
        #loginDiv {
            width: 37%;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 300px;
            background-color: rgba(75, 81, 95, 0.3);
            box-shadow: 7px 7px 17px rgba(52, 56, 66, 0.5);
            border-radius: 5px;
        }
         
        #name_trip {
            margin-left: 50px;
            color: red;
        }
         
        p {
            margin-top: 30px;
            margin-left: 20px;
            text-align:center;
            color: azure;
        }
        input {
            margin-left: 15px;
            border-radius: 5px;
            border-style: hidden;
            height: 30px;
            width: 140px;
           background-color: rgba(216, 191, 216, 0.5);
            outline: none;
            color: #f0edf3;
            padding-left: 10px;
        }
        .topNav .login_area #login_in a {
    float: left;
    display: block;
    width: 48px;
    height: 28px;
    line-height: 28px;
    border: 1px solid #169af3;
    border-radius: 2px;
    text-align: center;
    color: #fff;
    cursor: pointer;
    background-color: #169af3;
}
         
        .button {
            border-color: cornsilk;
            background-color: rgba(100, 149, 237, .7);
            color: aliceblue;
            border-style: hidden;
            border-radius: 5px;
            width: 100px;
            height: 31px;
            font-size: 16px;
        }                                                                                                                                                                 
</style>
</head>
<body>
<div class="headtop"></div>
<div class="register">
  <form action="register" method="post">
  <h1 style="text-align: center;color: aliceblue;">注册</h1>
    <p>用户名:<input type="text" name="username" placeholder="用户名" required="required"></p>
    <p>密 码:<input type="password" name="password" placeholder="密  码" required="required"></p>
    <p>确认密码:<input type="password" name="qrpassword" placeholder="确认密码" required="required"></p>
  <p><input class="button" type="submit" value="注册" />
    <input class="button" type="button" value="返回" onclick="window.location.href='./login.jsp'"/></p>
  </form>
</div>
</body>
</html>
