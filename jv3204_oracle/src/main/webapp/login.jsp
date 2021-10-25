<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    // セッションを破棄
    session.invalidate();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>ログイン</h1>
<form action="./LoginServlet" method="post">
<p>ユーザID<input type="text" name="user_id" value=""></p>
<p>パスワード<input type="password" name="pass" value=""></p>
<button type ="submit" name ="sb" value="login">ログイン</button><hr>
<a href="index.html">TOPへ</a>
</form>
</body>
</html>