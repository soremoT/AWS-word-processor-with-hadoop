package assignment2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public  class TextPartition extends Partitioner<Text, Text> {
    @Override
    public int getPartition(Text key, Text value, int numPartitions) {
    	String partition=key.toString();
    	return  Math.abs(partition.hashCode()%numPartitions);
    }    
  }