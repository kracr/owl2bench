package com.cs.myfirst.owlapi.Features.BoilerplateCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TextFileProcessor {
	
	/**
	 * @param fileName - fileName of Construct to read AXIOMS from.
	 * @param construct - constructName
	 * @return Arraylist of all AXIOMS/lines present in that particular file
	 * 
	 * Here I am reading WHOLE LINE and breaking them into WORDS.
	 * Then I am REPLACING the SPACE between each word with a COMMA.
	 * 
	 * For examples, for these 3 axioms :-
	 * Dean isHeadOf some College
	 * Chair isHeadOf some Department
	 * Director isHeadOf some Program
	 * 
	 * ArrayList contains as follows :-
	 * 1st Index (String) - Dean,isHeadOf,some,College
	 * 2nd Index (String) - Chair,isHeadOf,some,Department
	 * 3rd Index (String) - Director,isHeadOf,some,Program
	 * 
	 * The reason for this is bcz it becomes easier to NEXT steps to store/rank or any other operation. 
	 * You will get to see.
	 * 
	 * NO NEED TO GO INTO FURTHER DETAILS OF THIS FUNCTION
	 * 
	 * I have made a GENERIC function to READ ANY CONSTRUCT txt file.
	 * There are generally 4 TYPES of : - 
	 * 
	 * 1) - Single Pattern 
	 * Of format :- "property/concept CONSTRUCT"
	 * Examples:-	propertyName asymmetric, propertyName reflexive 
	 * 
	 * 2) - Three Worded Axiom
	 * Of format :- "subject CONSTRUCT object"
	 * Examples:- employee SUBCLASS person, man DSIJOINT woman
	 * 
	 * 3) - Four Worded Axioms
	 * Of format :- "subject property 'some/all' object'
	 * Examples:- Dean isHeadOf some College
	 * 
	 * 4) - Cardinality Axioms
	 * Of format :- "subject property 'max/min' number object"
	 */
	public static ArrayList<String> readTxtFile(String fileName, String construct) throws IOException {
		String constructpath = "/DL/"+fileName;
		ArrayList<String> axioms = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+constructpath));
		String line = reader.readLine();
		while (line != null) {
			if ( line.length() > 0 ) {
				String[] outputs = line.split(construct);
				if ( outputs.length == 1 ) axioms.add(outputs[0]);
				else if ( outputs.length == 2 ) {
					if ( outputs[1].contains(",") ) {
						String[] suboutputs = null;
						String ax = null;
//						if ( fileName.equals("OwlAllDisjointClassesFeature.txt") ||
//								fileName.equals("OwlPropertyChainAxiomFeature.txt") ) {
//							suboutputs =outputs;
//							ax = "";
//						}
						suboutputs = outputs[1].split(",");
						ax = outputs[0]+",";
						for ( int i = 0 ; i < suboutputs.length ; i++ ) {
							if ( suboutputs[i].length() > 0 ) ax = ax+suboutputs[i]+",";
						}
						ax = ax.substring(0,ax.lastIndexOf(","));
						axioms.add(ax);
					}
					else axioms.add(outputs[0]+","+outputs[1]);
				}
				else if ( outputs.length == 5 ) axioms.add(outputs[0]+","+outputs[1]+","+outputs[3]+","+outputs[4]);
				else if ( outputs.length >=3 ) {
					if ( fileName.equals("OwlPropertyChainAxiomFeature.txt") 
							|| fileName.equals("OwlObjectHasValueFeature.txt") || fileName.equals("DataHasValue.txt") ) {
						axioms.add(outputs[0]+","+outputs[1]+","+outputs[2]);
					}
					else axioms.add(outputs[0]+","+outputs[1]+","+outputs[3]);
				}
				//System.out.println(outputs[0]+","+outputs[1]);
			}
			line = reader.readLine();
		}
		reader.close();
		return axioms;
	}
	
	/**
	 * @param axioms - ArrayList of axioms, where at each index , we have comma separated words
	 * 
	 * Here, we are iterating over each AXIOM and calculating rank of each axiom
	 * For rank calculation, we are calculating occurrence of words in globalHashmap ( See line 129 ), 
	 * If a term of axiom occurs in globalHashmap, then increment its count.
	 * 
	 * Likewise we have calculated rank of ALL AXIOMS and then we are SORTING in DESCENDING ORDER.
	 * That is Axioms which have higher rank will be present in initial indexes and so.
	 * LinkedHashMap will have following items in order :-
	 * { AxiomsString , 12 } , { AxiomString 10 } , { AxiomString 8 }.......... { AxiomString 3 }, { AxiomString 1 }, { AxiomString 1 }
	 * 
	 * @return Sorted LinkedHashMap with values sorted in DESCENDING order
	 */
	public ArrayList<String> rankAxioms(ArrayList<String> axioms) throws IOException, ClassNotFoundException {
		LinkedHashMap<String, Integer> globalCommons = Util.commonConstructs;
		LinkedHashMap<String,Integer> axiomsRank = new LinkedHashMap<String,Integer>();
		for ( String ax : axioms ) {
			String[] contents = ax.split(",");
			int rank = 0;
			for ( int i = 0 ; i < contents.length ; i++ ) {
				if ( !CommonFramework.isIsNumber(contents[i]) && !CommonFramework.isIsLiteral(contents[i]) && globalCommons.containsKey(contents[i]) ) rank++;
			}
			axiomsRank.put(ax,rank);
		}
		LinkedHashMap<String, Integer> rankedMap = axiomsRank.entrySet().stream()
		.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		
		ArrayList<String> rankedAxioms = rankedMap.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
//		for ( String kk : rankedMap.keySet() ) System.out.println(kk+" | "+rankedMap.get(kk));
		return rankedAxioms;
	}

//		underscoreGlobalCommons.put(contents[i]+"_"+incre, incre+1);
//		if ( globalCommons.get(contents[i]) < incre+1 ) globalCommons.put(contents[i], incre+1);
//if ( phase.equals("first") ) globalCommons.put(contents[i], 1);
}
