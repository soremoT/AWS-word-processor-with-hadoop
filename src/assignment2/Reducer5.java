package assignment2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;


public class Reducer5 extends Reducer<Text,Text,Text,Text> {    
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,  InterruptedException {
	    
       List<String[]> list=new ArrayList<String[]>();
       //size[0] is fequency of specific word size[1] is frequency of all words for path slot
    	String [] Size=null;
    	for(Text text: values) {
    		String[] parts=text.toString().split("#");
    		if(parts.length==2)
    			Size=parts;
    		else list.add(parts);
    	}
    	
    	
    	double third=Integer.valueOf(Size[0]);
    	double fourth=Integer.valueOf(Size[1]);
    	//the equation is correct even if the names are weird
    	for(String str[]:list) {
    		double first=Integer.valueOf(str[1]);
    		double second=Integer.valueOf(str[2]);
    		double firstHalf=first*fourth;
    		double secondHalf=second*third;
    		double divide=firstHalf/secondHalf;
    		double mi=Math.log(divide);
    		//depends what to do with negative mi
    		if(mi<0)
    		mi=0;
			
    		StringBuilder localKey =  new StringBuilder();
			StringBuilder localValue =  new StringBuilder();
			localKey.append(str[0] );
			localValue.append("#"+ key.toString() + "#" + String.valueOf(mi));
    		context.write(new Text(localKey.toString()) , new Text(localValue.toString())); 
    	}
	  }
	
}
	