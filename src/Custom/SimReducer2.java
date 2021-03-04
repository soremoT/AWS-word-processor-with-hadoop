package Custom;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;



public class SimReducer2 extends Reducer<Text,Text,Text,Text> {    
	List<PathPair> positive;
	List<PathPair> negative;
	    @Override
	    
	    protected void setup(Context context) throws IOException, InterruptedException {
		    TestSet set=new TestSet();
	    	positive=set.getPos();
	    	negative=set.getNeg();
	    
	    }
	    
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,  InterruptedException {

		 List<String> list=new ArrayList<String>();
		 for(Text value:values)
			 list.add(value.toString());
		 
		 List<PathPath> testSetPair=new ArrayList<PathPath>();
		 for(PathPair pos:positive) {
			 PathPath obj=new PathPath();
			 boolean flag=true;
			 for(int i=0;i<list.size();i++) {
				 String [] parts=list.get(i).split("#");
				 String[] s=parts[0].split("\\s+");
				 String p=getString(s);
				 
				 if(flag&&pos.getPath1().trim().equals(p)) {
					 obj.setPath1(p);//TODO add set for all parts
					 obj.setMiAll1(parts[2]);
					 obj.setPath1List(parts[1]);
					 i=0;
					
					 flag=false;
				 }
				 if(!flag&&pos.getPath2().equals(p)) {
					 obj.setPath2(p);
					 obj.setMiAll2(parts[2]);
					 obj.setPath2List(parts[1]);
					 break;
				 }
				 
				 
			 }
			 if(obj.isFull())
					 testSetPair.add(obj);
		 }
		 
		 
		 for(PathPair pos:negative) {
			 PathPath obj=new PathPath();
			 boolean flag=true;
			 for(int i=0;i<list.size();i++) {
				 String [] parts=list.get(i).split("#");
				 String[] s=parts[0].split("\\s+");
				 String p=getString(s);
				 
				 if(flag&&pos.getPath1().equals(p)) {
					 obj.setPath1(p);//TODO add set for all parts
					 obj.setMiAll1(parts[2]);
					 obj.setPath1List(parts[1]);
					 i=0;
					
					 flag=false;
				 }
				 if(!flag&&pos.getPath2().equals(p)) {
					 obj.setPath2(p);
					 obj.setMiAll2(parts[2]);
					 obj.setPath2List(parts[1]);
					 break;
				 }
				 
				 
			 }
			 if(obj.isFull())
					 testSetPair.add(obj);
		 }
		 
		 Double value=0.0;
		 Double intersection=0.0;
		 for(PathPath pathPath:testSetPair) {
			 
			 intersection=pathPath.getIntersection();
			 Double mehane=pathPath.getMehane();
			 if(mehane!=0) 
				 value=intersection/mehane;
			 StringBuilder localKey=new StringBuilder();
			 localKey.append(pathPath.getPath1()+","+pathPath.getPath2()+"#"+String.valueOf(value));
			 context.write(new Text(localKey.toString()), null);
		 }
		 
	}
	public static String getString(String [] str) {
		 StringBuilder a= new StringBuilder();
		 for(int i=1;i<str.length;i++)
			 a.append(str[i]+" ");	 
		 return a.toString().trim();
	}
}





