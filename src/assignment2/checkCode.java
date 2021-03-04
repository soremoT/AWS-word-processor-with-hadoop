package assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

import Custom.PathPair;
import Custom.PathPath;
import Custom.TestSet;
import Custom.Triple;

public class checkCode {

	 
	    public static void m() throws IOException {
			   File file = new File("p"); 
			   
			   BufferedReader br = new BufferedReader(new FileReader(file)); 
			   
			   String st; 
			   while ((st = br.readLine()) != null) 
				   System.out.println("X");
			   
			 
				 
	    }

	   public static void main(String[] args) throws IOException, InterruptedException
	   {
		    	List<Triple> list=new ArrayList<Triple>();
		    	
		    	TestSet set=new TestSet();
		    	List<Triple> negListOfexisting=new ArrayList();
		    	List<Triple> posListOfexisting=new ArrayList();
		    	List<PathPair> neg=set.getNeg();
		    	List<PathPair> pos=set.getPos();
		    	
		  
		   	  
		   File file = new File("p");
		   
		   BufferedReader br = new BufferedReader(new FileReader(file)); 
		   
		   String st; 
		   while ((st = br.readLine()) != null) {
			   String aa [] =st.split(",");
			   String bb []=aa[1].split("\\s+");
			   StringBuilder cc=new StringBuilder();
			   String sim=null;
			   for(int i=0;i<bb.length-1;i++) {
				   cc.append(bb[i]+" ");
				   sim=bb[bb.length-1].trim();
				   }
			   list.add(new Triple(aa[0].trim(),cc.toString().trim(),sim));
	
		   }
		
	  for(Triple gg:list)
	  {
		  for(PathPair ne:neg)
		  if(gg.getPath1().equals(ne.getPath1())&&gg.getPath2().equals(ne.getPath2())) {
			  negListOfexisting.add(new Triple(gg.getPath1(),gg.getPath2(),gg.getValue()));
			  System.out.println(gg.getPath1());
		  }
	  }
	  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	  for(Triple gg:list)
	  {
		  for(PathPair ne:pos) {
		  if(gg.getPath1().equals(ne.getPath1())&&gg.getPath2().equals(ne.getPath2())) {
			  posListOfexisting.add(new Triple(gg.getPath1(),gg.getPath2(),gg.getValue()));
	}
	  }}

	  Double sumOfNeg=0.0;
	  for(int i=0;i<negListOfexisting.size();i++)
	  {
		  sumOfNeg=sumOfNeg+Double.valueOf(negListOfexisting.get(i).getValue());
	  }
	  Double sumOfPos=0.0;
	  for(int i=0;i<posListOfexisting.size();i++)
	  {
		  sumOfPos=sumOfPos+Double.valueOf(posListOfexisting.get(i).getValue());
		  
	  }
	  
	  System.out.println(sumOfNeg/negListOfexisting.size());
	  System.out.println(sumOfPos/posListOfexisting.size());
	  System.out.println(negListOfexisting.size());
	  System.out.println(posListOfexisting.size());
	   }

		public static String getString(String [] str) {
			 StringBuilder a= new StringBuilder();
			 for(int i=0;i<str.length-1;i++)
				 a.append(str[i]+" ");
			 
		
			 return a.toString().trim();
		}
	
		
	
	
	
	    public static void makePathHigh(String [] str) throws IOException, InterruptedException {
	    	String verb;
	    	String noun1=null;
	    	String noun2=null;
	    	String [] path = new String [str.length];
	    	for(int i=0;i<str.length;i++) {
	    		 String[] parts = str[i].split("/");
	    		 path[i]=parts[0];
	    		 if(checkNoun(parts[1])) {
		    		if(noun1==null) {
		    			 noun1 = parts[0];
		    			 path[i]="X";
		    		}
		    		else {noun2 = parts[0];
		    				path[i]="Y";
		    		}}
	    		 }
	    	boolean flag=true;
	    	for(int i=0;i<str.length;i++) {
	    			if(i+1==str.length) 
	    				break;
	    			if(path[i].equals("X")&&path[i+1].equals("Y")) {
	    				flag=false;
	    			}
	    	}
	    	if(flag) {
	    		makePathInverse(path,noun1,noun2);
	    	}
	    	
	    }
    public static void makePath5(String [] str) throws IOException, InterruptedException {
    	String noun1=null;
    	String noun2=null;
    	String[] path1=new String[5];
    	String[] path2=new String[5];
    	String[] path3=new String[5];
    	path1[1]="X";
    	path1[4]="Y";
    	path2[2]="X";
    	path2[4]="Y";
    	path3[1]="X";
    	path3[3]="Y";
    	//make each path manually
    	for(int i=0;i<str.length;i++) {
    		 String[] parts = str[i].split("/");
    		  if(checkVerb(parts[1])) 
    			  path1[2] = parts[0];
    		  if(checkNoun(parts[1]))
    			  if(noun1==null)
    				  noun1 = parts[0];
    			  else noun2 = parts[0];
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
    	makePathInverse(path1,noun1,noun2);
    	makePathInverse(path2,noun1,noun2);
    	makePathInverse(path3,noun1,noun2);
    }
    
   public static void  makePath3(String [] str) throws IOException, InterruptedException {
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

    	makePathInverse(path,noun1,noun2);

    	 
    	 
    	// context.write(FinalKey1, FinalValue);
    	// context.write(FinalKey2, FinalValue);
    	 
    }
    
    public static void  makePath4(String [] str) throws IOException, InterruptedException {
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
    	
    	makePathInverse(path1,noun1,noun2);
    	makePathInverse(path2,noun1,noun2);
    	makePathInverse(path3,noun1,noun2);
    	    	 
    }
    	//create inverse path and put paths to context(inverse X and Y)
    public static void makePathInverse(String [] str,String noun1,String noun2) throws IOException, InterruptedException 	{
    //add path to context
    	writeToContext(str,noun1,noun2);
    	for(int i=0;i<str.length;i++) {
    		if(str[i].equals("X")) {
    			str[i]="Y";
    			continue;}
    		if(str[i].equals("Y"))
    			str[i]="X";
    	}
    	//add inverse path to context
    	writeToContext(str,noun1,noun2);
    		
    }
    public static void  writeToContext(String [] str,String noun1,String noun2) throws IOException, InterruptedException {
    		Text FinalKey=new Text(); 
			Text FinalValue=new Text();
	    	StringBuilder localValue =  new StringBuilder();
	    	StringBuilder localKey =  new StringBuilder();
	    	for(int i=0;i<str.length;i++)
	    		localKey.append(str[i] + " ");
	    
	    	localValue.append(noun1 + " " + noun2);
	    	 FinalValue.set(localValue.toString());
	    	 FinalKey.set(localKey.toString());
	    	 
	    	 
	    	 System.out.println(localValue.toString());
	    	 System.out.println(localKey.toString());
	    	//context.write(FinalKey, FinalValue);
		   // context.write(FinalKey, FinalValue);
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
	 
	 public static boolean checkWord(String str) {
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
