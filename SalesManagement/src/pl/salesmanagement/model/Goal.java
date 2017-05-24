package pl.salesmanagement.model;

public enum Goal {
	WSTEPNA_ROZMOWA (1, "wstępna rozmowa"), PREZENTACJA_HANDLOWA (2, "prezentacja handlowa"), PODPISANIE_UMOWY(3, "podpisanie umowy"), 
	NIEOKRESLONE (4, "nieokreślone");

	private final long idGoal;
	private final String name;
	
	private Goal(long idGoal, String name) {
		this.idGoal= idGoal;
		this.name= name;
	}
	
	public static String findTheGoalNameAfterId(long idGoal){
		
		switch ((int)idGoal) {
        case 1:  return WSTEPNA_ROZMOWA.name;
        case 2:  return PREZENTACJA_HANDLOWA.name;
        case 3:  return PODPISANIE_UMOWY.name;
        case 4:  return NIEOKRESLONE.name;  
		}
	return null;
	}

	public static long findTheGoalNameAfterName(String name){
		
		switch (name) {
        case "wstępna rozmowa":  return WSTEPNA_ROZMOWA.idGoal;
        case "prezentacja handlowa":  return PREZENTACJA_HANDLOWA.idGoal;
        case "podpisanie umowy":  return PODPISANIE_UMOWY.idGoal;
        case "nieokreślone":  return NIEOKRESLONE.idGoal;
		}
	return 0;
	}
	
	public long getIdGoal() {
		return idGoal;
	}
	
}

