package pl.salesmanagement.model;

public class Client {

	protected long idClient;
	protected long idUser;
	protected String firstname;
	protected String lastname;
	protected long idProvince;
	protected String address;
	protected String city;
	protected String zipCode;
	protected long idIndustry;
	protected String company;
	protected String numberPhone;
	protected String email;
	protected String activity;
	
	public Client(){}
	
	//MyClientsNewController
	public Client(long idUser, String firstname, String lastname, long idProvince, String address,
			String city, String zipCode, long idIndustry, String company, String numberPhone, String email) {
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.idProvince = idProvince;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.idIndustry = idIndustry;
		this.company = company;
		this.numberPhone = numberPhone;
		this.email = email;
	}
	
	//MyClientsEditController
	public Client(long idClient, String firstname, String lastname, long idProvince, String address,
			String city, String zipCode, long idIndustry, String company, String numberPhone, String email, String activity) {
		this.idClient = idClient;
		this.firstname = firstname;
		this.lastname = lastname;
		this.idProvince = idProvince;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.idIndustry = idIndustry;
		this.company = company;
		this.numberPhone = numberPhone;
		this.email = email;
		this.activity = activity;
	}

	//DAO
	public Client(Client client) {
		this.idClient = client.idClient;
		this.idUser = client.idUser;
		this.firstname = client.firstname;
		this.lastname = client.lastname;
		this.idProvince = client.idProvince;
		this.address = client.address;
		this.city = client.city;
		this.zipCode = client.zipCode;
		this.idIndustry = client.idIndustry;
		this.company = client.company;
		this.numberPhone = client.numberPhone;
		this.email = client.email;
		this.activity = client.activity;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(long idProvince) {
		this.idProvince = idProvince;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public long getIdIndustry() {
		return idIndustry;
	}

	public void setIdIndustry(long idIndustry) {
		this.idIndustry = idIndustry;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", idUser=" + idUser + ", firstname=" + firstname + ", lastname="
				+ lastname + ", idProvince=" + idProvince + ", address=" + address + ", city=" + city + ", zipCode="
				+ zipCode + ", idIndustry=" + idIndustry + ", company=" + company + ", numberPhone=" + numberPhone
				+ ", email=" + email + ", activity=" + activity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + (int) (idClient ^ (idClient >>> 32));
		result = prime * result + (int) (idIndustry ^ (idIndustry >>> 32));
		result = prime * result + (int) (idProvince ^ (idProvince >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((numberPhone == null) ? 0 : numberPhone.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Client other = (Client) obj;
		if (activity== null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (idClient != other.idClient)
			return false;
		if (idIndustry != other.idIndustry)
			return false;
		if (idProvince != other.idProvince)
			return false;
		if (idUser != other.idUser)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (numberPhone == null) {
			if (other.numberPhone != null)
				return false;
		} else if (!numberPhone.equals(other.numberPhone))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}


}
