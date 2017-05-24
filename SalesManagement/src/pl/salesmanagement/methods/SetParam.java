package pl.salesmanagement.methods;

public enum SetParam{
	ON ("checked", "on"), NULL ("", null);
		
	private final String value;
	private final String key;
			
	private SetParam(String value, String key) {
		this.value=value;
		this.key=key;
	}
		
	public static String researchValue(String key){
		try{
			if(key.equals(ON.key)){
				return ON.value;
			}
		}
		catch(NullPointerException e){}
		return NULL.value;
	}
	
}