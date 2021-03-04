package assignment2;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper2 extends Mapper<LongWritable, Text, Text, IntWritable> {

	    
	    @Override
	    public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {
	       
	      	String word = value.toString();
	        String[] parts = word.split("#");
	        
	        StringBuilder localkey =  new StringBuilder();
	        if(parts[1].equals("X"))
	        	localkey.append(parts[0]+" X");
	        else localkey.append(parts[0]+" Y");
	        
	        	
	        	
	    context.write(new Text(localkey.toString()),new IntWritable(Integer.valueOf(parts[2])));
	        	
	    }

	    

}


