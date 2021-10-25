<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>

<body>
<h1>新規登録</h1>
<form action="./UserRegisterServlet" method="post">
<p>ユーザID<input type="text" name="user_id" value=""></p>
<p>ユーザ名<input type="text" name="user_name" value=""></p>
<p>パスワード<input type="password" name="pass" value=""></p>
<p>区分　<input type="radio" name="classification" value=1 checked>会員
<input type="radio" name="classification" value=2>管理者
</p>
<button type ="submit" name ="sb" value="register">登録</button>
</form>
</body>

</html>