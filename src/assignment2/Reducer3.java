package assignment2;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class Reducer3 extends Reducer<Text,Text,Text,Text> {    
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,  InterruptedException {
		double N1=0 , N2=0 , T10=0 , T01=0;
		 double Ns = 0;
	    for (Text value : values) {
	    	String word = value.toString();
	        String[] parts = word.split("\\s+");
	        
	        if(parts[parts.length-1].equals("0")) {
	        	if(Ns==0)
	        		Ns=Double.valueOf(parts[parts.length-2]);
	        	N1++;
	        	T01=T01+Integer.valueOf(parts[4]);
	        }
	        else { 
	        	if(Ns==0)
	        		Ns=Double.valueOf(parts[parts.length-2]);
	        	N2++;;
	        	T10=T10+Integer.valueOf(parts[3]);
	        }
	    }
	    
	   
	    
	    double T=(T01+T10);
	    double N=Ns*(N1+N2);
	    double propability=T/N;
	    Text out=new Text();
	    out.set(String.valueOf(propability));
	    
	    context.write(key, out); 
	  }
}