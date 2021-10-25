package dto;

public class UserInfoDTO {	
	// フィールド変数
	private int id;
	private String name;
	private String pass;
	private int classification;
	
	// アクセサメソッド
	/**
	 * ユーザIDのゲッター
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * ユーザIDのセッター
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * ユーザ名のゲッター
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * ユーザ名のセッター
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * パスワードのゲッター
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * パスワードのセッター
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * ユーザ種別のゲッター
	 * @return classification
	 */
	public int getClassification() {
		return classification;
	}
	/**
	 * ユーザ種別のセッター
	 * @param classification
	 */
	public void setClassification(int classification) {
		this.classification = classification;
	}
}
