package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserInfoDTO;
import model.UserInfoModel;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		//文字化け対策
		request.setCharacterEncoding("utf8");

		//jspから値取得
		String strUserId = request.getParameter("user_id");
		String pass = request.getParameter("pass");
		int userId;
		
		//入力桁数を取得
		int userIdNum = strUserId.length();
		int passNum = pass.length();

		//数値チェック
		try {
			userId = Integer.parseInt(strUserId);
		} catch(NumberFormatException e) {
			String result = "ユーザIDには数値を入力してください。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request,  response);
			
			System.out.println(result);
			return;
		}
		
		/**
		 * 空白チェック
		 * ・全項目空白時
		 * ・ユーザID空白時
		 * ・パスワード空白時
		 */
		if (strUserId.equals("") && pass.equals("")) {
			String result = "ユーザID、パスワードを入力してください。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request,  response);
			
			System.out.println(result);
			return;
		} else if (strUserId.equals("")) {
			String result = "ユーザIDを入力してください。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request,  response);
			
			System.out.println(result);
			return;
		} else if (pass.equals("")) {
			String result = "パスワードを入力してください。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request,  response);
			
			System.out.println(result);
			return;
		}
		/**
		 * 空白チェック通過後
		 * 桁数チェック
		 */
		if (userIdNum > 5) {
			String result = "ユーザIDの桁数は5桁迄です。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request,  response);
			
			System.out.println(result);
			return;
		} else if (passNum > 8 || passNum < 6) {
			String result = "パスワードの桁数は6~8桁迄です。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			
			rd.forward(request,  response);
			
			System.out.println(result);
			return;
		}

		//入力チェック通過後の処理		
		// UserInfoModelをインスタンス化
		UserInfoModel model = new UserInfoModel();
		
		// データをbeanListとして取得
		UserInfoDTO dto = model.getUserInfo(userId, pass);
		boolean userExist = model.checkUserExist(userId);
		// ユーザ情報が存在した場合（ログイン成功）
		if (dto != null) {
			// dtoのデータを変数に代入
			String getName = dto.getName();
			int getClassification = dto.getClassification();
			
			/**
			 * セッション
			 * 1.開始宣言
			 * 2.セッションに値をセット
			 */
			HttpSession session = request.getSession();
			session.setAttribute("NAME", getName);
			if (getClassification == 1) {
				System.out.println("ログイン成功");
				//商品検索ページへ推移
				response.sendRedirect("/jv3204/product_search.jsp");
				// 処理を抜ける
				return;
			} else {
				System.out.println("ログイン成功");
				//商品管理ページへ推移
				response.sendRedirect("/jv3204/product_manager.jsp");
				// 処理を抜ける
				return;
			}
		}
		// ユーザIDが正しくない時
		else if (!userExist) {
			System.out.println("ログイン失敗");
			String result = "ユーザIDが正しくありません。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			// エラーページに推移
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			rd.forward(request,  response);
		}
		
		// パスワードが正しくない時
		else {
			System.out.println("ログイン失敗");
			String result = "パスワードが正しくありません。";
			String backPages = "login.jsp";
			request.setAttribute("RESULT", result);
			request.setAttribute("BACKPAGES", backPages);
			// エラーページに推移
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp"); 
			rd.forward(request,  response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
