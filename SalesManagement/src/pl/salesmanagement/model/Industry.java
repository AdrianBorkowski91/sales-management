package pl.salesmanagement.model;

public enum Industry {
	PRZEMYSL (1, "przemysł"), BUDOWNICTWO (2, "budownictwo"), ROLNICTWO (3, "rolnictwo"), LESNICTWO (4, "leśnictwo"),
	TRANSPORT (5, "transport"), LACZNOSC (6, "łączność"), HANDEL (7, "handel"), NIERUCHOMOSCI (8, "nieruchomości"),
	NAUKA_ROZWOJ (9, "nauka i rozwój techniki"), OSWIATA_WYCHOWANIE (10, "oświata i wychowanie"), KULTURA_SZTUKA (11, "kultura i sztuka"), 
	MEDYCYNA (12, "medycyna"), TURYSTYKA (13, "turystyka"), MALE_USLUGI (14, "małe branże usługowe"), 
	FINANSE (15, "usługi finansowe"), INNE (16, "inne");

	private final int idIndustry;
	private final String name;
	
	private Industry(int idIndustry, String name) {
		this.idIndustry= idIndustry;
		this.name= name;
	}
	
	public static String findTheIndustryNameAfterId(long idIndustry){

		switch ((int)idIndustry) {
        case 1:  return PRZEMYSL.name;
        case 2:  return BUDOWNICTWO.name;
        case 3:  return ROLNICTWO.name;
        case 4:  return LESNICTWO.name;
        case 5:  return TRANSPORT.name;
        case 6:  return LACZNOSC.name;
        case 7:  return HANDEL.name;
        case 8:  return NIERUCHOMOSCI.name;
        case 9:  return NAUKA_ROZWOJ.name;
        case 10:  return OSWIATA_WYCHOWANIE.name;
        case 11:  return KULTURA_SZTUKA.name;
        case 12:  return MEDYCYNA.name;
        case 13:  return TURYSTYKA.name;
        case 14:  return MALE_USLUGI.name;
        case 15:  return FINANSE.name;
        case 16:  return INNE.name;       
		}
	return null;
	}	

	public static long findTheIndustryNameAfterName(String name){

		switch (name) {
        case "przemysł":  return PRZEMYSL.idIndustry;
        case "budownictwo":  return BUDOWNICTWO.idIndustry;
        case "rolnictwo":  return ROLNICTWO.idIndustry;
        case "leśnictwo":  return LESNICTWO.idIndustry;
        case "transport":  return TRANSPORT.idIndustry;
        case "łączność":  return LACZNOSC.idIndustry;
        case "handel":  return HANDEL.idIndustry;
        case "nieruchomości":  return NIERUCHOMOSCI.idIndustry;
        case "nauka i rozwój techniki":  return NAUKA_ROZWOJ.idIndustry;
        case "oświata i wychowanie":  return OSWIATA_WYCHOWANIE.idIndustry;
        case "kultura i sztuka":  return KULTURA_SZTUKA.idIndustry;
        case "medycyna":  return MEDYCYNA.idIndustry;
        case "turystyka":  return TURYSTYKA.idIndustry;
        case "małe branże usługowe":  return MALE_USLUGI.idIndustry;
        case "usługi finansowe":  return FINANSE.idIndustry;
        case "inne":  return INNE.idIndustry;     
		}
	return 0;
	}		
	
	public int getIdIndustry() {
		return idIndustry;
	}
		
}
