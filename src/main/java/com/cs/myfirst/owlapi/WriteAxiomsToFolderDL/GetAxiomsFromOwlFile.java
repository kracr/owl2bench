package com.cs.myfirst.owlapi.WriteAxiomsToFolderDL;

import owl.cs.myfirst.owlapi.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataCardinalityRestriction;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectCardinalityRestriction;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLQuantifiedDataRestriction;
import org.semanticweb.owlapi.model.OWLQuantifiedObjectRestriction;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.util.OWLEntityRenamer;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.FilenameConstructMapping;

public class GetAxiomsFromOwlFile {
	
	static String totriple(String test) {
		test = test.replace("#", "/");
		test = test.replace("<http://benchmark/OWL2Bench/", " ");
		test = test.replace(">", "");
		test = test.replace("(", "");
		test = test.replace(")", "");
		test = test.replace("^^", " ");
		test = test.replace("  ", " ");
		String result = test.replaceAll("\\s+$", "");
		return result;
	}
	
	public static String[] refine(String[] arr, String ok) {
		String[] result = Arrays.copyOfRange(arr, 1, arr.length);
		List<String> list = new ArrayList<String>(Arrays.asList(result));
		list.remove(ok);
		result = list.toArray(new String[0]);
		return result;
	}

	 public static void genericWriteToFile(String[] arr, String separator, String fileName) throws IOException {
		
		String singleLine = "";
		if ( arr.length > 2 ) {
			for( int i = 0 ; i < arr.length ; i++ ) {
				if ( i == arr.length-1 ) {
					singleLine = singleLine+arr[i];
				} else {
					singleLine = singleLine+arr[i]+separator;
				}
			}	
		} else if ( arr.length == 2 ) {
			singleLine = arr[0]+separator+arr[1];
		} else {
			singleLine = arr[0]+separator;
		}
		
//		System.out.println(singleLine);
//		System.out.println();
		
		File file = new File(System.getProperty("user.dir")+"/DLTemp/"+fileName);
		FileWriter fr = new FileWriter(file, true);
		fr.write(singleLine+"\n");
		fr.close();
		
	 }
	 
	 //Axiom Count of construct
	 //Axioms Written By Me :- 
	 //DataAllValues , DataComplement, DataHasVAlue, DataIntersectionOf, DataMaxQualified, DataMinQualified,
	 //DataOneOf, DataQualified, DataSomeValues, DataUnionOf, EquivalentData, OwlEquivalentClass ( Added extra term at end of each axiom ),
	 //OwlEquivalentObjectProperty ( Added extra term at end of each axiom ) , ObjectHasValue , ObjectIntersectionOf, ObjectOneOf,
	 //ObjectProperty, ObjectUnionOf, 
	 //
	 //ObjectProperty, OwlClass ( WRONG ), DataDomain ( WRONG )
	 
	static void addtoFile(String[] arr) throws IOException{
		FilenameConstructMapping filenameSepartor = new FilenameConstructMapping();
		filenameSepartor.mapping();
		
		//[SubDataPropertyOf, hasCode, owl:topDataProperty]
		//[SubClassOf, Person4, ObjectUnionOf, Man4]
		//[SubClassOf, NonHumanitiesAndSocialScience, ObjectComplementOf, HumanitiesAndSocialScience]
		
		//[EquivalentClasses, Person4, ObjectIntersectionOf, Man4, Woman4]
		//[EquivalentClasses, DataOneOf3, ObjectOneOf, dt3.1, dt3.2, dt3.3]
		//[EquivalentClasses, SelfAwarePerson, ObjectHasSelf, knows]
		//[SubObjectPropertyOfObjectPropertyChain, enrollIn, isPartOf, isMemberOf]
		
		//[DataPropertyRange, dt3.1, DataUnionOf, dt3.2, dt3.3]
		//[DataPropertyRange, dataComplementOfProperty3, DataComplementOfxsd:string]
		//[DataPropertyRange, dt3.1, DataIntersectionOf, dt3.2, dt3.3]
		//[DatatypeDefinition, DataOneOf3, DataOneOf"dt3.1", xsd:string, "dt3.2", xsd:string, "dt3.3", xsd:string]
		
		//[ObjectPropertyRange, property3, range3]
		//[DataPropertyRange, dataComplementOfProperty4, DataComplementOfxsd:string]
		
		//[DatatypeDefinition, DataOneOf4, DataOneOf"dt4.1", xsd:string, "dt4.2", xsd:string, "dt4.3", xsd:string, "dt4.4", xsd:string, "dt4.5", xsd:string]
		
		 String construct = "";
		 if ( arr[0].equals("DisjointClasses") ) { 
			if ( arr.length == 3 ) {
				 construct = FilenameConstructMapping.ontologyTerm.get(arr[0]+"-DisjointWith") != null 
							? FilenameConstructMapping.ontologyTerm.get(arr[0]+"-DisjointWith") : "";
			} else {
				 construct = FilenameConstructMapping.ontologyTerm.get(arr[0]) != null 
							? FilenameConstructMapping.ontologyTerm.get(arr[0]) : "";	
			}
		 } else if ( arr[0].equals("DisjointObjectProperties") ) {
			if ( arr.length == 3 ) {
				construct = FilenameConstructMapping.ontologyTerm.get(arr[0]+"-DisjointWith") != null 
						? FilenameConstructMapping.ontologyTerm.get(arr[0]+"-DisjointWith") : "";
			} else {
				construct = FilenameConstructMapping.ontologyTerm.get(arr[0]) != null 
						? FilenameConstructMapping.ontologyTerm.get(arr[0]) : "";	
			}
		 }  else if ( arr[0].equals("DisjointDataProperties") ) {
			if ( arr.length == 3 ) {
				construct = FilenameConstructMapping.ontologyTerm.get(arr[0]+"-DisjointWith") != null 
						? FilenameConstructMapping.ontologyTerm.get(arr[0]+"-DisjointWith") : "";
			} else {
				construct = FilenameConstructMapping.ontologyTerm.get(arr[0]) != null 
						? FilenameConstructMapping.ontologyTerm.get(arr[0]) : "";	
			}
		 }
		 else {
			 construct = FilenameConstructMapping.ontologyTerm.get(arr[0]) != null 
						? FilenameConstructMapping.ontologyTerm.get(arr[0]) : "";	 
		 }
//		 System.out.println(Arrays.toString(arr)+" || "+arr.length);
		 
		if(!arr[0].contains("Disjoint") && construct.length() != 0 && (arr.length == 3 || arr.length == 2)) {
			
			if ( arr[0].contains("DataPropertyRange") ) {
				if ( arr[arr.length-1].contains("xsd") ) { arr[arr.length-1] = arr[arr.length-1].replace("xsd:string",""); }
			}
			ArrayList<String> simpleTerms = new ArrayList<String>();

			if ( arr.length == 2 ) { simpleTerms.add(arr[1]); }
			if (arr.length == 3 ) {
				simpleTerms.add(arr[1]); 
				if ( !arr[2].equals("DataComplementOf") ) 
				{ 
					simpleTerms.add(arr[2]); 
				} else {
					construct = arr[2];
//					System.out.println(Arrays.toString(arr));	
				}
			}
			String[] finalTerms = simpleTerms.stream().toArray(String[]::new);
			
//			System.out.println(Arrays.toString(finalTerms)+" | "+construct+" || "+finalTerms.length+" || "+FilenameConstructMapping.separatorMap.get(construct));
			genericWriteToFile(finalTerms,FilenameConstructMapping.separatorMap.get(construct),FilenameConstructMapping.fileMap.get(construct));
		}
		else if(arr.length > 3 && arr[0].equals("SubClassOf") && arr[2].contains("Cardinality")) {
					
			System.out.println(Arrays.toString(arr)+" || "+arr.length);
			
			String number = arr[2].substring(arr[2].indexOf("y")+1);
			arr[2] = arr[2].substring(0,arr[2].indexOf("y")+1);
			String lastTerm = arr[arr.length-1];
			
			for ( int i = 1 ; i < arr.length ; i++ ) { arr[i-1] = arr[i]; }
			arr[arr.length-2] = number;
			if ( arr[1].contains("Object") ) {
				arr[arr.length-1] = arr[0];
				arr[0] = lastTerm;
			}
			if ( arr[1].contains("Data") ) {
				String firstTerm = arr[0];
				arr[0] = arr[2];
				arr[2] = firstTerm;
			}
			construct = FilenameConstructMapping.ontologyTerm.get(arr[1]) != null 
					? FilenameConstructMapping.ontologyTerm.get(arr[1]) : "";
			
			System.out.println(Arrays.toString(arr)+" || "+arr.length);
			
			ArrayList<String> finalTerms = new ArrayList<String>();
			if ( construct.contains("Data") ) {
				finalTerms.add(arr[2]);
				finalTerms.add(arr[0]);
			} else {
				finalTerms.add(arr[0]); finalTerms.add(arr[2]);
				if ( construct.contains("MinQualified") ) {
					finalTerms.add("min");
				} else if ( construct.contains("MaxQualified") ) { 
					finalTerms.add("max");
				} else {
					finalTerms.add("exactly");
				}
				finalTerms.add(arr[3]); finalTerms.add(arr[4]);
			}
			String[] cardinality = finalTerms.stream().toArray(String[]::new);
//			System.out.println(Arrays.toString(cardinality)+" || "+construct+" || "+cardinality.length+" || "+FilenameConstructMapping.separatorMap.get(construct));
			genericWriteToFile(cardinality,FilenameConstructMapping.separatorMap.get(construct),FilenameConstructMapping.fileMap.get(construct));
		}
		else if (arr.length > 3 && ( arr[2].contains("SomeValue") || arr[2].contains("AllValue") || arr[2].contains("HasValue") ) 
				&& (!arr[2].contains("Intersection") && !arr[2].contains("Union") && !arr[2].contains("Complement")) ) {
			construct = FilenameConstructMapping.ontologyTerm.get(arr[2]) != null 
					? FilenameConstructMapping.ontologyTerm.get(arr[2]) : "";

			if ( arr[2].equals("DataAllValuesFrom") || arr[2].equals("DataSomeValuesFrom") || arr[2].equals("DataHasValue") ) {
				String[] dataValues = {arr[3],arr[1]};
//				System.out.println(Arrays.toString(dataValues)+" || "+construct+" || "+dataValues.length+" || "+FilenameConstructMapping.separatorMap.get(construct));
				genericWriteToFile(dataValues,FilenameConstructMapping.separatorMap.get(construct),FilenameConstructMapping.fileMap.get(construct));
			}
			else if ( arr[2].equals("ObjectAllValuesFrom") || arr[2].equals("ObjectSomeValuesFrom") || arr[2].equals("ObjectHasValue") ) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(arr[1]); list.add(arr[3]);
				if ( arr[2].contains("AllValues") ) {
					list.add("only"); list.add(arr[4]);
				} else if ( arr[2].contains("SomeValues") ) {
					list.add("some"); list.add(arr[4]);				
				} else if ( arr[2].contains("HasValue") ) {
					list.add(arr[4]);
				}
				String[] objectValues = list.stream().toArray(String[]::new);
//				System.out.println(Arrays.toString(objectValues)+" || "+construct+" || "+objectValues.length+" || "+FilenameConstructMapping.separatorMap.get(construct));
				genericWriteToFile(objectValues,FilenameConstructMapping.separatorMap.get(construct),FilenameConstructMapping.fileMap.get(construct));
			} 
		}
		else if ( ( arr[0].equals("EquivalentClasses") && arr.length > 3 ) || 
				( arr[0].equals("SubClassOf") && (arr[2].contains("ComplementOf") || arr[2].contains("UnionOf")) ) ||
				( arr[0].contains("DataProperty") && arr.length > 3 ) ) {
			construct = FilenameConstructMapping.ontologyTerm.get(arr[2]) != null 
					? FilenameConstructMapping.ontologyTerm.get(arr[2]) : "";
			
			String secondTerm = "";
			for( int i = 3 ; i < arr.length ; i++ ) {
				secondTerm = secondTerm+arr[i]+",";
			}
			secondTerm = secondTerm.substring(0, secondTerm.length()-1);
			String[] equivalentValues = {arr[1],secondTerm};
//			System.out.println(Arrays.toString(equivalentValues)+" || "+construct+" || "+equivalentValues.length+" || "+FilenameConstructMapping.separatorMap.get(construct));
			genericWriteToFile(equivalentValues,FilenameConstructMapping.separatorMap.get(construct),FilenameConstructMapping.fileMap.get(construct));
			System.out.println();
		}
		else {
			//AllDisjointDataProperties, AllDisjointClasses, AllDisjointObjectProperties, DisjointUnion, DataPropertyDisjointWith, ObjectPropertyDisjointWith, DataOneOf, DisjointWith
			String secondTerm = "";
			for ( int i = 2 ; i < arr.length ; i++ ) {
				if ( !arr[i].contains("xsd:") ) {
					secondTerm = secondTerm+arr[i]+",";
				}
			}
			if ( secondTerm.contains("\"") ) {
				secondTerm = secondTerm.replace("\"", "");
				secondTerm = secondTerm.replace("DataOneOf", "");
			}
			secondTerm = secondTerm.substring(0, secondTerm.length()-1);
			String[] disjointValues = {arr[1],secondTerm};
//			System.out.println(Arrays.toString(disjointValues)+ " || "+construct+" || "+disjointValues.length+" || "+FilenameConstructMapping.separatorMap.get(construct));
			genericWriteToFile(disjointValues,FilenameConstructMapping.separatorMap.get(construct),FilenameConstructMapping.fileMap.get(construct));
		}
//		System.out.println();
	}
	
	public static void clearContentOfFiles() throws FileNotFoundException {
		File DL = new File(System.getProperty("user.dir")+"//DLTemp//");
		if(DL.isDirectory()){
			String[] subNote = DL.list();
			for(String filename : subNote){
				if ( filename.contains(".txt") ) {
					PrintWriter writer = new PrintWriter(System.getProperty("user.dir")+"//DLTemp//"+filename);
					writer.print("");
					writer.close();
				}
			}
		}
	}
	
	public static void main( String[] args ) throws OWLOntologyCreationException, IOException
//	public static void getAxiomFromOntology(String filePath) throws OWLOntologyCreationException, IOException
    {
		clearContentOfFiles();
		
        String filePath = "UNIV-BENCH-OWL2DL.owl";
//        String filePath = "userInputCountOntology.owl";
        
        String ontoResult = usingBufferedReader(filePath);
    	OWLOntologyManager man = OWLManager.createOWLOntologyManager();
    	OWLOntology ontology = man.loadOntologyFromOntologyDocument(new StringDocumentSource(ontoResult));
    	
    	Set<OWLAxiom> axioms = ontology.getAxioms();
    	for (OWLAxiom ax : ontology.getAxioms()) {
    		String test = ax.toString();
    		String result = totriple(test);
    		String[] splited = result.split("\\s+");
//    		System.out.println(Arrays.toString(splited));
//    		System.out.println(test+" || test ");
//    		System.out.println(result+" || result ");
//    		if( splited[0].equals("DeclarationClass") && !splited[1].contains("Data") ) {
////    			System.out.println(Arrays.toString(splited));
//    			String[] classesName = {splited[1]};
//    			genericWriteToFile(classesName,"","OwlClass.txt");
//    		}
    		if(splited[0].contains("Declaration") || (splited[0].contains("Equivalent") && splited.length == 3)) {
    			continue;
    		} 
//    		else {
//    			addtoFile(splited);
//    		}
    		
    		System.out.println(Arrays.toString(splited));
    		System.out.println();
    	}
    	
    	System.out.println();
    	
    	/*
    	Set<OWLEquivalentObjectPropertiesAxiom> equivalentObjectProps = ontology.getAxioms(AxiomType.EQUIVALENT_OBJECT_PROPERTIES);
    	HashMap<String,ArrayList<String>> equivalentObjectConcepts = new HashMap<String,ArrayList<String>>();
    	ArrayList<String> equivalentObjectTerms;
    	for (OWLAxiom ax : equivalentObjectProps ) {
    		String test = ax.toString();
    		String result = totriple(test);
    		String[] splited = result.split("\\s+");
    		
    		if ( equivalentObjectConcepts.containsKey(splited[1]) ) {
    			equivalentObjectTerms = equivalentObjectConcepts.get(splited[1]);
    		} else {
    			equivalentObjectTerms = new ArrayList<String>();
    		}
    		equivalentObjectTerms.add(splited[2]);
    		equivalentObjectConcepts.put(splited[1],equivalentObjectTerms);
    	}
    	for ( String concept : equivalentObjectConcepts.keySet() ) {
    		ArrayList<String> terms = equivalentObjectConcepts.get(concept);
    		terms.add(0, "EquivalentObjectProperties");
    		terms.add(1, concept);
    		String[] arr = terms.toArray(new String[terms.size()]);
//    		System.out.println(Arrays.toString(arr)+"  || equivalent object || "+concept);
    		addtoFile(arr);
    	}
    	
    	Set<OWLEquivalentDataPropertiesAxiom> equivalentDataProps = ontology.getAxioms(AxiomType.EQUIVALENT_DATA_PROPERTIES);
    	HashMap<String,ArrayList<String>> equivalentDataConcepts = new HashMap<String,ArrayList<String>>();
    	ArrayList<String> equivalentDataTerms;
    	for (OWLAxiom ax : equivalentDataProps ) {
    		String test = ax.toString();
    		String result = totriple(test);
//    		System.out.println(result+" || equivalent data ");
    		String[] splited = result.split("\\s+");
    		if ( equivalentDataConcepts.containsKey(splited[1]) ) {
    			equivalentDataTerms = equivalentDataConcepts.get(splited[1]);
    		} else {
    			equivalentDataTerms = new ArrayList<String>();
    		}
    		equivalentDataTerms.add(splited[2]);
    		equivalentDataConcepts.put(splited[1],equivalentDataTerms);
    	}
    	for ( String concept : equivalentDataConcepts.keySet() ) {
    		ArrayList<String> terms = equivalentDataConcepts.get(concept);
    		terms.add(0, "EquivalentDataProperties");
    		terms.add(1, concept);
    		String[] arr = terms.toArray(new String[terms.size()]);
//    		System.out.println(Arrays.toString(arr));
    		addtoFile(arr);
    	}
    	
    	Set<OWLEquivalentClassesAxiom> equivalentClassProps = ontology.getAxioms(AxiomType.EQUIVALENT_CLASSES);
    	HashMap<String,ArrayList<String>> equivalentClassConcepts = new HashMap<String,ArrayList<String>>();
    	ArrayList<String> equivalentClassTerms;
    	for (OWLAxiom ax : equivalentClassProps ) {
    		String test = ax.toString();
    		String result = totriple(test);
    		String[] splited = result.split("\\s+");
    		if ( splited.length == 3 ) {
//        		System.out.println(result+" || equivalent classes ");
//        		System.out.println();
        		if ( equivalentClassConcepts.containsKey(splited[1]) ) {
        			equivalentClassTerms = equivalentClassConcepts.get(splited[1]);
        		} else {
        			equivalentClassTerms = new ArrayList<String>();
        		}
        		equivalentClassTerms.add(splited[2]);
        		equivalentClassConcepts.put(splited[1],equivalentClassTerms);
    		}
    	}
    	for ( String concept : equivalentClassConcepts.keySet() ) {
    		ArrayList<String> terms = equivalentClassConcepts.get(concept);
    		String secondTerm = "";
    		for ( String t : terms ) {
    			secondTerm = secondTerm+t+",";
    		}
    		secondTerm = secondTerm.substring(0, secondTerm.length()-1);
    		String[] arr = {"EquivalentClasses",concept,secondTerm};
//    		String[] arr = terms.toArray(new String[terms.size()]);
//    		System.out.println(Arrays.toString(arr)+" || "+secondTerm);
    		addtoFile(arr);
    	}
    	
    	Set<OWLDisjointObjectPropertiesAxiom> disjointClassesAxioms = ontology.getAxioms(AxiomType.DISJOINT_OBJECT_PROPERTIES);
//    	HashMap<String,ArrayList<String>> equivalentClassConcepts = new HashMap<String,ArrayList<String>>();
//    	ArrayList<String> equivalentClassTerms;
    	for (OWLAxiom ax : disjointClassesAxioms ) {
    		String test = ax.toString();
    		String result = totriple(test);
    		String[] splited = result.split("\\s+");
    		System.out.println(result+" || disjoint object ");
    		System.out.println();
//    		if ( splited.length == 3 ) {
//        		System.out.println(result+" || equivalent classes ");
//        		System.out.println();
//        		if ( equivalentClassConcepts.containsKey(splited[1]) ) {
//        			equivalentClassTerms = equivalentClassConcepts.get(splited[1]);
//        		} else {
//        			equivalentClassTerms = new ArrayList<String>();
//        		}
//        		equivalentClassTerms.add(splited[2]);
//        		equivalentClassConcepts.put(splited[1],equivalentClassTerms);
//    		}
    	}
    	*/
    	
    	//Set<OWLClassAssertionAxiom> classAssertionalAxioms = ontology.getAxioms(AxiomType.CLASS_ASSERTION);
    	//readAxiomsFromParticularConstruct(classAssertionalAxioms);
    	

    	//Constructs which are left are - ObjectComplement, ObjectIntersection, ObjectOneOf, ObjectUnionOf
    	//
    	//disjointClass Size == 2 then disjointWith else AllDisjointClasses
    	
    	Set<OWLDeclarationAxiom> owlClassAxioms = ontology.getAxioms(AxiomType.DECLARATION);
    	Set<OWLDisjointClassesAxiom> disjointWithClassAxioms = ontology.getAxioms(AxiomType.DISJOINT_CLASSES);
    	Set<OWLDisjointUnionAxiom> disjointUnionAxioms = ontology.getAxioms(AxiomType.DISJOINT_UNION);
    	Set<OWLSubClassOfAxiom> subClassOfAxioms = ontology.getAxioms(AxiomType.SUBCLASS_OF);
    	Set<OWLEquivalentClassesAxiom> equivalentClassAxioms = ontology.getAxioms(AxiomType.EQUIVALENT_CLASSES);
    	
    	Set<OWLObjectPropertyDomainAxiom> objectDomainAxioms = ontology.getAxioms(AxiomType.OBJECT_PROPERTY_DOMAIN);
    	Set<OWLObjectPropertyRangeAxiom> objectRangeAxioms = ontology.getAxioms(AxiomType.OBJECT_PROPERTY_RANGE);
    	Set<OWLSubObjectPropertyOfAxiom> objectSubPropertyAxioms = ontology.getAxioms(AxiomType.SUB_OBJECT_PROPERTY);
    
    	Set<OWLAsymmetricObjectPropertyAxiom> objectAsymmetricAxioms = ontology.getAxioms(AxiomType.ASYMMETRIC_OBJECT_PROPERTY);
    	Set<OWLFunctionalObjectPropertyAxiom> objectFunctionalAxioms = ontology.getAxioms(AxiomType.FUNCTIONAL_OBJECT_PROPERTY);
    	Set<OWLInverseFunctionalObjectPropertyAxiom> objectInverseFunctionalAxioms = ontology.getAxioms(AxiomType.INVERSE_FUNCTIONAL_OBJECT_PROPERTY);
    	Set<OWLInverseObjectPropertiesAxiom> objectInverseAxioms = ontology.getAxioms(AxiomType.INVERSE_OBJECT_PROPERTIES);
    	Set<OWLIrreflexiveObjectPropertyAxiom> objectIrreflexiveAxioms = ontology.getAxioms(AxiomType.IRREFLEXIVE_OBJECT_PROPERTY);
    	Set<OWLReflexiveObjectPropertyAxiom> objectReflexiveAxioms = ontology.getAxioms(AxiomType.REFLEXIVE_OBJECT_PROPERTY);
    	Set<OWLSymmetricObjectPropertyAxiom> objectSymmetricAxioms = ontology.getAxioms(AxiomType.SYMMETRIC_OBJECT_PROPERTY);
    	Set<OWLTransitiveObjectPropertyAxiom> objectTransitiveAxioms = ontology.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY);
    	
    	Set<OWLSubPropertyChainOfAxiom> objectPropertyChainAxioms = ontology.getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF);
    	Set<OWLObjectPropertyAssertionAxiom> objectPropertyAxioms = ontology.getAxioms(AxiomType.OBJECT_PROPERTY_ASSERTION);
    	Set<OWLEquivalentObjectPropertiesAxiom> objectEquivalentAxioms = ontology.getAxioms(AxiomType.EQUIVALENT_OBJECT_PROPERTIES);
    	Set<OWLDisjointObjectPropertiesAxiom> objectDisjointWithAxioms = ontology.getAxioms(AxiomType.DISJOINT_OBJECT_PROPERTIES);
    	
    	Set<OWLDataPropertyDomainAxiom> dataDomainAxioms = ontology.getAxioms(AxiomType.DATA_PROPERTY_DOMAIN);
    	Set<OWLDataPropertyRangeAxiom> dataRangeAxioms = ontology.getAxioms(AxiomType.DATA_PROPERTY_RANGE);
    	Set<OWLSubDataPropertyOfAxiom> dataSubPropertyAxioms = ontology.getAxioms(AxiomType.SUB_DATA_PROPERTY);
    	
    	Set<OWLFunctionalDataPropertyAxiom> dataFunctionalAxioms = ontology.getAxioms(AxiomType.FUNCTIONAL_DATA_PROPERTY);
    	Set<OWLEquivalentDataPropertiesAxiom> dataEquivalentAxioms = ontology.getAxioms(AxiomType.EQUIVALENT_DATA_PROPERTIES);
    	Set<OWLDisjointDataPropertiesAxiom> dataDisjointWithAxioms = ontology.getAxioms(AxiomType.DISJOINT_DATA_PROPERTIES);
    	
    	Object[] allConstructs = {owlClassAxioms,disjointWithClassAxioms,disjointUnionAxioms,subClassOfAxioms,equivalentClassAxioms,
    			objectDomainAxioms,objectRangeAxioms,objectSubPropertyAxioms,
    			objectAsymmetricAxioms,objectFunctionalAxioms,objectInverseFunctionalAxioms,objectInverseAxioms,objectIrreflexiveAxioms,objectReflexiveAxioms,objectSymmetricAxioms,objectTransitiveAxioms,
    			objectPropertyChainAxioms,objectPropertyAxioms,objectEquivalentAxioms,objectDisjointWithAxioms,
    			dataDomainAxioms,dataRangeAxioms,dataSubPropertyAxioms,dataFunctionalAxioms,dataEquivalentAxioms,dataDisjointWithAxioms};
    	
//    	for( Object construct : allConstructs ) {
//    		readAxiomsFromParticularConstruct((Set) construct);
//    	}
//    	System.out.println();
    	
    	/*
    	for( OWLClass oc : ontology.classesInSignature().collect( Collectors.toSet() ) ) {
    		System.out.println( "Class: " + oc.toString() );
    		for( OWLAxiom axiom : ontology.axioms( oc ).collect( Collectors.toSet() ) ) {
              System.out.println( "\tAxiom: " + axiom.toString() );
              // create an object visitor to get to the subClass restrictions
              axiom.accept( new OWLObjectVisitor() {
                  // found the subClassOf axiom  
                  public void visit( OWLSubClassOfAxiom subClassAxiom ) {
                      // create an object visitor to read the underlying (subClassOf) restrictions
                      subClassAxiom.getSuperClass().accept( new OWLObjectVisitor() {
                    	  public void visit( OWLObjectSomeValuesFrom someValuesFromAxiomObject ) { printQuantifiedRestrictionObject( oc, someValuesFromAxiomObject ); }
                    	  public void visit( OWLObjectAllValuesFrom allValuesFromAxiomObject ) { printQuantifiedRestrictionObject( oc, allValuesFromAxiomObject ); }
//                    	  public void visit( OWLObjectHasValue hasValuesFromAxiomObject ) { printHasValueRestrictionObject( oc, hasValuesFromAxiomObject ); }
                    	  public void visit( OWLObjectExactCardinality exactCardinalityAxiomObject ) { printCardinalityRestrictionObject( oc, exactCardinalityAxiomObject ); }
                    	  public void visit( OWLObjectMinCardinality minCardinalityAxiomObject ) { printCardinalityRestrictionObject( oc, minCardinalityAxiomObject ); }
                    	  public void visit( OWLObjectMaxCardinality maxCardinalityAxiomObject ) { printCardinalityRestrictionObject( oc, maxCardinalityAxiomObject ); }
                    	  
                    	  public void visit( OWLDataAllValuesFrom allValuesFromAxiomData ) { printQuantifiedRestrictionData( oc, allValuesFromAxiomData ); }
                    	  public void visit( OWLDataSomeValuesFrom someValuesFromAxiomData ) { printQuantifiedRestrictionData( oc, someValuesFromAxiomData ); }
//                    	  public void visit( OWLDataHasValue hasValuesFromAxiomData ) { printQuantifiedRestrictionData( oc, hasValuesFromAxiomData ); }
                    	  public void visit( OWLDataExactCardinality exactCardinalityAxiomData ) { printCardinalityRestrictionData( oc, exactCardinalityAxiomData ); }
                          public void visit( OWLDataMinCardinality minCardinalityAxiomData ) { printCardinalityRestrictionData( oc, minCardinalityAxiomData ); }
                          public void visit( OWLDataMaxCardinality maxCardinalityAxiomData ) { printCardinalityRestrictionData( oc, maxCardinalityAxiomData ); }
                          // TODO: same for AllValuesFrom etc.
                      });
                  }
              });
          }
          System.out.println();
         }
    	*/
    }	
	 public static void printQuantifiedRestrictionObject( OWLClass oc, OWLQuantifiedObjectRestriction restriction ) {
	        System.out.println( "\t\tClass: " + oc.toString() );
	        System.out.println( "\t\tClassExpressionType: " + restriction.getClassExpressionType().toString() );
	        System.out.println( "\t\tProperty: "+ restriction.getProperty().toString() );
	        System.out.println( "\t\tObject: " + restriction.getFiller().toString() );
	        System.out.println();
	    }
	    
	    public static void printCardinalityRestrictionObject( OWLClass oc, OWLObjectCardinalityRestriction restriction ) {
	        System.out.println( "\t\tClass: " + oc.toString() );
	        System.out.println( "\t\tClassExpressionType: " + restriction.getClassExpressionType().toString() );
	        System.out.println( "\t\tCardinality: " + restriction.getCardinality() );
	        System.out.println( "\t\tProperty: "+ restriction.getProperty().toString() );
	        System.out.println( "\t\tObject: " + restriction.getFiller().toString() );
	        System.out.println();
	    }

	    
	    public static void readAxiomsFromParticularConstruct(Set<OWLAxiom> constructAxioms) {
	    	for (OWLAxiom ax : constructAxioms ) {
	    		String test = ax.toString();
	    		String result = totriple(test);
	    		String[] splited = result.split("\\s+");
	    		System.out.println(result+" || functionnnn ke andar object ");
	    		System.out.println(Arrays.toString(splited)+" || "+splited[0]+" || "+splited.length);
	    		System.out.println();
	    	}
    	}
    	    
	
//		 public static void printHasValueRestrictionObject( OWLClass oc, OWLObjectHasValue restriction ) {
//		        System.out.println( "\t\tClass: " + oc.toString() );
//		        System.out.println( "\t\tClassExpressionType: " + restriction.getClassExpressionType().toString() );
//		        System.out.println( "\t\tProperty: "+ restriction.getProperty().toString() );
//		        System.out.println( "\t\tObject: " + restriction.getFiller().toString() );
//		        System.out.println();
//		    }
		 
		 public static void printQuantifiedRestrictionData( OWLClass oc, OWLQuantifiedDataRestriction restriction ) {
		        System.out.println( "\t\tClass: " + oc.toString() );
		        System.out.println( "\t\tClassExpressionType: " + restriction.getClassExpressionType().toString() );
		        System.out.println( "\t\tProperty: "+ restriction.getProperty().toString() );
		        System.out.println( "\t\tObject: " + restriction.getFiller().toString() );
		        System.out.println();
		    }
		 
	    public static void printCardinalityRestrictionData( OWLClass oc, OWLDataCardinalityRestriction restriction ) {
	        System.out.println( "\t\tClass: " + oc.toString() );
	        System.out.println( "\t\tClassExpressionType: " + restriction.getClassExpressionType().toString() );
	        System.out.println( "\t\tCardinality: " + restriction.getCardinality() );
	        System.out.println( "\t\tProperty: "+ restriction.getProperty().toString() );
	        System.out.println( "\t\tObject: " + restriction.getFiller().toString() );
	        System.out.println();
	    }
	    
    private static String usingBufferedReader(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) { contentBuilder.append(sCurrentLine).append("\n"); }
        } 
        catch (IOException e) { e.printStackTrace(); }
        return contentBuilder.toString();
    }
}
