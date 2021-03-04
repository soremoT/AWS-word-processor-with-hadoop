package assignment2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper3 extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	
	    @Override
	    public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {
	    	
	    	String word = value.toString();
	        String[] parts = word.split("\\s+");

	        StringBuilder localValue1 =  new StringBuilder();
	        StringBuilder localValue2 =  new StringBuilder();
	        
	        
	        //we divide each ngram to separate corpus to check each r of both corpus
	        if (parts.length >= 3){
		        context.write(new Text(parts[1]), new IntWritable(Integer.valueOf(parts[2])));

	    }
}}


