package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserInfoDTO;

public class UserInfoDAO {

	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	private String url = "jdbc:mysql://localhost:3306/jv32?characterEncoding=utf8";
	private String user = "root";
	private String password = "";
	private String driverName="com.mysql.cj.jdbc.Driver";
	
	/**
	 * ユーザID重複チェック用
	 * @param id
	 * @return rs
	 * @throws ClassNotFoundException
	 */
	public ResultSet searchUserInfo(int id) throws SQLException {
		
		try {
			Class.forName(this.driverName);
			con = DriverManager.getConnection(this.url, this.user, this.password);
			String sql = "SELECT * FROM user_info WHERE id = ?;";
			// SQLを生成
			ps = con.prepareStatement(sql);
			// プレースホルダーにidを付与
			ps.setInt(1, id);
			// SQL実行
			rs = ps.executeQuery();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return rs;
	}
	
	/**
	 * ログイン用
	 * @param id
	 * @param pass
	 * @return rs
	 * @throws ClassNotFoundException
	 */
	public ResultSet searchUserInfo(int id, String pass) throws SQLException {
		
		try {
			Class.forName(this.driverName);
			con = DriverManager.getConnection(this.url, this.user, this.password);
			String sql = "SELECT * FROM user_info WHERE id = ? AND pass = ?;";
			// SQLを生成
			ps = con.prepareStatement(sql);
			// プレースホルダーにidを付与
			ps.setInt(1, id);
			ps.setString(2, pass);
			// SQL実行
			rs = ps.executeQuery();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * 会員登録用
	 * @param dto
	 * @throws ClassNotFoundException
	 */
	public void insertUserInfo(UserInfoDTO dto) throws SQLException {

		try {
			Class.forName(this.driverName);
			con = DriverManager.getConnection(this.url, this.user, this.password);

			ps = con.prepareStatement("INSERT INTO user_info(id, name, pass, classification) VALUES(?, ?, ?, ?);");

			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getPass());
			ps.setInt(4, dto.getClassification());

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * データベース切断
	 * @throws SQLException
	 */
	public void close() {

		try {

			// データベースとの接続を切断
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}

		} catch (SQLException e) {

			// データベースからの切断に失敗した場合
			e.printStackTrace();
		}
	}

}
