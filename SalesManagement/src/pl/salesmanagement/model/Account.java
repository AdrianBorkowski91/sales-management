package pl.salesmanagement.model;

public class Account {

	private long idAccount;
	private String reminder;
	private String raportModified;
	
	public Account(){}
	
	//DAO
	public Account(Account account) {
		this.idAccount = account.idAccount;
		this.reminder = account.reminder;
		this.raportModified = account.raportModified;
	}
	
	public long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(long idAccount) {
		this.idAccount = idAccount;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getRaportModified() {
		return raportModified;
	}

	public void setRaportModified(String raportModified) {
		this.raportModified = raportModified;
	}

	@Override
	public String toString() {
		return "Account [idAccount=" + idAccount + ", reminder=" + reminder + ", raportModified=" + raportModified + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAccount ^ (idAccount >>> 32));
		result = prime * result + ((raportModified == null) ? 0 : raportModified.hashCode());
		result = prime * result + ((reminder == null) ? 0 : reminder.hashCode());
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
		Account other = (Account) obj;
		if (idAccount != other.idAccount)
			return false;
		if (raportModified == null) {
			if (other.raportModified != null)
				return false;
		} else if (!raportModified.equals(other.raportModified))
			return false;
		if (reminder == null) {
			if (other.reminder != null)
				return false;
		} else if (!reminder.equals(other.reminder))
			return false;
		return true;
	}

}
