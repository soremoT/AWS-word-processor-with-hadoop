package Custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


	public class SimReducer3 extends Reducer<Text,Text,Text,Text> {    
		
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,  InterruptedException {
			
			Double ans=1.0;
			
			for(Text value: values)
				ans=ans*Double.valueOf(value.toString());
			
			ans=Math.sqrt(ans);	

			context.write(key, new Text(String.valueOf(ans)));
			}
}
