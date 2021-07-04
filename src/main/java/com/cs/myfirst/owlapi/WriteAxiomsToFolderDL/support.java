package com.cs.myfirst.owlapi.WriteAxiomsToFolderDL;

//public class support {
//
//}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class support {
	
	 static String[] twoWorded = {"OwlAsymmetricProperty.txt","OwlFunctionalDataProperty.txt","OwlFunctionalObjectProperty.txt",
			 "OwlInverseFunctionalProperty.txt","OwlIrreflexiveProperty.txt","OwlReflexiveProperty.txt",
			 "OwlSymmetricProperty.txt","OwlTransitiveProperty.txt"};
	 //,"DataComplementOf.txt"
	 
	 static String[] threeWorded = {"RdfsSubClassOfFeature.txt","RdfsDataDomain.txt",
			 "RdfsDataSubPropertyOf.txt",
			 "OwlObjectPropertyDisjointWith.txt","RdfsObjectDomain.txt","RdfsObjectRange.txt",
			 "RdfsObjectSubPropertyOf.txt","OwlInverseOfProperty.txt"};
	 
	 //AllDisjointObjectProperties and ObjectPropertyDisjointWith are SAME EVEN IN ontobench code as well, BOTH ARE SAME.
	 //OwlObjectComplementFeature
	 //"RdfsDataRange.txt",
	 //separator -> "DataMaxQualifiedCardinality.txt","DataMinQualifiedCardinality.txt","DataQualifiedCardinality.txt",ObjectQualifiedCardinality,ObjectMaxQualifiedCardinality,ObjectMinQualifiedCardinality
	 //"OwlHasSelfFeature.txt","OwlObjectComplementFeature.txt",
	 public static void initialize() {
		 HashMap<String,String> twoCountMap = new HashMap<String,String>();
		 HashMap<String,String> threeCountMap = new HashMap<String,String>();
		 for ( String two : twoWorded ) { twoCountMap.put(two,""); }
		 for ( String three : threeWorded ) { threeCountMap.put(three,""); }	
	 }
	 
	 public static void genericWriteToFile(String[] arr, String separator, String fileName) throws IOException {
			
			File file = new File(System.getProperty("user.dir")+"/DL/"+fileName);
			StringBuilder str = new StringBuilder(); 
			
			if ( arr.length == 2 ) {
//				System.out.println(arr[0]+" "+arr[1]+" || "+separator+" || "+fileName+" || "+arr.length);
				str.append(arr[1]+" "+separator);
			}
			if ( arr.length == 3 ) {
//				System.out.println(arr[0]+" "+arr[1]+" "+arr[2]+" || "+separator+" || "+fileName+" || "+arr.length);
			}
//			System.out.println();
			/*
			if(separator.equals(" ") ==  false) {
				str.append(arr[1]+" ");
				str.append(separator);
				str.append(" "+arr[2]);
			}
			else {
				str.append(arr[2]);
				str.append(separator);
				str.append(arr[0]);
			}
			
			String k = str.toString();
			
			
			try {
			    Scanner scanner = new Scanner(file);
			    boolean flag = false;
			    while (scanner.hasNextLine()) {
			        String line = scanner.nextLine();
			        if(line.contains(k)) { 
			            System.out.println("ho hum, i found it on line ");
			            flag = true;
			            break;
			        }
			    }
			    
			    if(!flag) {
					FileWriter fr = new FileWriter(file, true);
					fr.write("\n"+str.toString());
					fr.close();
					System.out.println(str);
					System.out.println("appended");
			    }
			} catch(FileNotFoundException e) { 
			    //handle this
			}
			*/
			
	 }
	 
	 //"EquivalentDataProperty.txt","OwlEquivalentObjectProperty.txt","ObjectProperty.txt",
	public static void writetofile(String[] arr, String ok, String ff) throws IOException {
		
		//File file = new File("subclass.txt");
		StringBuilder str = new StringBuilder(); 
		System.out.println(ff+" <-- file here --> "+arr.length+" || "+arr[0]+" {} "+arr[1]);
		if(ok.equals(" ") ==  false) {
			str.append(arr[1]+" ");
			str.append(ok);
			str.append(" "+arr[2]);
		}
		else {
			str.append(arr[2]);
			str.append(ok);
			str.append(arr[0]);
		}
		//System.out.println(str);
		
		String k = str.toString();
		String p = System.getProperty("user.dir")+"/DL/"+ff;
		File file = new File(p);

		    Scanner scanner = new Scanner(file);
		    //now read the file line by line...
		    int lineNum = 0;
		    boolean flag = false;
		    while (scanner.hasNextLine()) {
		    	//System.out.println("111");
		        String line = scanner.nextLine();
		        lineNum++;
		        //System.out.println(lineNum + " " + line);
		        if(line.contains(k)) { 
		            System.out.println("ho hum, i found it on line " +lineNum);
		            flag = true;
		            break;
		        }
		    }
		    
		    if(!flag) {
				FileWriter fr = new FileWriter(file, true);
//				fr.write("\n"+str.toString());
//				fr.close();
				System.out.println(str);
				System.out.println("appended");
		    }
	}
	
	public static void writetofile2(String[] arr, String ok, String ff) throws IOException {
		//File file = new File("subclass.txt");
		StringBuilder str = new StringBuilder(); 
		str.append(arr[1]+" ");
		str.append(ok);
		System.out.println(str);
		
		String k = str.toString();
		String p = "D:/Desktop/DL/"+ff;
		File file = new File(p);

		try {
		    Scanner scanner = new Scanner(file);
		    //now read the file line by line...
		    int lineNum = 0;
		    boolean flag = false;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        //System.out.println(lineNum + " " + line);
		        if(line.contains(k)) { 
		            System.out.println("ho hum, i found it on line " +lineNum);
		            flag = true;
		            break;
		        }
		    }
		    
		    if(!flag) {
				FileWriter fr = new FileWriter(file, true);
				fr.write("\n"+str.toString());
				fr.close();
				System.out.println(str);
				System.out.println("appended");
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
		}
	}
	
	
public static void writetofile3(String[] arr, String ok, String ff, int check) throws IOException {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
			
		}
		System.out.println();
		
		StringBuilder str = new StringBuilder(); 
				
		for(int j=0;j<arr.length;j++) {
			if(j==arr.length-1) {
				if(check == 1) {
					str.append(ok);
				}
				str.append(arr[j]);
			}
			else {
				if(j==2 && check ==0) {
					str.append(ok);
				}
				str.append(arr[j]+" ");	
					
				}
			}

		System.out.println(str);
		
		String k = str.toString();
		String p = "D:/Desktop/DL/"+ff;
		File file = new File(p);

		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line...
		    int lineNum = 0;
		    boolean flag = false;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        //System.out.println(lineNum + " " + line);
		        if(line.contains(k)) { 
		            System.out.println("ho hum, i found it on line " +lineNum);
		            flag = true;
		            break;
		        }
		    }
		    if(!flag) {
				FileWriter fr = new FileWriter(file, true);
				fr.write("\n"+str.toString());
				fr.close();
				System.out.println(str);
				System.out.println("appended");
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
		}
	}
	
	public static void writetofile4(String[] arr, String ok, String ff) throws IOException {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
			
		}
		System.out.println();
		
		StringBuilder str = new StringBuilder(); 
		str.append(arr[0]+" ");
		str.append(ok+" ");
				
		for(int j=1;j<arr.length;j++) {
			if(j==arr.length-1) {
				str.append(arr[j]);
			}
			else {
				str.append(arr[j]+",");	
					
				}
			}
		System.out.println(str);
		
		String k = str.toString();
		String p = "D:/Desktop/DL/"+ff;
		File file = new File(p);

		try {
		    Scanner scanner = new Scanner(file);
		    //now read the file line by line...
		    int lineNum = 0;
		    boolean flag = false;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        //System.out.println(lineNum + " " + line);
		        if(line.contains(k)) { 
		            System.out.println("ho hum, i found it on line " +lineNum);
		            flag = true;
		            break;
		        }
		    }
		    if(!flag) {
				FileWriter fr = new FileWriter(file, true);
				fr.write("\n"+str.toString());
				fr.close();
				System.out.println(str);
				System.out.println("appended");
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
		}
	}
	
		public static ArrayList<ArrayList<String>> EquivalentTerms(String arr[], ArrayList<ArrayList<String>> eqlist) {
		if(eqlist.isEmpty()) {
			List<String> list = Arrays.asList(arr); 
			ArrayList<String> temp = new ArrayList<>(list.size());
			temp.addAll(list);
		    eqlist.add(temp);
		}
		else {
			boolean flag = true;
			for (ArrayList<String> i : eqlist) {
				if(i.contains(arr[0]) && i.contains(arr[1])) {
					flag = true;
					break;
				}
				if(i.contains(arr[0])) {
					if(i.contains(arr[1])==false) {
						i.add(arr[1]);
						flag = true;
						break;
					}
				}
				else if(i.contains(arr[1])) {
					if(i.contains(arr[0])==false) {
						i.add(arr[0]);
						flag = true;
						break;
					}
				}
				else {
					flag = false;
					break;
					
				}
			}
			
			
			if(flag == false) {
				List<String> list = Arrays.asList(arr); 
				ArrayList<String> temp = new ArrayList<>(list.size());
				temp.addAll(list);
				//System.out.print("ok ");System.out.println(temp);
			    eqlist.add(temp);
			    
			}
		}
		
		return eqlist;
	}
	
	public static void writetofile5(ArrayList<String> arrlist, String ok, String ff) throws IOException {		
		
		Object[] arr = arrlist.toArray(); 
		
		
		StringBuilder str = new StringBuilder(); 
		str.append(arr[0]+" ");
		str.append(ok+" ");
				
		for(int j=1;j<arr.length;j++) {
			if(j==arr.length-1) {
				str.append(arr[j]);
			}
			else {
				str.append(arr[j]+",");	
					
				}
			}
		
	
	
		System.out.println(str);
	
		String k = str.toString();
		
		String p = "/Users/ishaanbhagat/Desktop/DL/"+ff;
		File file = new File(p);
		try {
		    Scanner scanner = new Scanner(file);
		    //now read the file line by line...
		    int lineNum = 0;
		    boolean flag = false;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        //System.out.println(lineNum + " " + line);
		        ArrayList<String> listinfile = stringtolist(line,ok);
		       // System.out.println(listinfile);
		        Collections.sort(arrlist);
		       // System.out.println(arr);
		        if (listinfile.equals(arrlist) == true) {
		            System.out.println("ho hum, i found it on line " +lineNum);
		            flag = true;
		            break;
		        }
		    }
		    
		    if(!flag) {
				FileWriter fr = new FileWriter(file, true);
				fr.write("\n"+str.toString());
				fr.close();
				//System.out.println(str);
				System.out.println("appended");
		    }
		    
		    
		} catch(FileNotFoundException e) { 
		    //handle this
		}
	}
	
	
	public static ArrayList<String> stringtolist(String str, String ok) {
		 str =str.replace(ok,",");
		 String arr[] = str.split(",");
		 List<String> list = Arrays.asList(arr); 
		 ArrayList<String> temp = new ArrayList<>(list.size());
		 temp.addAll(list);
		 Collections.sort(temp); 
		 return temp;
		 
	}

	public static String clean_dataoneof(String test) {
		test = test.replace("DataOneOf\"", "");
		test = test.replace("^^xsd:string", "");
		test = test.replace("\"", "");
		return test;
	}
}
