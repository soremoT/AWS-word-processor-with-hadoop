package assignment2;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Mapper1 extends Mapper<LongWritable, Text, Text, Text> {
	private  Text FinalValue = new Text();
	private  Text FinalValue2 = new Text();
	
	
	    @Override
	    public void map(LongWritable key, Text value, Context context) throws IOException,  InterruptedException {
	    	boolean verb=false;
	    	 boolean flag=true;
	    	int numofNoun=0;
	    	String word = value.toString();
	        String[] parts = word.split("\\s+");
	        StringBuilder localValue =  new StringBuilder();

	       // localValue.append(parts[0]+" ");
	        for(int i=1;i<parts.length&&flag;i++) {
	        	  String[] parts2 = parts[i].split("/"); 
	        	  //check parts2 is not a year,check head
	        	  
	        	  if(parts2.length>2) {
	        		  
	        		if(checkLegal(parts2[1])) {
	        			flag=checkLowercase(parts2[0]);
	        		  localValue.append(parts[i]+" ");
	        		
	        		}
	        		  if(checkNoun(parts2[1]))
	        			  numofNoun++;
		        	  if(checkVerb(parts2[1]))
		        		  verb=true;
	        	  }else   break;
	        }
	        

	       
	        
	        if(verb&&numofNoun==2&&flag) {
	        	String [] str = localValue.toString().split("\\s+");
	        	if(str.length==3)
	        		makePath3(str ,context);
	        	if(str.length==4)
	        		makePath4(str ,context);
	        	if(str.length==5)
	        		makePath5(str ,context);
	        	if(str.length>5)
	        		makePathHigh(str ,context);
	        	
	        }
	        
	    	
	    }
	    
	    public static boolean checkPathLegal(String [] str) {
	    	boolean flag=true;
	    	for(int i=0;i<str.length;i++) {
	    			if(i+1==str.length) 
	    				break;
	    			if(str[i]==null||str[i+1]==null)
	    				return false;
	    			if(str[i].equals("X")&&str[i+1].equals("Y")) {
	    				return false;
	    			}
	    	}
	    	return flag;
	    } 
	    public static void makePathHigh(String [] str ,Context context) throws IOException, InterruptedException {
	    	String noun1="";
	    	String noun2="";
	    	String [] path = new String [str.length];
	    	boolean flag2=true;
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		 path[i]=parts[0];
	    		 if(checkNoun(parts[1])) {
		    		if(flag2) {
		    			 noun1 = parts[0];
		    			 path[i]="X";
		    			 flag2=false;
		    		}
		    		else {noun2 = parts[0];
		    				path[i]="Y";
		    		}}
	    		 }
	    	if(checkPathLegal(path)) {
	    		makePathInverse(path,noun1,noun2,context);
	    	}
	    	
	    }
	    /**
	     * stem the word so the value will be easier to read
	     * returns stemmed word**/
	    public static String stemmer(String str) {
	    	try {
	        Stemmer stemmer = new Stemmer();
	        for(int i=0;i<str.length();i++) {
	        	stemmer.add(str.charAt(i));
	        }
	        stemmer.stem();
	    	return stemmer.toString();}catch(Exception e) {
	    		return str;
	    	}
	    }
	    
	    
	    //TODO add function for other possibility
	    public static void makePath5(String [] str ,Context context) throws IOException, InterruptedException {
	    	String noun1="";
	    	String noun2="";
	    	String[] path1=new String[5];
	    	String[] path2=new String[5];
	    	String[] path3=new String[5];
	    	path1[1]="X";
	    	path1[4]="Y";
	    	path2[2]="X";
	    	path2[4]="Y";
	    	path3[1]="X";
	    	path3[3]="Y";
	    	boolean flag=true;
	    	//make each path manually
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		  if(checkVerb(parts[1])) 
	    			  path1[2] = parts[0];
	    		  if(checkNoun(parts[1])) {
	    			  if(flag) {
	    				  noun1 = parts[0];
	    				  flag=false;}
	    			  else noun2 = parts[0];}
	    		  if(checkPrep(parts[1]))
	    			  if(path1[0]==null)
	    				  path1[0] = parts[0];
	    			  else path1[3] = parts[0];
	    	}
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		  if(checkVerb(parts[1])) 
	    			  path2[0] = parts[0];
	    		  if(checkPrep(parts[1]))
	    			  if(path2[1]==null)
	    				  path2[1] = parts[0];
	    			  else path2[3] = parts[0];
	    	}
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		  if(checkVerb(parts[1])) 
	    			  path3[4] = parts[0];
	    		  if(checkPrep(parts[1]))
	    			  if(path3[0]==null)
	    				  path3[0] = parts[0];
	    			  else path3[2] = parts[0];
	    	}
	    	if(checkPathLegal(path1))
	    		makePathInverse(path1,noun1,noun2,context);
	    	if(checkPathLegal(path2))
	    		makePathInverse(path2,noun1,noun2,context);
	    	if(checkPathLegal(path3))
	    		makePathInverse(path3,noun1,noun2,context);
	    }
	    
	   public static void  makePath3(String [] str,Context context) throws IOException, InterruptedException {
	    	String noun1=null;
	    	String noun2=null;
	    	String path[]=new String[3];
	    	path[0]="X";
	    	path[2]="Y";
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		  if(checkVerb(parts[1])) 
	    			  path[1] = parts[0];
	    		  if(checkNoun(parts[1]))
	    			  if(noun1==null)
	    				  noun1 = parts[0];
	    			  else noun2 = parts[0];
	    	}
	    	if(checkPathLegal(path))
	    		makePathInverse(path,noun1,noun2,context);

	    	 
	    	 
	    	// context.write(FinalKey1, FinalValue);
	    	// context.write(FinalKey2, FinalValue);
	    	 
	    }
	    
	    public static void  makePath4(String [] str,Context context) throws IOException, InterruptedException {
	    	String noun1=null;
	    	String noun2=null;
	    	String[] path1=new String[4];
	    	String[] path2=new String[4];
	    	String[] path3=new String[4];
	    	path1[1]="X";
	    	path1[3]="Y";
	    	path2[0]="X";
	    	path2[3]="Y";
	    	path3[0]="X";
	    	path3[2]="Y";
	    	//make each path manually
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		  if(checkVerb(parts[1])) 
	    			  path1[0] = parts[0];
	    		  if(checkNoun(parts[1]))
	    			  if(noun1==null)
	    				  noun1 = parts[0];
	    			  else noun2 = parts[0];
	    		  if(checkPrep(parts[1]))
	    			  path1[2] = parts[0];
	    	}
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		  if(checkVerb(parts[1])) 
	    			  path2[1] = parts[0];
	    		  if(checkPrep(parts[1]))
	    			  path2[2] = parts[0];
	    	}
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		  if(checkVerb(parts[1])) 
	    			  path3[3] = parts[0];
	    		  if(checkPrep(parts[1]))
	    			  path3[1] = parts[0];
	    	}
	    	if(checkPathLegal(path1))
	    		makePathInverse(path1,noun1,noun2,context);
	    	if(checkPathLegal(path2))
	    		makePathInverse(path2,noun1,noun2,context);
	    	if(checkPathLegal(path3))
	    		makePathInverse(path3,noun1,noun2,context);
	    	    	 
	    }
	    	//create inverse path and put paths to context(inverse X and Y)
	    public static void makePathInverse(String [] str,String noun1,String noun2,Context context) throws IOException, InterruptedException 	{
	  
	    	for(int i=0;i<str.length;i++) {
	    		str[i]=stemmer(str[i]);
	    	}
	    	//add path to context
	    	writeToContext(str,noun1+" X ", noun2 ,context);
	    	
	    	for(int i=0;i<str.length;i++) {
	    		if(str[i].equals("X")) {
	    			str[i]="Y";
	    			continue;}
	    		if(str[i].equals("Y"))
	    			str[i]="X";
	    	}
	    	//add inverse path to context
	    	writeToContext(str,noun1 +" Y ", noun2 ,context);
	    		
	    }
	    
	    /**
	     * key is path and value is the Slot fillers*/
	    public static void  writeToContext(String [] str,String noun1,String noun2,Context context) throws IOException, InterruptedException {
	    		Text FinalKey=new Text(); 
				Text FinalValue=new Text();
		    	StringBuilder localValue =  new StringBuilder();
		    	StringBuilder localKey =  new StringBuilder();
		    	for(int i=0;i<str.length;i++)
		    		localKey.append(str[i] + " ");
		    
		    	localValue.append(noun1 + " " + noun2);
		    	 FinalValue.set(localValue.toString());
		    	 FinalKey.set(localKey.toString());
		    	 
		    	 
		    	// System.out.println(localValue.toString());
		    	// System.out.println(localKey.toString());
		    	context.write(FinalKey, FinalValue);
	    }
	    
	    public static boolean checkLegal(String str) {
			 if(str.equals("NN"))
				 return true;
			 if(str.equals("JJ"))
				 return true;
			 if(str.equals("RB"))
				 return true;
			 if(str.equals("TO"))
				 return true;
			 if(str.equals("IN"))
				 return true;
			 if(str.equals("VB"))
				 return true;
	   	return false;
	   }
	   //TODO can delete if make global variable
	   public static boolean checkNoun(String str) {
			 if(str.equals("NN"))
				 return true;
	   	return false;
	   }
		 public static boolean checkVerb(String str) {
			 if(str.equals("VB"))
				 return true;
			 return false;
		 }
		 
		 public static boolean checkPrep(String str) {
			 if(str.equals("TO"))
				 return true;
			 if(str.equals("IN"))
				 return true;
			 if(str.equals("JJ"))
				 return true;
			 if(str.equals("RB"))
				 return true;
			 return false;
		 }
		 public static boolean checkAuxilaryVerb(String str) {
			 if(str.equals("do"))
				 return true;
			 if(str.equals("does"))
				 return true;
			 if(str.equals("did"))
				 return true;
			 if(str.equals("has"))
				 return true;
			 if(str.equals("have"))
				 return true;
			 if(str.equals("had"))
				 return true;
			 if(str.equals("is"))
				 return true;
			 if(str.equals("am"))
				 return true;
			 if(str.equals("are"))
				 return true;
			 if(str.equals("was"))
				 return true;
			 if(str.equals("were"))
				 return true;
			 if(str.equals("be"))
				 return true;
			 if(str.equals("being"))
				 return true;
			 if(str.equals("been"))
				 return true;
			 if(str.equals("may"))
				 return true;
			 if(str.equals("must"))
				 return true;
			 if(str.equals("might"))
				 return true;
			 if(str.equals("should"))
				 return true;
			 if(str.equals("could"))
				 return true;
			 if(str.equals("would"))
				 return true;
			 if(str.equals("shall"))
				 return true;
			 if(str.equals("will"))
				 return true;
			 if(str.equals("can"))
				 return true;
			 return false;

		 }
		 
		 public static boolean checkLowercase(String str) {
			 Pattern pat = Pattern.compile("\\p{Lower}", Pattern.UNICODE_CASE);
			 Matcher match = null;

			    boolean isHebrew = true;
			    for (int i = 0; i < str.length() && isHebrew; i++){
			        String letter = str.charAt(i) + "";
			        match = pat.matcher(letter);
			        isHebrew = match.matches();
			        if (!isHebrew){
			             break;
			        }
			    }

			 return isHebrew;

		 }

}
