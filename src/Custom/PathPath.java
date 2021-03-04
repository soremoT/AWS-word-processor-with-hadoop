package Custom;

import java.util.ArrayList;
import java.util.List;

public class PathPath {
	String path1=null;
	String path2=null;
	String slot;
	List<PathPair> list1=new ArrayList<PathPair>();
	List<PathPair> list2=new ArrayList<PathPair>();; 
	
	String miAll1;
	String miAll2;
	
	public PathPath() {
		path1=null;
		path2=null;
	}

	
	public void setPath1(String path) {
		this.path1=path;
	}
	public void setPath2(String path) {
		this.path2=path;
	}
	
	public Double getMehane() {
		return Double.valueOf(miAll1) + Double.valueOf(miAll2);
	}
	
	
	public Double getIntersection() {
		Double sum=0.0;
		if(list1.size()>=list2.size()) {
			for(PathPair p2:list2) {
				for(int i=0;i<list1.size();i++) 
					if(list1.get(i).getPath1().equals(p2.getPath1()))
						sum=sum+Double.valueOf(list1.get(i).getPath2());
			}
		}
		else 
			for(PathPair p1:list1) 
				for(int i=0;i<list2.size();i++) 
					if(list2.get(i).getPath1().equals(p1.getPath1()))
						sum=sum+Double.valueOf(list2.get(i).getPath2());
			
		return sum;
		}
	
	
	public void setPath1List(String words) {
		String [] pairs = words.split("/");
		for(int i=0;i<pairs.length;i=i+2)
			list1.add(new PathPair(pairs[i],pairs[i+1]));
	}
	public void setPath2List(String words) {
		String [] pairs = words.split("/");
		for(int i=0;i<pairs.length;i=i+2)
			list2.add(new PathPair(pairs[i],pairs[i+1]));
	}
	public void setMiAll1(String mi) {
		miAll1=mi;
	}
	public void setMiAll2(String mi) {
		miAll2=mi;
	}
	public void setSlot(String s) {
		slot =s;
	}
	


	public String getPath1() {
		return path1;
	}
	public String getPath2() {
		return path2;
	}
	
	public String getMiAll1() {
		return miAll1;
		}
	
	public String getMiAll2() {
		return miAll2;
		}
	/**
	 * return true if both paths exist*/
	public boolean isFull(){ 
		return (path1!=null&&path2!=null);
	}



}
