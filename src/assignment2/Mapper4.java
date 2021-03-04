package assignment2;

import java.io.IOException;



import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper4 extends Mapper<LongWritable, Text, Text, Text> {


	    @Override
	    public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {


	    	String word = value.toString();
	    	 String[] parts = word.split("\\s+");
	    	 if(parts.length==2) {
	    		 StringBuilder localKey =  new StringBuilder();
	    		 StringBuilder localValue =  new StringBuilder();
	    		 localKey.append(parts[0]);
	    		 localValue.append(parts[1]);
	    		 context.write(new Text(localKey.toString()), new Text(localValue.toString()));
	    		 
	    	 }
	    	 if(parts.length==3) {
	    		 StringBuilder localKey =  new StringBuilder();
	    		 StringBuilder localValue =  new StringBuilder();
	    		 localKey.append(parts[1]);
	    		 localValue.append(parts[0]+" "+parts[2]);
	    		 context.write(new Text(localKey.toString()), new Text(localValue.toString()));
	    	 }
	        	
	    }


}

