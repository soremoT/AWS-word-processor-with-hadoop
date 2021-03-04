package Custom;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


	public class SimMapper3 extends Mapper<LongWritable, Text, Text, Text> {
		
		

		    

		    @Override
	public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {
		    		 
		    	String[] parts=value.toString().split("#");
 				context.write(new Text(parts[0]),new Text(parts[1]));
 				
		    	}
		    
	}
