package com.cs.myfirst.owlapi.Features.BoilerplateCode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import owl.cs.myfirst.owlapi.app;
import owl.cs.myfirst.owlapi.Features.ClassExpressionAxiomsCategory;
import owl.cs.myfirst.owlapi.Features.DataPropertyAxiomsCategory;
import owl.cs.myfirst.owlapi.Features.ObjectPropertyAxiomCategory;

public class LastDance {
	
	public static String getRandomTillMaxTerm(String concept) throws ClassNotFoundException, IOException {
		LinkedHashMap<String, Integer> commonGlobalHashMap = Util.commonConstructs;

		if ( commonGlobalHashMap.containsKey(concept) && !CommonFramework.isIsLiteral(concept) 
				&& !CommonFramework.isIsNumber(concept) ) {
			
			int count = commonGlobalHashMap.get(concept);
			String value = "";
			if ( count == 1 ) value = concept;
			else {
				Random r = new Random();
				int low = -1;
				int high = count;
				int result = r.nextInt(high-low) + low;

				if ( result != -1 ) value = concept+"_"+String.valueOf(result);
				else value = concept;
//				System.out.println(value+" || "+result+" || "+count+" || "+concept);
			}
			return value;
		} else return concept;
	}
	
	/**
	 * @param fileName - fileName to read AXIOMS from
	 * @param constructName - name Of Construct , could any of these values -> "Domain/Range/SubClass/SubPropertyOf"
	 * 
	 * Since all these constructs are Three Worded Axioms Of Format :-
	 * Subject "Domain/Range/SubClass/SubPropertyOf/DisjointWith,Object,Data" Object
	 * 
	 * Hence, we are making HashMap For Each Construct with Key->Subject | Value->Object in "constructAxiomsHashMap" method.
	 * This HashMap for each construct will be useful for further inserting into ontology.
	 * 
	 * @return that same HashMap with Key -> Subject | Value -> Object
	 */
	public static LinkedHashMap<String,String> constructAxiomsHashMap(String fileName,String constructName) throws IOException{
		LinkedHashMap<String,String> axiomsHashmap = new LinkedHashMap<String,String>();
		ArrayList<String> axioms = TextFileProcessor.readTxtFile(fileName,constructName);
		for ( String ax : axioms ) {
			String[] contents = ax.split(",");
			axiomsHashmap.put(contents[0],contents[1]);
		}
		return axiomsHashmap;
	}
	
	
	/**
	 * @param globalHashMap -> globalHashMap containing all terms/concept/properties, could be commonConstructs OR underscoreCommonConstructs
	 * @param constructHashMap -> HashMap with Key -> Subject | Value -> Object generated from above "constructAxiomsHashMap" method
	 * @param constructName -> name Of Construct , could any of these values something like -> "~Domain.txt/~Range.txt/~SubClass.txt/~SubPropertyOf.txt, ~ can be Data/Object something"
	 * @param whether -> if TRUE that means globalHashMap is underscoreCommonConstructs ( with underscore ) else it is commonConstructs.
	 * 
	 * constructName = constructName.substring(0,constructName.length()-4); -> removes ".txt" from end of constructName
	 * We iterate over globalHashMap
	 * if "whether" == true, then it means terms CONTAINS "_" in it, remove that part.
	 * We remove "_" part because , in Each Construct Axioms Txt, We dont have ANY TERM with "_" written. 
	 * Similarly, in constructHashMap, We will have ALL keys and values with term WITHOUT any underscore.
	 * 
	 * Suppose Axiom is :- Employee SubClassOf Person.
	 * Suppose Term is Employee_12, then how could find Object in this statement. Hence we REMOVE that to get Employee, then we can easily find its Object. 
	 * 
	 * -> Lets give one example each from commonConstructs globalHashmap, "whether" value = FALSE. 
	 * 	  While iterating, we get term "dislikes".
	 * 	  Its corresponding axiom is "dislikes domain Person" ( from ObjectDomain ). constructHashMap's Key -> dislikes and constructHashMap's Value -> Person
	 *    In below "for loop:, the variable values will be :-
	 * 	  String key -> "dislikes" , 
	 *    String obj -> "dislikes", 
	 *    String sub -> "Person" , from constructHashMap's corresponding value key value.
	 * 	  
	 * -> Lets give one example each from underscoreCommonConstructs globalHashmap, "whether" value = TRUE. 
	 * 	  While iterating, we get term "dislikes_12".
	 * 	  Its corresponding axiom is "dislikes domain Person" ( from ObjectDomain ). constructHashMap's Key -> dislikes and constructHashMap's Value -> Person, SAME in both case.
	 *    In below "for loop:, the variable values will be :-
	 * 	  String key -> "dislikes_12" , 
	 *    String obj -> "dislikes", 
	 *    String sub -> "Person_12" , from constructHashMap's corresponding value key value + appending _12.
	 *    
	 * Now, if globalHashMap contains "dislikes" , then we will INSERT it INTO ONTOLOGY.
	 * With 'subject as variable value "key"' and 'object as variable value "sub"'
	 * Then we call respective ConstructName and INSERT into ONTOLOGY.
	 */
	public static void addGlobalToOntologyDomainRange(LinkedHashMap<String,String> constructHashMap, 
			String constructName, boolean whether) throws OWLOntologyCreationException, NoSuchMethodException, SecurityException, ClassNotFoundException, OWLOntologyStorageException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		
		LinkedHashMap<String, Integer> globalHashMap = whether == true ? Util.underscoreCommonConstructs : Util.commonConstructs;
		constructName = constructName.substring(0,constructName.length()-4);
		ArrayList<String> extraConcepts = new ArrayList<String>();
		
		for ( String key : globalHashMap.keySet() ) {
			String obj = key.contains("_") ? key.substring(0,key.indexOf("_")) : key;
//			System.out.println(key+" | last domain-range ");
			if ( constructHashMap.containsKey(obj) && !app.domainRangeOnly.containsKey(key) ) {
				String value = getRandomTillMaxTerm(constructHashMap.get(obj));
				
				ArrayList<String> allConcepts = new ArrayList<String>();
				allConcepts.add(key); allConcepts.add(value);
				extraConcepts.add(allConcepts.get(0)); extraConcepts.add(allConcepts.get(1));
				
				if ( constructName.equals("RdfsObjectDomain") ) ObjectPropertyAxiomCategory.convertLineToRdfsObjectDomain(allConcepts);
				if( constructName.equals("RdfsObjectRange") ) ObjectPropertyAxiomCategory.convertLineToRdfsObjectRange(allConcepts);
				if ( constructName.equals("RdfsDataDomain") ) DataPropertyAxiomsCategory.convertLineToRdfsDataDomain(allConcepts);
				if( constructName.equals("RdfsDataRange") ) DataPropertyAxiomsCategory.convertLineToRdfsDataRange(allConcepts);
			}
		}
		for( String extra : extraConcepts ) CommonFramework.saveTermsInGlobalHashMap(extra,"third");
	}
	
	/**
	 * @param globalHashMap -> globalHashMap containing all terms/concept/properties, could be commonConstructs OR underscoreCommonConstructs
	 * @param constructHashMap -> HashMap with Key -> Subject | Value -> Object generated from above "constructAxiomsHashMap" method
	 * @param constructName -> name Of Construct , could any of these values something like -> "~Domain.txt/~Range.txt/~SubClass.txt/~SubPropertyOf.txt, ~ can be Data/Object something"
	 * @param whether -> if TRUE that means globalHashMap is underscoreCommonConstructs ( with underscore ) else it is commonConstructs.
	 * 
	 * Possible Problems in Function readFile in TextFileProcessor, 
	 * Above string sub variable , ( is underscore required )
	 * 
	 * SEE
	 * NodeSet<OWLClass> subClasses = reasoner.getSubClasses(df.getOWLThing(), true);
	 * 
	 */
	public static void addGlobalToOntologySubOf(LinkedHashMap<String,String> constructHashMap, 
			String constructName, boolean whether) throws OWLOntologyCreationException, NoSuchMethodException, SecurityException, ClassNotFoundException, OWLOntologyStorageException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		
		LinkedHashMap<String, Integer> globalHashMap = whether == true ? Util.underscoreCommonConstructs : Util.commonConstructs;

		constructName = constructName.substring(0,constructName.length()-4);
		ArrayList<String> extraConcepts = new ArrayList<String>();
		Queue<String> subOfTerms = new LinkedList<String>(globalHashMap.keySet());
//		for ( String keys : globalHashMap.keySet() ) subOfTerms.add(keys);
		
		while(!subOfTerms.isEmpty()) {
			String item = subOfTerms.poll();
			String key = item.contains("_") ? item.substring(0,item.indexOf("_")) : item;
//			System.out.println(item+" | last sub ");
			if ( constructHashMap.containsKey(key) && !app.rdfsSubsOnly.containsKey(item) ) {				
				String object = getRandomTillMaxTerm(constructHashMap.get(key));
				
				if ( !item.equals(object) ) {
					ArrayList<String> allConcepts = new ArrayList<String>();
					allConcepts.add(item); allConcepts.add(object);
					
					if ( constructName.equals("RdfsObjectSubPropertyOf") ) ObjectPropertyAxiomCategory.convertLineToRdfsObjectSubPropertyOf(allConcepts);
					if( constructName.equals("RdfsDataSubPropertyOf") ) DataPropertyAxiomsCategory.convertLineToRdfsDataSubPropertyOf(allConcepts);
					if( constructName.equals("RdfsSubClassOfFeature") ) ClassExpressionAxiomsCategory.convertLineToRdfsSubClassOf(allConcepts);					
					
					key = object.contains("_") ? object.substring(0,object.indexOf("_")) : object;
					if ( constructHashMap.containsKey(key) ) {
						subOfTerms.add(object);
						extraConcepts.add(allConcepts.get(0)); extraConcepts.add(allConcepts.get(1));
					}
				}
			}
		}
		for( String extra : extraConcepts ) CommonFramework.saveTermsInGlobalHashMap(extra,"third");
	}
	
	/**
	 * Here are transforming Concepts/Properties/Terms of globalHashMap, both "commonConstructs" and "underscoreCommonConstructs"
	 * And adding Domain/Range/SubClass/SubPropertyOf of both Object/Data corresponding axioms INTO ONTOLOGY.
	 * 
	 * Since all these constructs are Three Worded Axioms Of Format :-
	 * Subject "Domain/Range/SubClass/SubPropertyOf" Object
	 * 
	 * Hence, we are making HashMap For Each Construct with Key->Subject | Value->Object in "constructAxiomsHashMap" method.
	 * This HashMap for each construct will be useful for further inserting into ontology.
	 * 
	 * addGlobalToOntologyDomainRangeDisjointWith adds Domain/Range for both globalHashMaps.
	 * Similarly
	 * addGlobalToOntologySubOf adds SubClass/SubProperty of both globalHashMaps.
	 * 
	 * At the VERY END of "generateOntology" ,method in app.java JUST BEFORE WRITING in OWL file.
	 * 
	 * 
	 *if user seletced then  Run domain/range AT THE LAST ONLY,
	 *not during IN BETWWEN due to RANK logic.
	 *Whenever user has inputted domain/range only then only generate.
	 *
	 *
	 *Then subclass mandatory at the end for ALL.
	 *
	 *At the END , after running Some of the constructs, to add domain-range axioms for globalHashmap terms.
	 *suppose following terms , comes in globalHashMap "hasFullProfessor_0", "hasFullProfessor_1"
	 *and its corresponding axiom is "hasFullProfessor range FullProfessor"
	 *
	 *What will be axioms for aboves terms :-
	 *if hasFullProfessor_0 has already been used , present in globalHashmap then.
	 *	hasFullProfessor_0 range FullProfessor_0 
	 *else
	 *  hasFullProfessor_0 range FullProfessor ( to prevent making EXTRA classes )
	 *  
	 *Similarly for hasFullProfessor_1
	 *
	 *Is this fine ???
	 *
	 *Jaise classes ke ROOT concepts hain , ussi tarah objectProperties/dataProperties ke bhi root properties etc. hain kya ??
	 *If yes toh please, iss txt file main alag alag line main add karke dedo please.
	 *
	 *Jaise domain/range ko BILKUL end main call kar rhe hain if user has inputted, ussi taraf subClass/subProperty ko bilkul END main call karna hain if user has inputted ??
	 *
	 */
	public void oneLastTime() throws ClassNotFoundException, IOException, OWLOntologyCreationException, NoSuchMethodException, SecurityException, OWLOntologyStorageException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String[] files = {"RdfsSubClassOfFeature.txt","RdfsDataSubPropertyOf.txt","RdfsObjectSubPropertyOf.txt",
				"RdfsObjectDomain.txt","RdfsObjectRange.txt","RdfsDataDomain.txt","RdfsDataRange.txt"};
		String[] constructs = {" SubClassOf "," subpropertyof "," SubPropertyOf ",
				" domain "," range "," domain "," range "};
		
		for ( int i = 0 ; i < files.length ; i++ ) {
//			System.out.println(files[i]+" || "+constructs[i]);
			LinkedHashMap<String,String> constructHashMap = constructAxiomsHashMap(files[i],constructs[i]);
			if ( i >= 3 ) {
					addGlobalToOntologyDomainRange(constructHashMap,files[i],false);
					addGlobalToOntologyDomainRange(constructHashMap,files[i],true);			
			} else {
				addGlobalToOntologySubOf(constructHashMap,files[i],false);
				addGlobalToOntologySubOf(constructHashMap,files[i],true);
			}
		}
	}
}
