<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String strName = (String)session.getAttribute("NAME");
    if (strName == null) {
    	String result = "セッションが無効です。<br>再度ログインしてください。";
		String backPages = "login.jsp";
		request.setAttribute("RESULT", result);
		request.setAttribute("BACKPAGES", backPages);
		
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
		
		rd.forward(request, response);
		return;
    }
    int cf = (int)session.getAttribute("CF");
    if (cf != 2) {
    	String result = "このページにアクセスする権限がありません。";
		String backPages = "select_page.jsp";
		request.setAttribute("RESULT", result);
		request.setAttribute("BACKPAGES", backPages);
		
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
		
		rd.forward(request, response);
		return;
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録完了</title>
</head>
<body>
<p>【管理者】：<% out.print(strName); %>さん</p>
<h1>商品登録完了です。</h1>
<a href="product_manager.jsp">商品管理へ</a>　
<a href="select_page.jsp">TOPへ</a>
</body>
</html>