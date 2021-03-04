package assignment2;



import java.io.File;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import Custom.NumPartitioner;
import Custom.SimMapper2;
import Custom.SimMapper3;
import Custom.SimMapper1;
import Custom.SimPartitioner;
import Custom.SimReducer2;
import Custom.SimReducer3;
import Custom.SimReducer1;

  
public class Test { 
 

   


 public static void main(String[] args) throws Exception {
	 
 	File directory = new File(args[2]);
 	File directory2 = new File(args[3]);
 	File directory3 = new File(args[4]);
 	File directory4 = new File(args[5]);
 	File directory5 = new File(args[6]);
 	File directory6 = new File(args[7]);
 	File directory7 = new File(args[8]);
 	File directory8 = new File(args[9]);
 	//make sure directory exists
 	if(!directory.exists()){

        System.out.println("Directory does not exist.");
      

     }else{

        try{
        	
            delete(directory);
            delete(directory2);
            delete(directory3);
            delete(directory4);
            delete(directory5);
            delete(directory6);
        	delete(directory7);
        	delete(directory8);
        	
        }catch(IOException e){
            e.printStackTrace();
           
        }
     }
	 System.out.println("hello hadoop");


   Configuration conf2 = new Configuration();
   
   conf2.set("test1", "out1");
   conf2.set("test2", "out2");
   
   Job job2 = new Job(conf2, "Test2");
   job2.setJarByClass(Test.class);
   job2.setMapperClass(Mapper1.class);
   job2.setPartitionerClass(TextPartition.class);
   job2.setReducerClass(Reducer1.class);
   job2.setMapOutputKeyClass(Text.class);
   job2.setMapOutputValueClass(Text.class);
   job2.setOutputKeyClass(Text.class);
   job2.setOutputValueClass(Text.class);
   
  // job2.setInputFormatClass(SequenceFileInputFormat.class);
   System.out.println(args[1]);
   int i=1;
 //  for(i=1;i<=99;i++)
	//   MultipleInputs.addInputPath(job2, new Path(args[i]), SequenceFileInputFormat.class, Mapper1.class);
   
 
   FileInputFormat.addInputPath(job2, new Path(args[i]));
   FileOutputFormat.setOutputPath(job2, new Path(args[i+1]));
   
   
   MultipleOutputs.addNamedOutput(job2, "out1",   TextOutputFormat.class,Text.class, Text.class);
   MultipleOutputs.addNamedOutput(job2, "out2",   TextOutputFormat.class,Text.class, Text.class);
   
   job2.waitForCompletion(true);
  
   
 
    
    Configuration conf = new Configuration();
    Job job = new Job(conf, "Test");
    job.setJarByClass(Test.class);
    job.setMapperClass(Mapper2.class);
    job.setPartitionerClass(NumPartitioner.class);
    job.setReducerClass(Reducer2.class);
    job.setCombinerClass(Combiner.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    
    FileInputFormat.addInputPath(job, new Path(args[i+1]+"/out2"));
    FileOutputFormat.setOutputPath(job, new Path(args[i+2]));
    
    job.waitForCompletion(true);
     
   
  
   
   Configuration conf3 = new Configuration();
   Job job3 = new Job(conf3, "Test3");
   job3.setJarByClass(Test.class);
   job3.setMapperClass(Mapper3.class);
   job3.setPartitionerClass(NumPartitioner.class);
   job3.setCombinerClass(Reducer2.class);
   job3.setReducerClass(Reducer2.class);
   job3.setMapOutputKeyClass(Text.class);
   job3.setMapOutputValueClass(IntWritable.class);
   job3.setOutputKeyClass(Text.class);
   job3.setOutputValueClass(IntWritable.class);
   
   
   FileInputFormat.addInputPath(job3, new Path(args[i+2]));

   FileOutputFormat.setOutputPath(job3, new Path(args[i+3]));
   job3.waitForCompletion(true);
   
   

	 
	 
   Configuration conf4 = new Configuration();
   
   
   Job job4 = new Job(conf4, "Test4");
   job4.setJarByClass(Test.class);
   job4.setPartitionerClass(PartitionerClass.class);
   job4.setReducerClass(Reducer4.class);
   job4.setMapOutputKeyClass(Text.class);
   job4.setMapOutputValueClass(Text.class);
   job4.setOutputKeyClass(Text.class);
   job4.setOutputValueClass(Text.class);
   
   MultipleInputs.addInputPath(job4, new Path(args[i+2]), TextInputFormat.class, Mapper4.class);
   MultipleInputs.addInputPath(job4, new Path(args[i+3]), TextInputFormat.class, Mapper4.class);
   
   FileOutputFormat.setOutputPath(job4, new Path(args[i+4]));
   
   job4.waitForCompletion(true);
   
	 
	
	 
	 

   Configuration conf5 = new Configuration();
   Job job5 = new Job(conf5, "Test5");
   job5.setJarByClass(Test.class);

   job5.setPartitionerClass(TextPartition.class);
   job5.setReducerClass(Reducer5.class);
   job5.setMapOutputKeyClass(Text.class);
   job5.setMapOutputValueClass(Text.class);
   job5.setOutputKeyClass(Text.class);
   job5.setOutputValueClass(Text.class);
   
  

   
   MultipleInputs.addInputPath(job5, new Path(args[i+1]+"/out1"), TextInputFormat.class, Mapper5.class);
   MultipleInputs.addInputPath(job5, new Path(args[i+4]), TextInputFormat.class, Mapper5.class);
   
   FileOutputFormat.setOutputPath(job5, new Path(args[i+5]));
   job5.waitForCompletion(true);



   Configuration conf7 = new Configuration();
	   Job job7 = new Job(conf7, "Test6");
	   job7.setJarByClass(Test.class);
	   job7.setMapperClass(SimMapper1.class);
	   job7.setPartitionerClass(TextPartition.class);
	  // job7.setCombinerClass(SimReducer1.class);
	   job7.setReducerClass(SimReducer1.class);
	   job7.setMapOutputKeyClass(Text.class);
	   job7.setMapOutputValueClass(Text.class);
	   job7.setOutputKeyClass(Text.class);
	   job7.setOutputValueClass(Text.class);
	   
	   FileInputFormat.addInputPath(job7, new Path(args[i+5]));
	   FileOutputFormat.setOutputPath(job7, new Path(args[i+6]));
	   job7.waitForCompletion(true);
  	    

 
	   Configuration conf6 = new Configuration();
	   Job job6 = new Job(conf6, "Test7");
	   job6.setJarByClass(Test.class);
	   job6.setMapperClass(SimMapper2.class);
	   job6.setPartitionerClass(SimPartitioner.class);
	   job6.setReducerClass(SimReducer2.class);
	   job6.setMapOutputKeyClass(Text.class);
	   job6.setMapOutputValueClass(Text.class);
	   job6.setOutputKeyClass(Text.class);
	   job6.setOutputValueClass(Text.class);
	   
	   FileInputFormat.addInputPath(job6, new Path(args[i+6]));
	   
	   FileOutputFormat.setOutputPath(job6, new Path(args[i+7]));
	   
	   job6.waitForCompletion(true);

	 
	 
	  
	 
   Configuration conf8 = new Configuration();

	   Job job8 = new Job(conf8, "Test5");
	   job8.setJarByClass(Test.class);
	   job8.setMapperClass(SimMapper3.class);
	   job8.setPartitionerClass(PartitionerClass.class);
	   job8.setReducerClass(SimReducer3.class);
	   job8.setMapOutputKeyClass(Text.class);
	   job8.setMapOutputValueClass(Text.class);
	   job8.setOutputKeyClass(Text.class);
	   job8.setOutputValueClass(Text.class);
	   
	   FileInputFormat.addInputPath(job8, new Path(args[i+7]));
	   
	   FileOutputFormat.setOutputPath(job8, new Path(args[i+8]));
	   
	   job8.waitForCompletion(true); 
	   
	   
	   
	   
  }
 public static void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	    			
	    		   file.delete();
	    		   System.out.println("Directory is deleted : " 
	                                                 + file.getAbsolutePath());
	    			
	    		}else{
	    			
	    		   //list all the directory contents
	        	   String files[] = file.list();
	     
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	        		 
	        	      //recursive delete
	        	     delete(fileDelete);
	        	   }
	        		
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	     System.out.println("Directory is deleted : " 
	                                                  + file.getAbsolutePath());
	        	   }
	    		}
	    		
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    		System.out.println("File is deleted : " + file.getAbsolutePath());
	    	}
	    }
}