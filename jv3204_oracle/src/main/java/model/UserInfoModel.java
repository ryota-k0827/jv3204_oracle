package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserInfoDAO;
import dto.UserInfoDTO;

public class UserInfoModel {
	
	/**
	 * idで検索し、存在チェックを行うメソッド（true=存在, false=存在しない）
	 * LoginServlet, UserRegisterServletで使用
	 * @param id
	 * @return userExist
	 * @throws SQLException
	 */
	public boolean checkUserExist(int id) {
		// ResultSetを初期化
		ResultSet rs = null;
		// daoをインスタンス化
		UserInfoDAO dao = new UserInfoDAO();
		// 戻り値を初期化
		boolean userExist = false;
		try {
			// データを取得
			rs = dao.searchUserInfo(id);
			if (rs.next()) {
				userExist = true;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return userExist;		
	}
	
	/**
	 * IDとpassが一致した時、ユーザ情報を返却（else: nullを返却）
	 * LoginServletで使用
	 * @param id
	 * @param pass
	 * @return dto
	 * @throws SQLException
	 */
	public UserInfoDTO getUserInfo(int id, String pass) {
		// ResultSetを初期化
		ResultSet rs = null;
		// dtoをインスタンス化
		UserInfoDTO dto = new UserInfoDTO();
		// daoをインスタンス化
		UserInfoDAO dao = new UserInfoDAO();
		
		try {
			// データを取得
			rs = dao.searchUserInfo(id, pass);
			if (rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setClassification(rs.getInt("classification"));
			} else {
				dto = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return dto;		
	}

	/**
	 * ユーザ登録
	 * UserRegisterServletで使用
	 * @param id
	 * @param name
	 * @param pass
	 * @param classification
	 * @throws SQLException
	 */
	public void addUserInfo(int id, String name, String pass, int classification) {
		
		// daoをインスタンス化
		UserInfoDAO dao = new UserInfoDAO();
		// dtoをインスタンス化
		UserInfoDTO dto = new UserInfoDTO();

		// ユーザ情報をdtoにセット
		dto.setId(id);
		dto.setName(name);
		dto.setPass(pass);
		dto.setClassification(classification);

		// dtoを引数にしてdaoを実行
		try {
			dao.insertUserInfo(dto);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			// データベースとの接続を切断
			dao.close();
		}

	}

}
