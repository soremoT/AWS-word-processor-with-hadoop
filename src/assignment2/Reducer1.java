package assignment2;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import Custom.Frequency;

public class Reducer1 extends Reducer<Text,Text,Text,Text> {    
	
	 private MultipleOutputs mos;
	 protected void setup(Context context) throws IOException, InterruptedException {
			super.setup(context);
			mos = new MultipleOutputs<Text,Text>(context);
		}
	 
	 public void cleanup(Context context ) throws IOException, InterruptedException {mos.close();}
	 
	 
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,  InterruptedException {
		Map<String, Frequency> SlotX = new HashMap<String, Frequency>();
		Map<String, Frequency> SlotY = new HashMap<String, Frequency>();
		
		
		Configuration conf = context.getConfiguration();
		String param1 = conf.get("test1");
		String param2 = conf.get("test2");
		
		for (Text value : values) {
			
			String[] slots = value.toString().split("\\s+");
			
			if(slots[1].equals("X")) {
				if(!SlotX.containsKey(slots[0]))
					SlotX.put(slots[0], new Frequency(slots[0]));
				else SlotX.get(slots[0]).inc();
				
				if(!SlotY.containsKey(slots[2]))
					SlotY.put(slots[2], new Frequency(slots[2]));
				else SlotY.get(slots[2]).inc();
			}
			
			else {
				if(!SlotX.containsKey(slots[2]))
					SlotX.put(slots[2], new Frequency(slots[2]));
				else SlotX.get(slots[2]).inc();
			
				if(!SlotY.containsKey(slots[0]))
					SlotY.put(slots[0], new Frequency(slots[0]));
				else SlotY.get(slots[0]).inc();
			}
			
			}
		
		Text FinalKey=new Text(); 
		Text FinalValue=new Text();
    	StringBuilder localValue =  new StringBuilder();
    	StringBuilder localKey =  new StringBuilder();
    	
		Collection<Frequency> X =SlotX.values();
		Collection<Frequency> Y =SlotY.values();
		
		//adding separator so we can discern between them
			localKey.append(key.toString()+ "#" );
			
			int SumSlotXForPath=0;
			int SumSlotYForPath=0;
			for(Frequency x:X) {
				localValue.append(x.getNoun()+ "/" +x.getCount()+ " " );
				SumSlotXForPath=SumSlotXForPath+x.getNum();
				
		    	StringBuilder tempKey =  new StringBuilder();
		    	tempKey.append(x.getNoun() +"#X#" + x.getCount() + "#" +key);
		    	
		    	mos.write("out2", new Text(tempKey.toString()),null, param2+"/out2");
			}
			
			localValue.append("#");
			
			for(Frequency y:Y) {
				localValue.append(y.getNoun()+ "/" + y.getCount()+ " " );
				SumSlotYForPath=SumSlotYForPath+y.getNum();
				
				StringBuilder tempKey =  new StringBuilder();
		    	tempKey.append(y.getNoun() +"#Y#" + y.getCount() + "#" +key);
		    	
		    	mos.write("out2", new Text(tempKey.toString()),null, param2+"/out2");
			}
			
			localValue.append("#"+String.valueOf(SumSlotXForPath)+" "+ String.valueOf(SumSlotYForPath));
			
			FinalKey.set(localKey.toString());
			FinalValue.set(localValue.toString());
		
		//	context.write(FinalKey,FinalValue);
			
			mos.write("out1", FinalKey,FinalValue, param1+"/out1");
	
}}