package studys.form;

import org.apache.struts.validator.ValidatorForm;

public class SearchForm extends ValidatorForm{

	private String id;
	private String name;
	private String kana;
	
	public SearchForm() {
		super();
	}

	public SearchForm(String id, String name, String kana) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	
}
