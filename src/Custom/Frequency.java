package Custom;

public class Frequency {

	private String Noun;
	private int count;
	public Frequency(String name){
		this.Noun=name;
		count=1;
	}
	
	public String getNoun() {
		return Noun;
	}
	public String getCount() {
		return String.valueOf(count);
	}
	public void inc() {
		count++;
	}
	
	public int getNum() {
		return count;
	}
	
	
	
}
