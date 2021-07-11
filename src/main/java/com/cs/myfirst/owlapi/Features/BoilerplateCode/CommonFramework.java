package com.cs.myfirst.owlapi.Features.BoilerplateCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import owl.cs.myfirst.owlapi.app;

public class CommonFramework {
	
	public CommonFramework() throws IOException, ClassNotFoundException {}
	
	public static boolean isIsNumber(String strNum) {
		try {
			int num = Integer.parseInt(strNum);
			return true;
		} catch(NumberFormatException exception) {
			return false;
		}
	}
	
	public static boolean isIsLiteral(String strNum) {
		if ( strNum.contains("xsd") || strNum.contains("owl") || strNum.contains("rdf") ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getRandomMaxTerm(String concept,int limit,int userInp) throws ClassNotFoundException, IOException {
		
		LinkedHashMap<String, Integer> commonGlobalHashMap = TextFileProcessor.loadGlobalCommons("commonConstructs.ser");

		if ( commonGlobalHashMap.containsKey(concept) ) {
			int count = commonGlobalHashMap.get(concept);
//			System.out.println(count+" || globalhashmap count "+limit+" || userInp "+userInp);
		}
		
		String value = "";
		int upperBound = ((userInp-2)/limit)-1;
		if ( upperBound > 0 ) {
			Random r = new Random();
			int low = -1;
			int high = upperBound-1;
			int result = r.nextInt(high-low) + low;
//			System.out.println(" upper bound "+upperBound+" || "+low+" || "+high+" || "+result+" || "+concept);
			
			if ( result != -1 ) { value = concept.substring(0, concept.indexOf("_"))+"_"+String.valueOf(result);
			} else { value = concept; }
		} else {
			value = concept;
		}
		return value;
	}
	
	/**
	 * @param rankedAxioms
	 * @return
	 * 
	 * CAN BE IGNORED FOR NOW, 
	 * Here we are implementing to PREVENT INSERTION of Additional Terms in GENERIC constructs.
	 */
	public static ArrayList<String> filterGlobalHashMapConcepts(ArrayList<String> rankedAxioms) throws ClassNotFoundException, IOException {
		TextFileProcessor filterOnly = new TextFileProcessor();
		LinkedHashMap<String, Integer> globalCommons = filterOnly.loadGlobalCommons("commonConstructs.ser");
		ArrayList<String> chosen = new ArrayList<String>();
		
		for( String ax : rankedAxioms ) {
			String[] contents = ax.split(",");
//			String filterAxiom = contents[0]+",";
			String filterAxiom = "";
			for( int i = 0 ; i < contents.length ; i++ ) {
				if ( isIsNumber(contents[i]) || isIsLiteral(contents[i]) || globalCommons.containsKey(contents[i]) ) {
					filterAxiom = filterAxiom+contents[i]+",";
				}
			}
			if(filterAxiom.length() > 0) {
				filterAxiom = filterAxiom.substring(0,filterAxiom.length()-1);
				if ( filterAxiom.contains(",") ) {
					chosen.add(filterAxiom);
				}
			}
		}
		return chosen;
	}
	
	/**
	 * @param userAxioms - Axioms ( Comma separated terms String ) Arraylist for a construct.
	 * @param count - Counter of number of axioms INSERTED till NOW ( Before Invokation ), initially it will be 0. ( Line
	 * @param userInp - userCount ( Num of Axiom ) for that construct.
	 * @param phase - possible values 
	 * 				-> 'first' ( when appending WITHOUT underscore ) , 
	 * 				-> 'second' ( appending UNDERSCORE, to incorporate EXTRA Axioms )
	 * @param incre - 'underscore' value to append at the end of term, "_incre" , Ex :- "_0", "_1", "_2" etc.
	 * @param filename - fileName of the construct
	 * 
	 * First we remove "Owl" and "Feature" from fileName, bcz our construct name are WITHOUT it. Exception -> OwlClass
	 * IGNORE Commented Lines ( it is regarding to prevent INSERT EXTRA terms/concepts in Generic Disjoint type constructs.
	 * 
	 * If it is phase "second", we append _incre ( _0/_1 etc. ) at the end of terms
	 * 
	 * This Line :- "Method method = app.objectMap.get(filename).getClass().getDeclaredMethod("convertLineTo"+filename, ArrayList.class);"
	 * 
	 * Here, we have made use of "Reflections" in Java ( Read geeksforgeeks article, its NOT DIFFICULT ).
	 * That it does is , remember ObjectMap HashMap in app.java , what that hashmap stored as association of each contruct with
	 * its Category class. Have mentioned Construct names under each Category at the end of "ORDER.txt"
	 * 
	 * In package 'owlapi.Features', we have made different Category JAVA files. Ex - ClassEnumerationcategory.java, ClassExpressionAxiomsCategory.java, DataRangesCategory.java like 8 files. "AssertionCategory.java" file IS NOT currently being used.
	 * In each JAVA file, we have made a GENERIC function in FORMAT of "convertLineTo"+constructName
	 * For example :- in ClassExpressionAxiomsCategory.java file, we have methods like this :-
	 * 1) "RdfsSubClassOf" -> convertLineToRdfsSubClassOf
	 * 2) "DisjointWith" -> convertLineToDisjointWith
	 * 3) "AllDisjointClasses" -> convertLineToAllDisjointClasses
	 * and so. 
	 * So we get this owl api "method" from the above line corresponding line, without the need of "IF/ELSE" etc.
	 * 
	 * This Line :- method.invoke(app.objectMap.get(filename), allConcepts);
	 * Here, we are simply invoking that function TO INSERT INTO ONTOLOGY, with the ArrayList of Terms to insert.
	 * 
	 * @return "count" variable, after inserting ONE AXIOM, we increment "count" variable and return it if is >= userCount.
	 */
	
	static LinkedHashMap<String,Integer> rdfsSubsOnly;
	
	public static int insertAxiomIntoOntology(ArrayList<String> userAxioms, int count, int userInp, String phase, int incre,String filename) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, OWLOntologyCreationException, ClassNotFoundException, OWLOntologyStorageException, IOException {
		filename = filename.substring(0,filename.length()-4);
		if ( filename.length() >= 7 && filename.substring(filename.length()-7).equals("Feature") ) {
			filename = filename.substring(0,filename.length()-7);
		}
		if ( !filename.equals("OwlClass") && filename.length() >=3 && filename.substring(0,3).equals("Owl") ) {
			filename = filename.substring(3);
		}
//		System.out.println(filename);
		
//		String globalHashMapName = phase.equals("second") == true ? "underscoreCommonConstructs.ser" : "commonConstructs.ser";
//		LinkedHashMap<String, Integer> globalHashMap = TextFileProcessor.loadGlobalCommons(globalHashMapName);
		
		ArrayList<String> rankedAxioms = userAxioms;
		
		FilenameConstructMapping fcm = new FilenameConstructMapping();
		ArrayList<String> genericConstructs = fcm.disjointOnes();
		if ( genericConstructs.contains(filename) ) {
			rankedAxioms = filterGlobalHashMapConcepts(userAxioms);
			if ( rankedAxioms.size() == 0 ) {
				rankedAxioms = userAxioms;
			}
		}
		
		for( String ax : rankedAxioms ) {
			String[] contents = ax.split(",");
			ArrayList<String> allConcepts = new ArrayList<String>();
			for ( String c : contents ) {
				String key = "";
//				if ( FilenameConstructMapping.rootConcepts.containsKey(c) ) {
//					key = c;
//				}else {
//					key = phase.equals("second") == true && !isIsNumber(c) && !isIsLiteral(c) ? c+"_"+incre : c;
//				}
					
				key = phase.equals("second") == true && !isIsNumber(c) && !isIsLiteral(c) ? c+"_"+incre : c;
//				globalHashMap.put(key,1);
				allConcepts.add(key);
			}
			
//			if ( filename.equals("RdfsSubClassOf") || filename.equals("RdfsObjectSubPropertyOf") || filename.equals("RdfsDataSubPropertyOf") ) { rdfsSubsOnly.put(allConcepts.get(0),1); }
			if ( FilenameConstructMapping.constructsForLast.containsKey(filename) && phase.equals("second") == true ) {
				if ( !isIsNumber(allConcepts.get(1)) && !isIsLiteral(allConcepts.get(1)) && allConcepts.size() == 2 ) {
					//((userInp-2)/(sortedUserInput.get(filename))-1)
					String value = getRandomMaxTerm(allConcepts.get(1),rankedAxioms.size(),userInp);
//					System.out.println(filename+" common framework "+value+" || "+allConcepts.get(1)+" || "+userInp+" | axiom size "+rankedAxioms.size());
					allConcepts.add(1, value);
				}
			}
			
			if ( FilenameConstructMapping.constructsForLast.containsKey(filename) ) { 
				rdfsSubsOnly.put(allConcepts.get(0),1); 
			}

//			if ( FilenameConstructMapping.constructsForLast.containsKey(filename) ) {
//				System.out.println(allConcepts.get(0)+" || "+allConcepts.get(1)+" || "+count+" || "+userInp+" || "+filename);
//			}
			
			Method method = app.objectMap.get(filename).getClass().getDeclaredMethod("convertLineTo"+filename, ArrayList.class);
			method.invoke(app.objectMap.get(filename), allConcepts);
			
//			TextFileProcessor.saveGlobalCommons(globalHashMap,globalHashMapName);
			count++;
			if ( count >= userInp ) {
				break;
			}
		}
		return count;
	}
	
	/**
	 * @param fileName - fileName of 'txt' axioms file to read for particular CONSTRUCT.
	 * @param construct - name of the CONSTRUCT
	 * @param userInp - user input ( number of axiom ) for that CONSTRUCT.
	 * 
	 * This function is invoked in generateOntology of app.java file.
	 * What this method does is 
	 * First reads axioms in arraylist from 'txt' file of that construct.
	 * Shuffles, in order to INTRODUCE randomness 
	 * Get ranking of the axioms.
	 * 
	 * Now Lets Consider An Examples :- 
	 * Suppose one construct "disjointUnion" has total 10 axioms in its 'txt' file.
	 * Wrt an user input, 
	 * 
	 * There are 2 possible conditions :- 
	 * 
	 * 1) - userInput <= axiomCount , Example - userInput ( 5 ) < axiomCount ( 10 )
	 * We will straight away insert First 5 indexes of Randked ArrayList , 
	 * Bcz 0th index highest ranked, 1st index second highest ranked and so on.
	 * 
	 * 2) - userInput > axiomCount , Example - userInput ( 15 ) > axiomCount ( 10 )
	 * We have 10 axioms, they all will get inserted anyways.
	 * Thats what logic does in first 5 lines of the function. 
	 * Line 'int count = insertAxiomIntoOntology' , gives the NUMBER OF AXIOMS INSERTED into ontology TILL NOW.
	 * If count is still less than userCount, that means more axioms NEED to INSERTED.
	 * 
	 * For next EXTRA 5 axioms, we append "_" , keep inserting till we reach userCount.
	 * 
	 * From here, DIRECTLY read the comments above "insertAxiomIntoOntology" function of SAME java file,
	 * to get more details of how we are making use of OWL API methods.
	 * 
	 * After inserting axioms INTO ONTOLOGY, 
	 * We have to add them in "globalHashMap". pattern.insertIntoGlobalCommons(rankedAxioms,userInp) , does the same thing.
	 * In this TextFileProcessor java file, We have made use of SIMILAR LOGIC that we have applied here in "addToOntology" method.
	 * Instead of inserting into Ontology. We are inserting into "globalHashMap".
	 * 
	 * Here "addToOntology" function corresponds to "insertIntoGlobalCommons" there. They are functioning similar.
	 * Here "insertAxiomIntoOntology" function corresponds to "insertIntoGlobalHashMaps" there. They are functioning similar.
	 */
	public void addToOntology(String fileName, String construct,int userInp) throws ClassNotFoundException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, OWLOntologyCreationException, OWLOntologyStorageException {		
		rdfsSubsOnly = new LinkedHashMap<String,Integer>();
		TextFileProcessor pattern = new TextFileProcessor();
		ArrayList<String> axioms = pattern.readTxtFile(fileName,construct);

		if ( axioms.size() == 0 ) return ;
		
		Collections.shuffle(axioms);
		ArrayList<String> rankedAxioms = pattern.rankAxioms(axioms);
		int count = insertAxiomIntoOntology(rankedAxioms,0,userInp,"first",0,fileName);
		if ( count < userInp ) {
			int incre = 0;
			while ( count < userInp ) {
				count = insertAxiomIntoOntology(rankedAxioms,count,userInp,"second",incre,fileName);
				if ( count >= userInp ) {
					break;
				}
				incre++;
			}
		}
		if ( !FilenameConstructMapping.lastDomainRangeDisjoint.containsKey(fileName) )
			pattern.insertIntoGlobalCommons(rankedAxioms,userInp);
	}
}
