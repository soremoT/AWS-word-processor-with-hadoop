package Custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SimReducer1 extends Reducer<Text,Text,Text,Text> {    
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,  InterruptedException {
		double sum=0;
		List<String []> list=new ArrayList<String []>();
		for(Text value: values) {
				String[] word=value.toString().split("\\s+");
				list.add(word);
				sum=sum+Double.valueOf(word[1]);
			}
		StringBuilder localValue=new StringBuilder();
		localValue.append("#");
		for(String str[]:list) {
				localValue.append(str[0] + "/" + str[1]+"/");	
		}
		
		localValue.append("#"+String.valueOf(sum));
	context.write(key,new Text(localValue.toString()));
	}}