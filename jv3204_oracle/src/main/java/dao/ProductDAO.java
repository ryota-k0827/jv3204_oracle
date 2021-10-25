package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.ProductDTO;

public class ProductDAO {
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	private String driverName = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/jv32?characterEncoding=utf8";
	private String user = "root";
	private String password = "";

	/**
	 * 商品テーブルのデータを全取得する。
	 *
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet searchProducts() throws SQLException {

		try {

			// JDBCドライバのロード
			Class.forName(this.driverName);
			con = DriverManager.getConnection(this.url, this.user, this.password);

			String sql ="SELECT * FROM products";
			//SQLの生成
			ps = con.prepareStatement(sql);
			// SQLを実行
			rs = ps.executeQuery();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return rs;
	}
	/**
	 * 検索条件に応じた商品テーブルのデータを取得する
	 * 
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet searchProducts(String name, int categoryId, int lowPrice, int highPrice) throws SQLException {

		try {

			// JDBCドライバを用意
			Class.forName(this.driverName);
			// DB 接続
			con = DriverManager.getConnection(this.url, this.user, this.password);
			
			// 処理
			//価格(high)以外空白時
			if (name.equals("") && categoryId == 0 && lowPrice == 0) {
				String sql = "SELECT * FROM products WHERE price <= ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setInt(1, highPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(high)以外空白");
			}
			//価格(low)以外空白時
			else if (name.equals("") && categoryId == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE price >= ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setInt(1, lowPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(low)以外空白");
			}
			//カテゴリー以外空白時
			else if (name.equals("") && lowPrice == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE category_id = ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setInt(1, categoryId);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("カテゴリー以外空白");
			}
			//商品名以外空白時
			else if (categoryId == 0 && lowPrice == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE name = ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setString(1, name);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("商品名以外空白");
			}
			//価格(high)以外空白時
			else if (name.equals("") && categoryId == 0 && lowPrice == 0) {
				String sql = "SELECT * FROM products WHERE price <= ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setInt(1, highPrice);
				
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(high)以外空白");
			}
			//価格(low)以外空白時
			else if (name.equals("") && categoryId == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE price >= ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setInt(1, lowPrice);
				
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(low)以外空白");
			}
			//カテゴリー以外空白時
			else if (name.equals("") && lowPrice == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE category_id = ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setInt(1, categoryId);
				
				System.out.println("sql2→" + ps.toString());
				System.out.println("カテゴリー以外空白");
			}
			//商品名以外空白時
			else if (categoryId == 0 && lowPrice == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE name = ?;";
				ps = con.prepareStatement(sql);
				
				System.out.println("sql1→" + sql);
				
				ps.setString(1, name);
				
				System.out.println("sql2→" + ps.toString());
				System.out.println("商品名以外空白");
			}
			//価格が両方空白時
			else if(lowPrice == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE name = ? AND category_id = ?;";
				ps = con.prepareStatement(sql);
	
				System.out.println("sql1→" + sql);
	
				ps.setString(1, name);
				ps.setInt(2, categoryId);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格が両方空白");
			}
			//価格(high)とカテゴリーが空白時
			else if(categoryId == 0 && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE name = ? AND price >= ?;";
				ps = con.prepareStatement(sql);
	
				System.out.println("sql1→" + sql);
	
				ps.setString(1, name);
				ps.setInt(2, lowPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(high)とカテゴリーが空白");
			}
			//価格(high)と商品名が空白時
			else if(name.equals("") && highPrice == 0) {
				String sql = "SELECT * FROM products WHERE category_id = ? AND price >= ?;";
				ps = con.prepareStatement(sql);
	
				System.out.println("sql1→" + sql);
	
				ps.setInt(1, categoryId);
				ps.setInt(2, lowPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(high)と商品名が空白");
			}
			//価格(low)とカテゴリーが空白時
			else if(categoryId == 0 && lowPrice == 0) {
				String sql = "SELECT * FROM products WHERE name = ? AND price <= ?;";
				ps = con.prepareStatement(sql);
	
				System.out.println("sql1→" + sql);
	
				ps.setString(1, name);
				ps.setInt(2, highPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(low)とカテゴリーが空白");
			}
			//価格(low)と商品名が空白時
			else if(name.equals("") && lowPrice == 0) {
				String sql = "SELECT * FROM products WHERE category_id = ? AND price <= ?;";
				ps = con.prepareStatement(sql);
	
				System.out.println("sql1→" + sql);
	
				ps.setInt(1, categoryId);
				ps.setInt(2, highPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("価格(low)と商品名が空白");
			}
			//カテゴリーと商品名が空白時
			else if(name.equals("") && categoryId == 0) {
				String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ?;";
				ps = con.prepareStatement(sql);
	
				System.out.println("sql1→" + sql);
	
				ps.setInt(1, lowPrice);
				ps.setInt(2, highPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("カテゴリーと商品名が空白");
			}
			//全項目入力時
			else {
				String sql = "SELECT * FROM products WHERE name = ? AND category_id = ? AND price BETWEEN ? AND ?;";
				ps = con.prepareStatement(sql);
	
				System.out.println("sql1→" + sql);
	
				ps.setString(1, name);
				ps.setInt(2, categoryId);
				ps.setInt(3, lowPrice);
				ps.setInt(4, highPrice);
	
				System.out.println("sql2→" + ps.toString());
				System.out.println("全項目入力");
			}

		
			// SQLを実行
			rs = ps.executeQuery();
			System.out.println(rs);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return rs;
	}
	/**
	 * 検索条件に応じた商品テーブルのデータを取得する
	 * 
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet searchProducts(int no) throws SQLException {

		try {
			// JDBCドライバを用意
			Class.forName(this.driverName);
			// DB 接続
			con = DriverManager.getConnection(this.url, this.user, this.password);
			// sqlを生成
			String sql = "SELECT * FROM products WHERE no = ?;";
			ps = con.prepareStatement(sql);
			// sqlに商品IDを付与
			ps.setInt(1, no);
			// SQLを実行
			rs = ps.executeQuery();
		} catch (ClassNotFoundException e) {

			// JDBCドライバが見つからなかったときの処理
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * テーブルに商品のデータを追加する
	 *
	 * @param dto
	 * @throws SQLException
	 */
	public void insert(ProductDTO dto) throws SQLException {

		try {
			Class.forName(this.driverName);

			con = DriverManager.getConnection(this.url, this.user, this.password);

			// INSERT文を生成
			ps = con.prepareStatement("INSERT INTO products(no, name, category_id, price) VALUES(?, ?, ?, ?);");

			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getCategoryId());
			ps.setInt(4, dto.getPrice());

			// SQLを実行
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {

			// JDBCドライバが見つからなかったときの処理
			e.printStackTrace();
		}
	}


	/**
	 * データベース接続を切断
	 */
	public void close() {

		try {
			// データベースとの接続を切断
			if(this.con != null) {
				con.close();
			}
			if(this.ps != null) {
				ps.close();
			}
			if(this.rs != null) {
				rs.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}