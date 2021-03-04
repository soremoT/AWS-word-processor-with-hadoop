package Custom;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


	public  class SimPartitioner extends Partitioner<Text, Text> {
	    @Override
	    public int getPartition(Text key, Text value, int numPartitions) {
	    	
	    	if(numPartitions>0){
	    	if(key.toString().equals("X"))
	      	return 0;
	    	if(key.toString().equals("Y"))
	    		return 1;}
	    	return 0;
	    }    
	  }


