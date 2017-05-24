package pl.salesmanagement.model;

public class ClientModelForm extends Client{

	private String provinceName;
	private String industryName;
	private String disabled;
	private int allMeetings;
	
	//MethodsClient
	public ClientModelForm(Client client) {
		super(client.idClient, client.firstname, client.lastname, client.idProvince, client.address, client.city, 
				client.zipCode, client.idIndustry, client.company, client.numberPhone, client.email, client.activity);
		this.provinceName = setProvinceName(idProvince);
		this.industryName = setIndustryName(idIndustry);
	}
	
	public ClientModelForm(ClientModelForm client) {
		super(client.idClient, client.firstname, client.lastname, client.idProvince, client.address, client.city, 
				client.zipCode, client.idIndustry, client.company, client.numberPhone, client.email, client.activity);
		this.provinceName = setProvinceName(idProvince);
		this.industryName = setIndustryName(idIndustry);
		this.disabled = client.disabled;
		this.allMeetings= client.allMeetings;
	}
	
	private String setProvinceName(long idProvince) {
		String provinceName=Province.findTheProvinceNameAfterId(idProvince);
		return provinceName;
	}
	
	private String setIndustryName(long idIndustry) {
		String industryName=Province.findTheProvinceNameAfterId(idIndustry);
		return industryName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public int getAllMeetings() {
		return allMeetings;
	}

	public void setAllMeetings(int allMeetings) {
		this.allMeetings = allMeetings;
	}


}
