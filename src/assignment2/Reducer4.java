package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class Reducer4 extends Reducer<Text,Text,Text,Text> {    
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,  InterruptedException {
	    
       List<String[]> list=new ArrayList<String[]>();
    	String sumOfAll="";
    	for(Text text: values) {
    		String[] parts=text.toString().split("\\s+");
    		if(parts.length>1)
    			list.add(parts);
    		else sumOfAll=new String(parts[0]);
    		
    	}
    	for(String str[]:list) {
    		 StringBuilder localKey =  new StringBuilder();
    		 StringBuilder localValue =  new StringBuilder();
    		 localKey.append(str[0]+ "#" +key.toString() + "#" +str[1]+"#"+ sumOfAll+ "#"+"end" );
    		
    		context.write(new Text(localKey.toString()) , null); 
    	}
	  }
	
	
	
	
	
}