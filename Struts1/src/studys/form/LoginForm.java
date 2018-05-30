package studys.form;

import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {

	private String id;
	private String pass;
	
	public LoginForm() {
		super();
	}
	public LoginForm(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
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

	
	
}
