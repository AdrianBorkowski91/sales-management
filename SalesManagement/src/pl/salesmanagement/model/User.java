package pl.salesmanagement.model;

public class User {
	
	private long idUser;
	protected String username;
	protected String email;
	protected String password;
	private long idAccount;
	private Account account;
	
	public User(){}
	
	//DAO
	public User(User user) {
		this.idUser = user.idUser;
		this.username = user.username;
		this.email = user.email;
		this.password = user.password;
		this.idAccount = user.idAccount;
	}
	
	//UserModelForm
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(long idAccount) {
		this.idAccount = idAccount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", idAccount=" + idAccount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = (int) (prime * result + idAccount);
		result = (int) (prime * result + idUser);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idAccount != other.idAccount)
			return false;
		if (idUser != other.idUser)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
