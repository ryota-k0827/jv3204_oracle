package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserInfoModel;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("utf8");
		
		//jspから値取得
		String strUserId = request.getParameter("user_id");
		String userName = request.getParameter("user_name");
		String pass = request.getParameter("pass");
		int classification = Integer.parseInt(request.getParameter("classification"));
		int userId;
		
		//入力桁数を取得
		int userIdNum = strUserId.length();
		int userNameNum = userName.length();
		int passNum = pass.length();

		/**
		 * 空白チェック
		 * ・全項目空白時
		 * ・ユーザID空白時
		 * ・ユーザ名空白時
		 * ・パスワード空白時
		 */
		if (strUserId.equals("") && userName.equals("") && pass.equals("")) {
			String result = "ユーザID、ユーザ名、パスワードを入力してください。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		} else if (strUserId.equals("")) {
			String result = "ユーザIDを入力してください。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		} else if (userName.equals("")) {
			String result = "ユーザ名を入力してください。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		} else if (pass.equals("")) {
			String result = "パスワードを入力してください。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		
		//数値チェック
		try {
			userId = Integer.parseInt(strUserId);
		} catch(NumberFormatException e) {
			String result = "ユーザIDには数値を入力してください。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		
		/**
		 * 桁数チェック
		 */
		if (userIdNum > 5) {
			String result = "ユーザIDの桁数は5桁迄です。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		} else if (userNameNum > 10) {
			String result = "ユーザ名の桁数は10桁迄です。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		} else if (passNum > 8 || passNum < 6) {
			String result = "パスワードの桁数は6~8桁迄です。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			
			System.out.println(result);
			return;
		}
		

		// 入力チェック通過後の処理
		// UserInfoModelをインスタンス化
		UserInfoModel model = new UserInfoModel();
		
		// 重複チェック---------------------------------------------------------------------
		// データを取得
		boolean userExist = model.checkUserExist(userId);
		if (userExist) {
			String result = "ユーザID：" + strUserId + "　は既に存在します。";
			String backPages = "user_input.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request, response);
			return;
		}
		// -------------------------------------------------------------------------------
		
		// ユーザ登録
		model.addUserInfo(userId, userName, pass, classification);
		
		System.out.println("============================");
		System.out.println("ユーザID: " + userId);
		System.out.println("ユーザ名: " + userName);
		System.out.println("区分: " + classification);
		System.out.println("============================");
		System.out.println("ユーザ登録完了");
		
		// 登録完了ページに推移
		RequestDispatcher rd = request.getRequestDispatcher("/user_result.jsp");
		rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
