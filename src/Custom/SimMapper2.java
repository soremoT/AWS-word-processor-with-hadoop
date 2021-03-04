package Custom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SimMapper2 extends Mapper<LongWritable, Text, Text, Text> {
	

	    public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {
	    	String[] parts=value.toString().split("\\s+");
	    	if(parts[0].equals("X"));
	    		context.write(new Text("X"), value);
		    if(parts[0].equals("Y"));
	    		context.write(new Text("Y"), value);
	    }

	    
}





