package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
	public int insertUser(String id, String pass, String name, String kana) {
		try {
			connect();
			pstmt = con.prepareStatement("insert into user1 values(?,?,?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, kana);
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
			
		}finally {
			disconnect();
		}
	}
	public int insertUserdetail(String id, Date date, String club) {
		try {
			connect();
			pstmt = con.prepareStatement("insert into userdetail values(seq_no.nextval,?,?,?)");
			pstmt.setString(1, id);
			pstmt.setDate(2, date);
			pstmt.setString(3, club);
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			disconnect();
		}
	}
	public int updateUser(String name, String kana, String id) {
		try {
			connect();
			pstmt = con.prepareStatement("update user1 set name=?, kana=? where id=?");
			pstmt.setString(1, name);
			pstmt.setString(2, kana);
			pstmt.setString(3, id);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			disconnect();
		}
	}
	public int updateUserdetail(Date date, String club, String id) {
		try {	
			connect();
			pstmt = con.prepareStatement("update userdetail set birth=?, club=? where id=?");
			pstmt.setDate(1, date);
			pstmt.setString(2, club);
			pstmt.setString(3, id);
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			disconnect();
		}
	}
	public int deleteUser(String id) {
		try {
			connect();
			pstmt = con.prepareStatement("delete from user1 where id=?");
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			disconnect();
		}
	}
	public int deleteUserdetail(String id) {
		try {
			connect();
			pstmt = con.prepareStatement("delete from userdetail where id=?");
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
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
			Date birthD = rs.getDate("birth");
			String birth = new SimpleDateFormat("yyyy-MM-dd").format(birthD);
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
				Date birthD = rs.getDate("birth");
				String birth = new SimpleDateFormat("yyyy-MM-dd").format(birthD);
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
