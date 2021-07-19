package com.cs.myfirst.owlapi.Features.BoilerplateCode;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FilenameConstructMapping {
	public static HashMap<String,String> fileMap;
	public static HashMap<String,String> separatorMap ;
	public static HashMap<String,String> constructsForLast;
	public static HashMap<String,String> ontologyTerm;
	public static HashMap<String,String> lastDomainRangeDisjoint;
	
//	public static void main( String [] args ) throws IOException {
	public void mapping() throws IOException {
		 String[] construct1 = {"OwlClass","ObjectComplement","ObjectIntersection","ObjectOneOf","ObjectUnionOf"};
		 String[] filename1 = {"OwlClass.txt","OwlObjectComplementFeature.txt","OwlObjectIntersectionFeature.txt","OwlObjectOneOfFeature.txt","OwlObjectUnionOfFeature.txt"};
		 String[] separator1 = {"\\r?\\n"," complementOf "," IntersectionOf "," OneOf "," UnionOf "};
		 String[] generatedOntologyTerm1 = {"","ObjectComplementOf","ObjectIntersectionOf","ObjectOneOf","ObjectUnionOf"};
		 
		 String[] construct2 = {"DisjointUnion","DisjointWith","EquivalentClass","RdfsSubClassOf"}; //"AllDisjointClasses",
		 String[] filename2 = {"OwlDisjointUnionFeature.txt","OwlDisjointWithFeature.txt","OwlEquivalentClassFeature.txt","RdfsSubClassOfFeature.txt"}; //"OwlAllDisjointClassesFeature.txt",
		 String[] separator2 = {" DisjointUnionOf "," disjointWith "," EquivalentClasses "," SubClassOf "}; //" , ",
		 String[] generatedOntologyTerm2 = {"DisjointUnion","DisjointClasses-DisjointWith","EquivalentClasses","SubClassOf"}; //"DisjointClasses",
		 
		 String[] construct3 = {"AsymmetricProperty","EquivalentObjectProperty","FunctionalObjectProperty",
				  "InverseFunctionalProperty","InverseOfProperty","IrreflexiveProperty",
				  "ObjectPropertyDisjointWith","ReflexiveProperty","SymmetricProperty",
				  "TransitiveProperty","RdfsObjectDomain","RdfsObjectRange","PropertyChainAxiom",
				  "RdfsObjectSubPropertyOf","ObjectProperty"};  //"AllDisjointObjectProperties",
		 String[] filename3 = {"OwlAsymmetricProperty.txt","OwlEquivalentObjectProperty.txt","OwlFunctionalObjectProperty.txt",
				 "OwlInverseFunctionalProperty.txt","OwlInverseOfProperty.txt","OwlIrreflexiveProperty.txt",
				 "OwlObjectPropertyDisjointWith.txt","OwlReflexiveProperty.txt","OwlSymmetricProperty.txt",
				 "OwlTransitiveProperty.txt","RdfsObjectDomain.txt","RdfsObjectRange.txt","OwlPropertyChainAxiomFeature.txt",
				 "RdfsObjectSubPropertyOf.txt","OwlObjectProperty.txt"}; //"AllDisjointObjectProperties.txt",
		 String[] separator3 = {" Asymmetric"," equivalent "," Functional",
				 " inverse"," inverseOf "," irreflexive",
				 " disjointWithObject "," reflexive"," Symmetrical",
				 " Transitive"," domain "," range "," , ",
				 " SubPropertyOf "," "}; //" disjointObject ",
		 String[] generatedOntologyTerm3 = {"AsymmetricObjectProperty","EquivalentObjectProperties","FunctionalObjectProperty",
				 "InverseFunctionalObjectProperty","InverseObjectProperties","IrreflexiveObjectProperty",
				 "DisjointObjectProperties-DisjointWith","ReflexiveObjectProperty","SymmetricObjectProperty",
				 "TransitiveObjectProperty","ObjectPropertyDomain","ObjectPropertyRange","SubObjectPropertyOfObjectPropertyChain",
				 "SubObjectPropertyOf",""}; //"DisjointObjectProperties",
		 
		 String[] construct4 = {"ObjectAllValuesFrom","ObjectHasSelf","ObjectHasValue","ObjectSomeValuesFrom","ObjectQualifiedCardinality","ObjectMaxQualifiedCardinality","ObjectMinQualifiedCardinality"};
		 String[] filename4 = {"OwlObjectAllValuesFromFeature.txt","OwlObjectHasSelfFeature.txt","OwlObjectHasValueFeature.txt","OwlObjectSomeValuesFromFeature.txt","OwlObjectQualifiedCardinalityFeature.txt","OwlObjectMaxQualifiedCardinalityFeature.txt","OwlObjectMinQualifiedCardinalityFeature.txt"};
		 String[] separator4 = {" "," "," "," "," "," "," "};
		 String[] generatedOntologyTerm4 = {"ObjectAllValuesFrom","ObjectHasSelf","ObjectHasValue","ObjectSomeValuesFrom","ObjectExactCardinality","ObjectMaxCardinality","ObjectMinCardinality"};
		 
		 String[] construct5 = {"EquivalentDataProperty","FunctionalDataProperty","DataPropertyDisjointWith","RdfsDataDomain","RdfsDataRange","RdfsDataSubPropertyOf"}; //"AllDisjointDataProperties",
		 String[] filename5 = {"EquivalentDataProperty.txt","OwlFunctionalDataProperty.txt","DataPropertyDisjointWith.txt","RdfsDataDomain.txt","RdfsDataRange.txt","RdfsDataSubPropertyOf.txt"}; //"AllDisjointDataProperties.txt",
		 String[] separator5 = {" EquivalentTo "," Functional"," disjointWithData "," domain "," range "," subpropertyof "}; //" disjointData ",
		 String[] generatedOntologyTerm5 = {"EquivalentDataProperties","FunctionalDataProperty","DisjointDataProperties-DisjointWith","DataPropertyDomain","DataPropertyRange","SubDataPropertyOf"}; //"DisjointDataProperties",
		 
		 String[] construct6 = {"DataAllValues","DataHasValue", "DataMaxQualifiedCardinality","DataMinQualifiedCardinality","DataQualifiedCardinality","DataSomeValuesFrom"};
		 String[] filename6 = {"DataAllValues.txt","DataHasValue.txt", "DataMaxQualifiedCardinality.txt","DataMinQualifiedCardinality.txt","DataQualifiedCardinality.txt","DataSomeValuesFrom.txt"};
		 String[] separator6 = {" "," "," "," "," "," "};
		 String[] generatedOntologyTerm6 = {"DataAllValuesFrom","DataHasValue","DataMaxCardinality","DataMinCardinality","DataExactCardinality","DataSomeValuesFrom"};
		 
		 String[] construct7 = {"DataComplementOf","DataIntersectionOf", "DataOneOf","DataUnionOf"};
		 String[] filename7 = {"DataComplementOf.txt","DataIntersectionOf.txt","DataOneOf.txt","DataUnionOf.txt"};
		 String[] separator7 = {" complementOf "," IntersectionOf "," OneOf "," UnionOf "};
		 String[] generatedOntologyTerm7 = {"DataComplementOf","DataIntersectionOf","DatatypeDefinition","DataUnionOf"};
	
		 String[] construct10 = {"HasKey"};
		 String[] filename10 = {"OwlHasKey.txt"};
		 String[] separator10 = {" HasKey "};
		 String[] generatedOntologyTerm10 = {"HasKey"};
		 
		 String[][] construct = {construct1,construct2,construct3,construct4,construct5,construct6,construct7,construct10}; 
		 String[][] filename = {filename1,filename2,filename3,filename4,filename5,filename6,filename7,filename10}; 
		 String[][] separator = {separator1,separator2,separator3,separator4,separator5,separator6,separator7,separator10}; 
		 String[][] generatedOntologyTerm = {generatedOntologyTerm1,generatedOntologyTerm2,generatedOntologyTerm3,generatedOntologyTerm4,
				                generatedOntologyTerm5,generatedOntologyTerm6,generatedOntologyTerm7,generatedOntologyTerm10}; 
		 
		fileMap = new HashMap<String,String>();
		separatorMap = new HashMap<String,String>();
		ontologyTerm = new HashMap<String,String>();
		
		 for ( int i = 0 ; i < construct.length ; i++ ) {
			 //System.out.println(construct[i].length+" | "+filename[i].length+" | "+construct[i].length);
			 for( int j = 0 ; j < construct[i].length ; j++ ) {
				 fileMap.put(construct[i][j], filename[i][j]);
				 separatorMap.put(construct[i][j], separator[i][j]);
				 ontologyTerm.put(generatedOntologyTerm[i][j],construct[i][j]);
			 }
		 }
		 
		String[] lastOnesConstructs = { "RdfsSubClassOf","RdfsDataSubPropertyOf","RdfsObjectSubPropertyOf",
				"RdfsObjectDomain","RdfsObjectRange","RdfsDataDomain","RdfsDataRange",
	            "DisjointWith","ObjectPropertyDisjointWith","DataPropertyDisjointWith" };
		
		//subClass/subProper all subs -> reason -> all hierarchy get printed/more connected -> MANDATORY, if user selects or NOT
		//domain/range -> NOT MANDATORY , if user HAS SELECTED then ONLY
		//all disjoint ones -> NOT MANDATORY , if user HAS SELECTED then ONLY , reason -> more relations one
		// REMOVE all disjoint one, then use disjointWith ONLY
		constructsForLast = new HashMap<String,String>();
		for( String dr : lastOnesConstructs ) constructsForLast.put(dr,"");
		
		String[] constructsDomainRangeDisjoint = {  "RdfsObjectDomain.txt","RdfsObjectRange.txt","RdfsDataDomain.txt","RdfsDataRange.txt",
				  "OwlObjectPropertyDisjointWith.txt","DataPropertyDisjointWith.txt","OwlDisjointWithFeature.txt" };
		lastDomainRangeDisjoint = new HashMap<String,String>();
		for( String dr : constructsDomainRangeDisjoint ) lastDomainRangeDisjoint.put(dr,"");
	}
	
	
	/**
	 * Can Be IGNORED, DONT BE AFRAID, IF YOU DONT GET IT
	 * 
	 * What we are basically doing here making a List Of Constructs, 
	 * Where we have written GENERIC Code to insert into OWL API. 
	 * These are all those constructs.
	 * 
	 * @return
	 */
	public static ArrayList<String> disjointOnes() {
		ArrayList<String> genericConstructs = new ArrayList<String>();
		Collections.addAll(genericConstructs,"DisjointUnionOf",
				"ObjectIntersection","ObjectOneOf","ObjectUnionOf");
		
		// they wont come "ObjectPropertyDisjointWith","DataPropertyDisjointWith","EquivalentClass","EquivalentDataProperty","EquivalentObjectProperty",
		// LAST MAIN EVEN AFTER SUBCLASS/SUBPROP -> All Disjoint With CONTAINING "Disjoint" ONLY NAMES ( not including oneOf, intersection, union ), 

		return genericConstructs;
	}
	// 'empty role chain' 
	// not enough elements in the n-ary element
	// Index 1 out of bounds for length 1 -objectoneof, objectunionof, datacomplementof, objectIntersectionOf, alldisjointDataProperties,
	//     alldisjointClasses,disjointUnion,disjointWith,propertyChainAxiom, dataintersectionof, dataunionof, equivalentDataProperty, equivalentObjectProperty
	// org.semanticweb.owlapi.reasoner.ReasonerInternalException: Unsupported expression 'empty role chain' in transformation
	//                              equivalentclasss, dataPropertyDisjointWith
}
