package pl.salesmanagement.model;

public enum Effect {
	SPOTKANIE_NIEODBYTE (1, "spotkanie nie obyło się"), SPOTKANIE_BEZ_EFEKTU (2, "spotkanie bez oczekiwanego efektu"), 
	KOLEJNE_SPOTKANIE(3, "umówiony na kolejne spotkanie"), PODPISANA_UMOWA (4, "podpisana umowa");
	
	private final int idEffect;	
	private final String name;
	
	private Effect(int idEffect, String name) {
		this.idEffect= idEffect;
		this.name= name;
	}
	
	public int getIdEffect() {
		return idEffect;
	}
	
	public String getName() {
		return name;
	}

	public static String findTheEffectNameAfterId(long idEffect){
		
		switch ((int)idEffect) {
        case 1:  return SPOTKANIE_NIEODBYTE.name;
        case 2:  return SPOTKANIE_BEZ_EFEKTU.name;
        case 3:  return KOLEJNE_SPOTKANIE.name;
        case 4:  return PODPISANA_UMOWA.name;  
		}
	return null;
	}

	public static long findTheEffectIdAfterName(String name){
		
		switch (name) {
        case "spotkanie nie obyło się":  return SPOTKANIE_NIEODBYTE.idEffect;
        case "spotkanie bez oczekiwanego efektu":  return SPOTKANIE_BEZ_EFEKTU.idEffect;
        case "umówiony na kolejne spotkanie":  return KOLEJNE_SPOTKANIE.idEffect;
        case "podpisana umowa":  return PODPISANA_UMOWA.idEffect;
		}
	return 0;
	}
	
}
