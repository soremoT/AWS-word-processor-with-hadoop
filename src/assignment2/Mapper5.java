package assignment2;

import java.io.IOException;



import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper5 extends Mapper<LongWritable, Text, Text, Text> {


	    @Override
	    public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {


	    	String word = value.toString();
	    	 String[] parts = word.split("#");
	    	 if(parts.length==5) {
	    		 
	    		 for(int i=0;i<parts.length;i++) 
	    			 parts[i].trim();
	    		 
	    		 StringBuilder localKey =  new StringBuilder();
	    		 StringBuilder localValue =  new StringBuilder();
	    		 localKey.append(parts[0] + " " + parts[1]);
	    		 //value will be word slot count and total slot count
	    		 localValue.append(parts[2] + "#" + parts[3]);
	    		 
	    		 context.write(new Text(localKey.toString()), new Text(localValue.toString()));
	    		 
	    	 }
	    	 
	    	 if(parts.length==4) {
	    		 

	    		 String path=parts[0].trim();
	    		 
	    		 //for each word in X or Y out: word, Slot, and the frequency of that word ,total frequency of all words
	    			 String [] WordsX=parts[1].split("\\s+");
	    			 String [] WordsY=parts[2].split("\\s+");
	    			 String [] totalSize=parts[3].split("\\s+");
	    			 //wordX has all the words of x and their frequency
	    			 for(int i=0;i<WordsX.length;i++)
	    			 {//noun has the word and its frequency
	    				String Noun[] = WordsX[i].split("/");
	    				StringBuilder localKey =  new StringBuilder();
	    				StringBuilder localValue =  new StringBuilder();
	    				 if(Noun.length>1) {
		    				localKey.append(Noun[0] +" X");
		    				localValue.append(path + "#" + Noun[1] + "#" + totalSize[0]  );
	    				
	    				 context.write(new Text(localKey.toString()), new Text(localValue.toString()));
	    			 }}
	    				 for(int i=0;i<WordsY.length;i++)	 
	    				 {
	    					String Noun[] = WordsY[i].split("/");
		    				StringBuilder localKey =  new StringBuilder();
		    				StringBuilder localValue =  new StringBuilder();
		    				 if(Noun.length>1) {
			    				localKey.append(Noun[0] +" Y");
			    				localValue.append(path + "#" + Noun[1] + "#" + totalSize[1]  );
			    				
		    				context.write(new Text(localKey.toString()), new Text(localValue.toString()));
	    				 }}
	    		 }
	    }
}