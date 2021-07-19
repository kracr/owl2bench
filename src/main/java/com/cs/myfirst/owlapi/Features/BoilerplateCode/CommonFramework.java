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
		if ( strNum.contains("xsd") || strNum.contains("owl") || strNum.contains("rdf") ) return true;
		else return false;
	}
	
	/**
	 * @param rankedAxioms
	 * @return
	 * 
	 * CAN BE IGNORED FOR NOW, 
	 * Here we are implementing to PREVENT INSERTION of Additional Terms in GENERIC constructs.
	 */
	public static ArrayList<String> filterGlobalHashMapConcepts(ArrayList<String> rankedAxioms) throws ClassNotFoundException, IOException {
		LinkedHashMap<String, Integer> globalCommons = Util.commonConstructs;
		ArrayList<String> chosen = new ArrayList<String>();
		
		for( String ax : rankedAxioms ) {
			String[] contents = ax.split(",");
			String filterAxiom = "";
			for( int i = 0 ; i < contents.length ; i++ ) {
				if ( isIsNumber(contents[i]) || isIsLiteral(contents[i]) || globalCommons.containsKey(contents[i]) ) filterAxiom = filterAxiom+contents[i]+",";
			}
			if(filterAxiom.length() > 0) {
				filterAxiom = filterAxiom.substring(0,filterAxiom.length()-1);
				if ( filterAxiom.contains(",") ) chosen.add(filterAxiom);
			}
		}
		return chosen;
	}
	
	public static void saveTermsInGlobalHashMap(String term, String phase) {
		if ( isIsNumber(term) && isIsLiteral(term) ) return ;
		
		String ori = new String(term);
		int incre = term.contains("_") ? Integer.parseInt(term.substring(term.indexOf("_")+1)) : 0;
		term = term.contains("_") ? term.substring(0,term.indexOf("_")) : term;
		
		if( phase.equals("third") ) phase = Util.commonConstructs.containsKey(term) ? "second" : "first";
		
		if ( phase.equals("first") ) Util.commonConstructs.putIfAbsent(term, 1);
		if ( phase.equals("second") ) {
			if ( Util.commonConstructs.containsKey(term) && Util.commonConstructs.get(term) <=incre+1 ) 
				Util.commonConstructs.put(term, Util.commonConstructs.get(term)+1);
			Util.underscoreCommonConstructs.put(ori, incre);
		}
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
	public static int insertAxiomIntoOntology(ArrayList<String> userAxioms, int count, int userInp, String phase, int incre,String filename) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, OWLOntologyCreationException, ClassNotFoundException, OWLOntologyStorageException, IOException {
		filename = filename.substring(0,filename.length()-4);
		if ( filename.length() >= 7 && filename.substring(filename.length()-7).equals("Feature") ) filename = filename.substring(0,filename.length()-7);
		if ( !filename.equals("OwlClass") && filename.length() >=3 && filename.substring(0,3).equals("Owl") ) filename = filename.substring(3);

		ArrayList<String> rankedAxioms = userAxioms;
		ArrayList<String> genericConstructs = FilenameConstructMapping.disjointOnes();
		
		if ( genericConstructs.contains(filename) ) {
			rankedAxioms = filterGlobalHashMapConcepts(userAxioms);
			if ( rankedAxioms.size() == 0 ) rankedAxioms = userAxioms;
		}
		
		for( String ax : rankedAxioms ) {
			String[] contents = ax.split(",");
			ArrayList<String> allConcepts = new ArrayList<String>();
			for ( String c : contents ) {
				String key = phase.equals("second") == true && !isIsNumber(c) && !isIsLiteral(c) ? c+"_"+incre : c;
				allConcepts.add(key);
				
				if ( !FilenameConstructMapping.constructsForLast.containsKey(filename) ) saveTermsInGlobalHashMap(key,phase);
			}
			
			if ( FilenameConstructMapping.constructsForLast.containsKey(filename) ) {
				if ( !isIsNumber(allConcepts.get(1)) && !isIsLiteral(allConcepts.get(1)) && allConcepts.size() == 2 ) {
					String value = LastDance.getRandomTillMaxTerm(allConcepts.get(1));
					
//					System.out.println(filename+" common framework "+value+" || "+allConcepts.get(1)+" || "+userInp+" | axiom size "+rankedAxioms.size());
					allConcepts.add(1, value);
					
					saveTermsInGlobalHashMap(allConcepts.get(0),phase); 
					saveTermsInGlobalHashMap(allConcepts.get(1),phase);
				}
			}
			
			if ( FilenameConstructMapping.constructsForLast.containsKey(filename) && !FilenameConstructMapping.lastDomainRangeDisjoint.containsKey(filename) ) 
				app.rdfsSubsOnly.put(allConcepts.get(0),1);
			
			System.out.println(allConcepts);
			Method method = app.objectMap.get(filename).getClass().getDeclaredMethod("convertLineTo"+filename, ArrayList.class);
			method.invoke(app.objectMap.get(filename), allConcepts);
			
			count++;
			if ( count >= userInp ) break;
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
		TextFileProcessor pattern = new TextFileProcessor();
		ArrayList<String> axioms = TextFileProcessor.readTxtFile(fileName,construct);

		if ( axioms.size() == 0 ) return;
		
		Collections.shuffle(axioms, new Random(2));
		ArrayList<String> rankedAxioms = pattern.rankAxioms(axioms);
		int count = insertAxiomIntoOntology(rankedAxioms,0,userInp,"first",0,fileName);
		if ( count < userInp ) {
			int incre = 0;
			while ( count < userInp ) {
				count = insertAxiomIntoOntology(rankedAxioms,count,userInp,"second",incre,fileName);
				if ( count >= userInp ) break;
				incre++;
			}
		}
	}
}
