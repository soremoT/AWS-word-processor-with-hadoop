package Custom;

public class Triple {
	private String path1;
	private String path2;
	private String value;
	
	public Triple(String path1,String path2,String value) {
		this.path1=path1;
		this.path2=path2;
		this.value=value;
		
	}
	/**
	 * returns first path in the pair*/
	public String getPath1() {
		return path1;
	}
	public String getPath2() {
		return path2;
	}
	public String getValue() {
		return value;
	}
}
