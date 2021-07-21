package owl.cs.myfirst.owlapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.TextFileProcessor;

public class WriteAxiomsFromOwlApi {

	public static HashMap<String,String> fileMap;
	public static HashMap<String,String> separatorMap ;
	public static HashMap<String,String> ontologyTerm;
	
	public static void main( String[] args ) throws OWLOntologyCreationException, IOException {
		
        String filePath = "UNIV-BENCH-OWL2DL.owl";
//        String filePath = "UNIV-BENCH-OWL2DL_1.owl";
//      String filePath = "userInputCountOntology.owl";
        
		mapping();
		clearContentOfFiles();
        String ontoResult = readTxtFiles(filePath);
    	OWLOntologyManager man = OWLManager.createOWLOntologyManager();
    	OWLOntology ontology = man.loadOntologyFromOntologyDocument(new StringDocumentSource(ontoResult));
    	
    	//disjointClass Size == 2 then disjointWith else AllDisjointClasses
    	//OWLClassAssertionAxiom - CLASS_ASSERTION
    	//OWLObjectPropertyAssertionAxiom - OBJECT_PROPERTY_ASSERTION
    	//OWLDataPropertyAssertionAxiom - DATA_PROPERTY_ASSERTION
    	
    	// contructs where JAVA FILE is problem - 
    	//ObjectPRoperty
    	
    	Set<OWLClassAssertionAxiom> classAssertionAxioms = ontology.getAxioms(AxiomType.CLASS_ASSERTION);
    	
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
    	
    	Set<OWLHasKeyAxiom> hasKeyAxioms = ontology.getAxioms(AxiomType.HAS_KEY);
    	
    	Object[] cleanAxioms = {disjointUnionAxioms,objectDomainAxioms,objectSubPropertyAxioms,
    	objectAsymmetricAxioms,objectFunctionalAxioms,objectInverseFunctionalAxioms,objectInverseAxioms,objectIrreflexiveAxioms,objectReflexiveAxioms,objectSymmetricAxioms,objectTransitiveAxioms,
    	objectPropertyAxioms,objectEquivalentAxioms,
    	dataDomainAxioms,dataSubPropertyAxioms,dataFunctionalAxioms,dataEquivalentAxioms,
    	hasKeyAxioms
    	};
    	for( Object construct : cleanAxioms ) { readAxiomsFromParticularConstruct((Set) construct); }
    	
    	Object[] restrictions = {subClassOfAxioms,equivalentClassAxioms };
    	for( Object construct : restrictions ) { readAxiomsForRestrictionsTypes((Set) construct); }
    	
    	Object[] moreThanThreeWordedAxioms = {objectDisjointWithAxioms, dataDisjointWithAxioms, disjointWithClassAxioms, owlClassAxioms, classAssertionAxioms, objectPropertyChainAxioms  };
    	for( Object construct : moreThanThreeWordedAxioms ) { readAxiomsFromDisjointAndDeclaration((Set) construct); }
    	
    	Object[] rangesOnes = {dataRangeAxioms, objectRangeAxioms };
    	for( Object construct : rangesOnes ) { readAxiomsFromRangesOnes((Set) construct); }
    
	}
	
    public static void readAxiomsFromParticularConstruct(Set<OWLAxiom> constructAxioms) throws IOException {
    	for (OWLAxiom ax : constructAxioms ) {
    		String result = totriple(ax.toString());
    		String[] splited = result.split("\\s+");
    		
    		String axiom = "";
    		String separator = separatorMap.get(ontologyTerm.get(splited[0]));
    		String fileName = fileMap.get(ontologyTerm.get(splited[0]));
    		
    		if ( splited.length > 2 ) {
    			String singleLine = "";
    			for ( int i = 2 ; i < splited.length ; i++ ) { singleLine = singleLine+splited[i]+","; }
    			singleLine = singleLine.substring(0,singleLine.length()-1);
    			axiom = splited[1]+separator+singleLine;
    		}
    		else { axiom = splited[1]+separator; }
    		genericWriteToFile(axiom,fileName,separator);
    	}
	}
    
    public static void separateValuesOnlyFunction(String[] splited) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		list.add(splited[1]); 
		list.add(splited[3]);
		if ( splited[2].contains("AllValues") ) { list.add("only"); } 
		else if ( splited[2].contains("SomeValues") ) { list.add("some"); }
		
		if ( !splited[2].contains("HasSelf") ) { list.add(splited[4]); }
		String axiom = "";
		for ( String term : list ) { axiom = axiom+term+" "; }
		axiom.trim();
		genericWriteToFile(axiom,fileMap.get(ontologyTerm.get(splited[2])), separatorMap.get(ontologyTerm.get(splited[2])));
    }
    
    public static void separateEnumerationOnlyFunction(String[] splited) throws IOException {
		String axiom = "";
		String singleLine = "";
		for ( int i = 3 ; i < splited.length ; i++ ) { singleLine = singleLine+splited[i]+","; }
		singleLine = singleLine.substring(0,singleLine.length()-1);
		axiom = splited[1]+separatorMap.get(ontologyTerm.get(splited[2]))+singleLine;
		genericWriteToFile(axiom,fileMap.get(ontologyTerm.get(splited[2])), separatorMap.get(ontologyTerm.get(splited[2])));
    }
    
    public static void readAxiomsForRestrictionsTypes(Set<OWLAxiom> constructAxioms) throws IOException {
    	for (OWLAxiom ax : constructAxioms ) {
    		String result = totriple(ax.toString());
    		String[] splited = result.split("\\s+");
    		System.out.println(Arrays.toString(splited)+" || "+splited.length);
    		System.out.println();
    		
    		if ( splited.length > 3 ) {
    			if ( splited[2].contains("SomeValue") || splited[2].contains("AllValue") || splited[2].contains("HasValue") || splited[2].contains("HasSelf") ) {
    				separateValuesOnlyFunction(splited);
    			} else if ( splited[2].contains("Cardinality") ) {
    				ArrayList<String> list = new ArrayList<String>();
    				list.add(splited[1]);
    				list.add(splited[4]);
    				if ( splited[2].contains("MinCardinality") ) { list.add("min"); } 
    				else if ( splited[2].contains("MaxCardinality") ) {  list.add("max"); } 
    				else { list.add("exactly"); }
    				list.add(splited[3]);
    				list.add(splited[splited.length-1]);
    				String axiom = "";
    				for ( String term : list ) { axiom = axiom+term+" "; }
    				axiom.trim();
    				genericWriteToFile(axiom,fileMap.get(ontologyTerm.get(splited[2])), " ");
    			} else if ( (splited[2].contains("ComplementOf") || splited[2].contains("UnionOf") 
    					|| splited[2].contains("OneOf") || splited[2].contains("IntersectionOf")) ) {
    				separateEnumerationOnlyFunction(splited);
    	    	}
    		} else {
        		String axiom = splited[1]+separatorMap.get(ontologyTerm.get(splited[0]))+splited[2];
        		genericWriteToFile(axiom,fileMap.get(ontologyTerm.get(splited[0])),separatorMap.get(ontologyTerm.get(splited[0])));
    		}
    	}
    }
    
    public static void readAxiomsFromDisjointAndDeclaration(Set<OWLAxiom> constructAxioms) throws IOException {
    	for (OWLAxiom ax : constructAxioms ) {
    		String result = totriple(ax.toString());
    		String[] splited = result.split("\\s+");
    		String axiom = "";
//    		System.out.println(Arrays.toString(splited)+" || "+splited.length);
    		
    		if ( splited[0].equals("ClassAssertion") ) {
        		axiom = splited[1]+" classAssertion "+splited[2];
            	genericWriteToFile(axiom,"OwlNamedIndividual.txt"," classAssertion ");
    		}
    		else if ( splited.length > 3 && splited[1].contains("ObjectPropertyChain") ) {
        		String singleLine = "";
        		for ( int i = 3 ; i < splited.length ; i++ ) { singleLine = singleLine+splited[i]+","; }
        		singleLine = singleLine.substring(0,singleLine.length()-1);
        		axiom = splited[2]+" , "+singleLine;
        		genericWriteToFile(axiom,"OwlPropertyChainAxiomFeature.txt"," , ");
    		}
    		else if ( !splited[0].contains("Declaration") ) {
    			String construct = splited.length == 3 ? splited[0]+"-DisjointWith" : splited[0];
        		String separator = separatorMap.get(ontologyTerm.get(construct));
        		
        		String singleLine = "";
        		for ( int i = 2 ; i < splited.length ; i++ ) { singleLine = singleLine+splited[i]+","; }
        		singleLine = singleLine.substring(0,singleLine.length()-1);
        		axiom = splited[1]+separator+singleLine;
        		genericWriteToFile(axiom,fileMap.get(ontologyTerm.get(construct)),separator);
        		
    		} else if ( splited[1].contains("Class") || splited[1].contains("ObjectProperty") ) {
        		String fileName = splited[1].contains("Class") ? "OwlClass.txt" : "OwlObjectProperty.txt";
            	genericWriteToFile(splited[2],fileName,"\\r?\\n");
    		}
    	}
	}
    
    public static void readAxiomsFromRangesOnes(Set<OWLAxiom> constructAxioms) throws IOException {
    	for (OWLAxiom ax : constructAxioms ) {
    		String result = totriple(ax.toString());
    		String[] splited = result.split("\\s+");
//    		System.out.println(Arrays.toString(splited)+" || "+splited.length);
    		
    		if ( splited.length > 3 ) {
    			//Data waale handle karne hain  - oneof
    			readAxiomsForRestrictionsTypes(constructAxioms);
    		} else {
        		String axiom = splited[1]+separatorMap.get(ontologyTerm.get(splited[0]))+splited[2];
        		genericWriteToFile(axiom,fileMap.get(ontologyTerm.get(splited[0])),separatorMap.get(ontologyTerm.get(splited[0])));
    		}
    	}
    }
    
    //Some,HasVal,AllVale,Cardinality -> subclass and equivalent both data and object
    //oneof, complement, intersection, union -> data ke liye data-ranges yeh fix aur kahin nhi aayega
    //oneof, complement, intersection, union objects ke -> subclass and equivalent 
    //data-ranges 
    
    //SubClassOf Person DataAllValuesFrom hasName xsd:string 
    //SubClassOf Person DataMinCardinality1 hasResearchInterest xsd:string
    //SubClassOf Person DataSomeValuesFrom hasEmailAddress xsd:string
    //SubClassOf Person DataMaxCardinality2 hasTitle xsd:string
    //SubClassOf Student ObjectMinCardinality1 hasFacultyAdvisor Faculty
    //SubClassOf Person DataExactCardinality1 hasCode xsd:string
    
    //ObjectPropertyRange hasMajor ObjectUnionOf Engineering FineArts HumanitiesAndSocialScience Management Science 
    //DataPropertyRange hasPublicationDate DataUnionOfxsd:dateTime xsd:dateTimeStamp
    //DataPropertyRange hasAge DataComplementOfxsd:negativeInteger
    //DataPropertyRange hasPublicationDate DataUnionOfxsd:dateTime xsd:dateTimeStamp
    
    //EquivalentClasses ScienceStudent ObjectSomeValuesFrom hasCollegeDiscipline Science
    //EquivalentClasses TestCricketFan ObjectHasValue isCrazyAbout TestCricket
    //EquivalentClasses WomenCollege ObjectAllValuesFrom hasStudent Woman
    //EquivalentClasses PhDStudent ObjectExactCardinality1 enrollFor PhDProgram
    //EquivalentClasses NonEngineering ObjectComplementOf Engineering
    
    //EquivalentClasses LeisureStudent ObjectMaxCardinality1 takesCourse Course 
    
    //EquivalentClasses PeopleWithManyHobbies ObjectMinCardinality3 likes Interest
    //EquivalentClasses SemiGovernmentOrganization ObjectOneOf NonProfitOrganization ProfitOrganization
    //EquivalentClasses SelfAwarePerson ObjectHasSelf knows
    //EquivalentClasses Organization ObjectUnionOf GovernmentOrganization PrivateOrganization SemiGovernmentOrganization
    //EquivalentClasses Mother ObjectIntersectionOf Parent Woman
 
	 //write get construct term from first term
	 public static void genericWriteToFile(String axiomToWrite, String fileName, String separator) throws IOException {
//			System.out.println(axiomToWrite+" || "+fileName);
//			System.out.println();
			
		boolean flag = false;
		String filesContent = readTxtFiles(System.getProperty("user.dir")+"/DL/"+fileName);
		ArrayList<String> axioms = TextFileProcessor.readTxtFile(fileName,separator);
		String content[] = filesContent.split("\n");
		for(String con: content ) {
			if ( con.equals(axiomToWrite) ) {
//				System.out.println(con+" || "+axiomToWrite+" || "+fileName);
				flag = true;
				break;
			}
		}
		
		if(!flag) {
			System.out.println(axiomToWrite+" || "+fileName);
			File file = new File(System.getProperty("user.dir")+"/DL/"+fileName);
			FileWriter fr = new FileWriter(file, true);
			fr.write(axiomToWrite+"\n");
			fr.close();	
		}
	 }
	 
	public static void clearContentOfFiles() throws FileNotFoundException {
		File DL = new File(System.getProperty("user.dir")+"/DL/");
		if(DL.isDirectory()){
			String[] subNote = DL.list();
			for(String filename : subNote){
				if ( filename.contains(".txt") && !filename.contains("RootConcepts.txt") ) {
					PrintWriter writer = new PrintWriter(System.getProperty("user.dir")+"/DL/"+filename);
					writer.print("");
					writer.close();
				}
			}
		}
	}
	
    public static String readTxtFiles(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) { contentBuilder.append(sCurrentLine).append("\n"); }
        } 
        catch (IOException e) { e.printStackTrace(); }
        return contentBuilder.toString();
    }
    
	public static String totriple(String test) {
		test = test.replace("#", "/");
		test = test.replace("\"", "");
//		test = test.replace("(\"", " ");
		test = test.replace("<http://benchmark/OWL2Bench/", " ");
		test = test.replace(">", "");
		test = test.replace("(", " ");
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

	public static void mapping() throws IOException {
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
				  "RdfsObjectSubPropertyOf","ObjectProperty"}; //"AllDisjointObjectProperties",
		 String[] filename3 = {"OwlAsymmetricProperty.txt","OwlEquivalentObjectProperty.txt","OwlFunctionalObjectProperty.txt",
				 "OwlInverseFunctionalProperty.txt","OwlInverseOfProperty.txt","OwlIrreflexiveProperty.txt",
				 "OwlObjectPropertyDisjointWith.txt","OwlReflexiveProperty.txt","OwlSymmetricProperty.txt",
				 "OwlTransitiveProperty.txt","RdfsObjectDomain.txt","RdfsObjectRange.txt","OwlPropertyChainAxiomFeature.txt",
				 "RdfsObjectSubPropertyOf.txt","OwlObjectProperty.txt"}; //"AllDisjointObjectProperties.txt",
		 String[] separator3 = {" Asymmetric"," equivalent "," Functional",
				 " inverse"," inverseOf "," irreflexive",
				 " disjointWithObject "," reflexive"," Symmetrical",
				 " Transitive"," domain "," range "," , ",
				 " SubPropertyOf "," "}; // " disjointObject ",
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
		 String[] generatedOntologyTerm7 = {"DataComplementOf","DataIntersectionOf","DataOneOf","DataUnionOf"};
		 
		 String[] construct9 = {"NamedIndividual"}; 
		 String[] filename9 = {"OwlNamedIndividual.txt"};
		 String[] separator9 = {" classAssertion "};
		 String[] generatedOntologyTerm9 = {"ClassAssertion"};
		 
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
	}
}
