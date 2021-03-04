package Custom;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public  class NumPartitioner extends Partitioner<Text, IntWritable> {
	    @Override
	    public int getPartition(Text key, IntWritable value, int numPartitions) {
	    	return  0;
	  
	    }} 

