package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studys.form.UserForm;

/**
 * @author choi.hyuncheol
 * DB関連ロジック。
 */
public class DBconnect {
	
	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	/**
	 * DBに接続、トランザクション開始。
	 */
	public void connect(){
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url,"kadaidb","root");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
			System.out.println("DB接続失敗");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続失敗");
		}
	}

	/**
	 * DBから切断、トランザクション終了。
	 */
	public void disconnect(){
		try {
			if(rs!=null) {
				rs.close();
			}
			if(con!=null) {
				con.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続失敗");
		}
	}
	
	/**
	 * INSERT 、UPDATE、DELETEを実行。
	 * @param sql : 実行するsql文
	 * @return int : 完了した作業の数
	 */
	public int updateExec(String sql) {
		try {
			connect();
			pstmt = con.prepareStatement(sql);
			int n = pstmt.executeUpdate();
			
			System.out.println("作業完了");
			return n;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("作業失敗");
			return 0;
		}finally {
			disconnect();
		}
	}
	
	/**
	 * ユーザの存在有無を確認。
	 * @param id : ユーザーのID
	 * @param pwd : ユーザーのパスワード
	 * @return boolean : 有であればtrue、無であればfalse
	 */
	public boolean isUser(String id) {
		try {
			connect();
			pstmt = con.prepareStatement("select * from user1 where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}		
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("データ情報の取得に失敗しました。");
			return false;
		}finally {
			disconnect();
		}
	}
	
	/**
	 * ユーザーのIDでディテールな情報を呼び出す。
	 * @param id : ユーザーのID
	 * @return UserForm : ユーザーの情報
	 */
	public UserForm findList(String id) {
		
		String sql = "select u.*,ud.* from user1 u, userdetail ud where u.id=ud.id and u.id=?";
				
		try {
			connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			String pass = rs.getString("pass");
			String name = rs.getString("name");
			String kana = rs.getString("kana");
			Date birth = rs.getDate("birth");
			String club = rs.getString("club");
			
			UserForm userForm = new UserForm(id, pass, name, kana, birth, club);
			
			return userForm;
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("データ情報の取得に失敗しました。");
			return null;
		}finally {
			disconnect();
		}
	}
	
	/**
	 * 検索条件と前方一致した情報を呼び出す。
	 * @param idValue : ユーザーのID
	 * @param nameValue : ユーザーの名前
	 * @param kanaValue : ユーザーのカナ
	 * @return ArrayList : 前方一致した情報
	 */
	public ArrayList<UserForm> findList(String idValue, String nameValue, String kanaValue) {
		ArrayList<UserForm> list = new ArrayList<>();
		String idC = idValue + "%";
		String nameC = nameValue + "%";
		String kanaC = kanaValue + "%";		
	
		String sql = "select u.*,ud.* from user1 u, userdetail ud where u.id=ud.id and u.id like ? and u.name like ? and u.kana like ? order by ud.no asc";
				
		try {
			connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, idC);
			pstmt.setString(2, nameC);
			pstmt.setString(3, kanaC);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String kana = rs.getString("kana");
				Date birth = rs.getDate("birth");
				String club = rs.getString("club");
				
				UserForm user = new UserForm(id, name, kana, birth, club);
				
				list.add(user);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("データ情報の取得に失敗しました。");
			return null;
		}finally {
			disconnect();
		}
	}
}
