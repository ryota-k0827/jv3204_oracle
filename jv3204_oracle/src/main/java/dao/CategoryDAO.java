package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	private String url = "jdbc:oracle:oci:";
	private String user = "user01/";
	private String password = "User01";
	private String netService = "@WINSRV01";
	private String driverName ="oracle.jdbc.driver.OracleDriver";

	/**
	 * カテゴリーテーブルのデータを全首都
	 *
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet searchCategory() throws SQLException {

		try {
			Class.forName(this.driverName);
			con = DriverManager.getConnection(this.url + this.user + this.password + this.netService);
			String sql ="SELECT * FROM categories";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * カテゴリーIDでデータを取得し、返却する。
	 * 
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet searchCategory(int categoryId) throws SQLException {

		try {
			Class.forName(this.driverName);
			con = DriverManager.getConnection(this.url + this.user + this.password + this.netService);
			String sql ="SELECT * FROM categories WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * データベース接続を切断
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
			e.printStackTrace();
		}
	}

}
