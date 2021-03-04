package Custom;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class SimMapper1 extends Mapper<LongWritable, Text, Text, Text> {
	
	
	List<PathPair> positive;
	List<PathPair> negative;
	    @Override
	    
	    protected void setup(Context context) throws IOException, InterruptedException {
		    TestSet set=new TestSet();
	    	positive=set.getPos();
	    	negative=set.getNeg();
	    }
	    @Override
 	 public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {
	    		    
	 	    	String [] parts=value.toString().split("#");
	 	    	if(parts.length>=3) {
	 	    		parts[0]=parts[0].trim();

	 	    		boolean flag=true;
	 	    		for(PathPair pos : positive) {
	 	    			if(parts[0].equals(pos.getPath1().trim())||parts[0].equals(pos.getPath2().trim())) {
	 	    				StringBuilder localKey=new StringBuilder();
	 	    				StringBuilder localValue=new StringBuilder();
	 	    				String  [] word=parts[1].split("\\s+");
	 	    				
	 	    				localKey.append(word[1]+ " " +parts[0]);//word[1] is slot parts[0] is path
	 	    				localValue.append(word[0]+ " " +parts[2]);//word[0] is word parts[2] is mi
	 	    				flag=false;
	 	    			context.write(new Text(localKey.toString()),new Text(localValue.toString() ));
	 	    			break;
	 	    			
	 	    			}
	 	    		}
	 	    		
	 	    		
	 	    		for(int i=0;i<negative.size()&&flag;i++) {
	 	    			PathPair neg=negative.get(i);
	 	    			if(parts[0].equals(neg.getPath1().trim())||parts[0].equals(neg.getPath2().trim())) {
	 	    				StringBuilder localKey=new StringBuilder();
	 	    				String  [] word=parts[1].split("\\s+");
	 	    				StringBuilder localValue=new StringBuilder();
	 	    				localKey.append(word[1]+" "+parts[0]);
	 	    				localValue.append(word[0]+ " " +parts[2]);
	 	    			context.write(new Text(localKey.toString()),new Text(localValue.toString()));
	 	    			break;
	 	    			
	 	    			}
	 	    			
	 	    		}
	 	    	}
	 	    }
	    
	    
	    }