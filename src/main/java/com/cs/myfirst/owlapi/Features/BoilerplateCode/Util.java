package com.cs.myfirst.owlapi.Features.BoilerplateCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {
	
	static LinkedHashMap<String,Integer> commonConstructs;
	static LinkedHashMap<String,Integer> underscoreCommonConstructs;
	
	/**
	 * Sorting on the basis of Axiom Count of construct 
	 * 
	 * Here we are iterating in DL folder ( thats where all construct 'txt' files are present )
	 * We are reading NUMBER-OF-LINES in each file. Because NUMBER-OF-LINES corresponds to AXIOM COUNT of that construct.
	 * So, here we are iterating DL folder, removing 'Owl' and 'Feature' word construct name except for OwlClass
	 * The reason, bcz on UI, we are showing all words without it. See lines 86-95 in app.java. 
	 * To be consistent everywhere
	 * 
	 * Then we are Sorting ( ASCENDING ) hashmap on the basis of VALUE ( axiom count/total lines ).
	 * This resultant hashmap is stored in LinkedHashMap. 
	 * No need to go in details.
	 * Some entries of this RESULTANT linkedhashmap are -> 
	 * { asymmetric , 1 } , { refflexive , 1 } , { objectPropertyDisjoint, 2 } , ....... { subClassOf, 120 }
	 * Notice starting entries will have LOWER VALUES ( axiom count ) and it will go on increasing as we iterate.
	 * 
	 * @return LinkedHashMap , the ORDER in which we ITERATE OVER this, is the order in which we will INSERT into ontology.
	 */
	public static LinkedHashMap<String, Integer> initialAxiomsCount() throws IOException {
		String rootpath = System.getProperty("user.dir");
		File DL = new File(rootpath+"//DL//");
		LinkedHashMap<String,Integer> contruct_axioms_frequency = new LinkedHashMap<String,Integer>();
		if(DL.isDirectory()){
			String[] subNote = DL.list();
			int file_count = 0;
			for(String filename : subNote){
				if ( filename.substring(filename.length()-4).compareTo(".txt") == 0 ) {
					file_count++;
					File contructAxioms = new File(DL,filename);
					BufferedReader br = new BufferedReader(new FileReader(contructAxioms));
					String st; 
					int count = 0;
					while ((st = br.readLine()) != null && st.length() != 0) {
						count++;
					}
					filename = filename.substring(0,filename.length()-4);
					if ( filename.length() >= 7 && filename.substring(filename.length()-7).equals("Feature") ) {
						filename = filename.substring(0,filename.length()-7);
					}
					if ( !filename.equals("OwlClass") && filename.length() >=3 && filename.substring(0,3).equals("Owl") ) {
						filename = filename.substring(3);
					}
					contruct_axioms_frequency.put(filename, count);
					br.close();
				}
			}
		}
		LinkedHashMap<String, Integer> sorted = contruct_axioms_frequency.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		
//		for ( String key : sorted.keySet() ) {
//			System.out.println(key+" "+sorted.get(key));
//		}
		return sorted;
	}
	
	
	/**
	 * Sorting On the basis of User Input of construct
	 * 
	 * @param inputs - user inputted array of constructs value
	 * @param reverse_indexes - Hashmap containing construct with index mapping. 
	 * 		  For examples of some (Key,Value) are -> { subClassOf, 2 ) , ( objectMaxCardinality , 42 )
	 * 		  ( subClass, 2 ) represents that , we will get its corresponding user input in 'INPUTS ARRAY' at index 2.
	 * 		  ( objectMaxCardinality , 42 ) represents that , we will get its corresponding user input in 'INPUTS ARRAY' at index 42.
	 * 
	 * What we are doing here is that, we are Sorting ( ASCENDING ) hashmap on the basis of VALUE ( user input ).
	 * No need to go in details from line 82 to 84.
	 * This resultant hashmap is stored in LinkedHashMap. 
	 * Some entries of this hashmap are -> 
	 * { asymmetric , 1 } , { refflexive , 1 } , { objectPropertyDisjoint, 2 } , ....... { subClassOf, 120 }
	 * Notice starting entries will have LOWER VALUES ( user input ) and it will go on increasing as we iterate.
	 * 
	 * @return LinkedHashMap , the ORDER in which we ITERATE OVER this, is the order in which we will INSERT into ontology.
	 */
	public static LinkedHashMap<String, Integer> initialUserInputCount(int[] inputs,LinkedHashMap<String,Integer> reverse_indexes){
		LinkedHashMap<String,Integer> userInpMap = new LinkedHashMap<String,Integer>();
		int index = 0;
		for( String key : reverse_indexes.keySet() ) {
			userInpMap.put(key, inputs[index]);
			index++;
		}
		LinkedHashMap<String, Integer> sorted = userInpMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		return sorted;
	}
	
	
	/**
	 * @param mappings - whatever Resultant linkedhashmap we get from AXIOM COUNT or USER COUNT. 
	 * the ORDER in which we ITERATE OVER this, is the order in which we will INSERT into ontology.
	 * 
	 * But here, we are SHUFFLING the keys of linkedhashmap, in order to ITERATE in RANDOM order. 
	 * 
	 * @return LinkedHashMap , the ORDER in which we ITERATE OVER this, is the order in which we will INSERT into ontology.      
	 *         But here, we HAVE SHUFFLED the keys, it's values are NO LONGER in any order.
	 */
	public LinkedHashMap<String, Integer> randomizeMapping(LinkedHashMap<String, Integer> mappings){
		LinkedHashMap<String,Integer> randomFiller = new LinkedHashMap<String,Integer>();
		ArrayList<String> onlyKeys = new ArrayList<String>(mappings.keySet());
		Collections.shuffle(onlyKeys);
		for( String iter : onlyKeys ) {
			randomFiller.put(iter, mappings.get(iter));
		}
		return randomFiller;
	}
	
	/**
	 * Here we are simplying INITIALIZING and storing EMPTY Hashmap
	 * There are two kinds Hashmaps. We dont make use of value of Hashmap, we ONLY require KEY for check
	 * ( present/not present ) operation. Hence . 'Value' of Hashmap is INSIGNIFICANT.
	 * 
	 * 1) - commonConstructs
	 * We store concepts/properties SIMPLY ( not without any underscore/extra ones )
	 * Example Key/Value:- ( takesCourse, 0 ) , ( Employee, 0 ) , ( dislikes, 0 )
	 * 
	 * 2) - underscoreCommonConstructs
	 * We store ONLY those concepts/properties which CONSISTS UNDERSCORE in them.
	 * Example Key/Value:- ( takesCourse_2, 0 ) , ( Employee_0, 0 ) , ( dislikes_1, 0 ) , ( Man_5, 0 )
	 */
	public static void initializeGlobalHashMaps() throws IOException {
//	      LinkedHashMap<String,Integer> commonConstructs = new LinkedHashMap<String,Integer>();
	      commonConstructs = new LinkedHashMap<String,Integer>();
	      
	      FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+File.separator+"commonConstructs.ser");
	      ObjectOutputStream oos = new ObjectOutputStream(fos);
	      oos.writeObject(commonConstructs);
	      oos.close();fos.close();
	      
//	      LinkedHashMap<String,Integer> underscoreCommonConstructs = new LinkedHashMap<String,Integer>();
	      underscoreCommonConstructs = new LinkedHashMap<String,Integer>();
	      
	      FileOutputStream fos1 = new FileOutputStream(System.getProperty("user.dir")+File.separator+"underscoreCommonConstructs.ser");
	      ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
	      oos1.writeObject(underscoreCommonConstructs);
	      oos1.close();fos1.close();
	}
	
	public int[] readUserInputFile() throws NumberFormatException, IOException {
		   File userFile = new File(System.getProperty("user.dir")+"/constructs-input-file.txt"); 
		   BufferedReader br = new BufferedReader(new FileReader(userFile));
		   String st="";
		   ArrayList<Integer> constructInp = new ArrayList<Integer>();
		   while ((st = br.readLine()) != null) {
		     String[] userInp = st.split(",");
		     System.out.println(userInp[0]+","+userInp[1]);
		     constructInp.add(Integer.parseInt(userInp[1]));
		   } 
		   int[] inputs = constructInp.stream().mapToInt(Integer::intValue).toArray();
		   br.close();
		   return inputs;
	}
}
