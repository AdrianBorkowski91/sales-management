package pl.salesmanagement.model;

public class UserModelForm extends User{
	
	private String repeatPassword;
	private String oldPassword;
	
	public UserModelForm(String username, String email, String password, String repeatPassword, String oldPassword) {
		super(username, email, password);
		this.setRepeatPassword(repeatPassword);
		this.setOldPassword(oldPassword);
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	
}
