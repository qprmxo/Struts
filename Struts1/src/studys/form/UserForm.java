package studys.form;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm{

	private String id;
	private String pass;
	private String name;
	private String kana;
	
	private int no;
	private Date birth;
	private String club;
	
	public UserForm(String id, String name, String kana, Date birth, String club) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.birth = birth;
		this.club = club;
	}
	public UserForm(String id, String pass, String name, String kana, Date birth, String club) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.kana = kana;
		this.birth = birth;
		this.club = club;
	}
	public UserForm() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	
	
}
