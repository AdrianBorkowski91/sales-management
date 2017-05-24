package pl.salesmanagement.model;

public enum Province {
	DOLNOSLASKIE (1, "dolnośląskie"), KUJAWSKO_POMORSKIE (2, "kujawsko-pomorskie"), LUBELSKIE (3, "lubelskie"), 
	LUBUSKIE (4, "lubuskie"), LODZKIE (5, "łódzkie"), MALOPOLSKIE (6, "małopolskie"), MAZOWIECKIE (7, "mazowieckie"), 
	OPOLSKIE (8, "opolskie"), PODKARPACKIE (9, "podkarpackie"), PODLASKIE (10, "podlaskie"), POMORSKIE (11, "pomorskie"),
	SLASKIE (12, "śląskie"), SWIETOKRZYSKIE (13, "świętokrzyskie"), WARMINSKO_MAZURSKIE (14, "warmińsko-mazurskie"), 
	WIELKOPOLSKIE (15, "wielkopolskie"), ZACHODNIOPOMORSKIE (16, "zachodniopomorskie");
	
	private final long idProvince;
	private final String name;
	
	private Province(long idProvince, String name) {
		this.idProvince= idProvince;
		this.name= name;
	}
	
	public static String findTheProvinceNameAfterId(long idProvince){
		
		switch ((int)idProvince) {
        case 1:  return DOLNOSLASKIE.name;
        case 2:  return KUJAWSKO_POMORSKIE.name;
        case 3:  return LUBELSKIE.name;
        case 4:  return LUBUSKIE.name;
        case 5:  return LODZKIE.name;
        case 6:  return MALOPOLSKIE.name;
        case 7:  return MAZOWIECKIE.name;
        case 8:  return OPOLSKIE.name;
        case 9:  return PODKARPACKIE.name;
        case 10:  return PODLASKIE.name;
        case 11:  return POMORSKIE.name;
        case 12:  return SLASKIE.name;
        case 13:  return SWIETOKRZYSKIE.name;
        case 14:  return WARMINSKO_MAZURSKIE.name;
        case 15:  return WIELKOPOLSKIE.name;
        case 16:  return ZACHODNIOPOMORSKIE.name;       
		}
	return null;
	}
	
	public static long findTheProvinceNameAfterName(String name){

		switch (name) {
        case "dolnośląskie":  return DOLNOSLASKIE.idProvince;
        case "kujawsko-pomorskie":  return KUJAWSKO_POMORSKIE.idProvince;
        case "lubelskie":  return LUBELSKIE.idProvince;
        case "lubuskie":  return LUBUSKIE.idProvince;
        case "łódzkie":  return LODZKIE.idProvince;
        case "małopolskie":  return MALOPOLSKIE.idProvince;
        case "mazowieckie":  return MAZOWIECKIE.idProvince;
        case "opolskie":  return OPOLSKIE.idProvince;
        case "podkarpackie":  return PODKARPACKIE.idProvince;
        case "podlaskie":  return PODLASKIE.idProvince;
        case "pomorskie":  return POMORSKIE.idProvince;
        case "śląskie":  return SLASKIE.idProvince;
        case "świętokrzyskie":  return SWIETOKRZYSKIE.idProvince;
        case "warmińsko-mazurskie":  return WARMINSKO_MAZURSKIE.idProvince;
        case "wielkopolskie":  return WIELKOPOLSKIE.idProvince;
        case "zachodniopomorskie":  return ZACHODNIOPOMORSKIE.idProvince;       
		}
	return 0;
	}
	
	public long getIdProvince() {
		return idProvince;
	}
	
}
