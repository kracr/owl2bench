package owl.cs.myfirst.owlapi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLFunctionalSyntaxOntologyFormat;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;
import com.cs.myfirst.owlapi.Features.BoilerplateCode.FilenameConstructMapping;
import com.cs.myfirst.owlapi.Features.BoilerplateCode.LastDance;
import com.cs.myfirst.owlapi.Features.BoilerplateCode.Util;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;

import owl.cs.myfirst.owlapi.Features.AssertionCategory;
import owl.cs.myfirst.owlapi.Features.ClassEnumerationcategory;
import owl.cs.myfirst.owlapi.Features.ClassExpressionAxiomsCategory;
import owl.cs.myfirst.owlapi.Features.DataPropertyAxiomsCategory;
import owl.cs.myfirst.owlapi.Features.DataPropertyRestrictionCategory;
import owl.cs.myfirst.owlapi.Features.DataRangesCategory;
import owl.cs.myfirst.owlapi.Features.ObjectPropertyAxiomCategory;
import owl.cs.myfirst.owlapi.Features.ObjectPropertyRestrictinoCategory;
import owl.cs.myfirst.owlapi.Generator.ClassPool;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;
import owl.cs.myfirst.owlapi.Generator.PropertyPool;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class app 
{	
	public static Map<String, Object> objectMap;
	LinkedHashMap<String,Integer> reverse_indexes;
	public static OWLOntologyManager man;
	OWLOntology my_ontology;
	
	TurtleOntologyFormat turtleFormat;
	RDFXMLOntologyFormat rdfFormat;
	OWLXMLOntologyFormat xmlFormat;
	ManchesterOWLSyntaxOntologyFormat manchesterFormat;
	OWLFunctionalSyntaxOntologyFormat functionalFormat;
	
	public static ArrayList<OWLAxiom> notToBeIncludedAxioms;
	public static HashMap<String,String> alreadyAssertionAxiom;
	public static HashMap<String,String> domainRangeOnly;
	public static LinkedHashMap<String,Integer> rdfsSubsOnly;
	public static HashMap<String,String> discardConcepts;
	public static HashMap<String,String> discardProperties;
	
	/**
	 * This method is responsible for generating OBJECT HASHMAP and INDEX HASHMAP for construct.
	 * There are 7 Categories currently, under which some constructs are present. 
	 * OBJECT HASHMAP stores this relationship of construct with its category (key -> constructName, value -> category )
	 * 
	 * INDEX HASHMAP stores the index of construct, so that we can DIRECTLY ACCESS its value from USER INPUT ARRAY,
	 * to determine the corresponding user input for THAT construct.
	 * ( key -> constructName, value -> index )
	 * 
	 * With this, we also initialiazing some OWL API objects to STORE the ontology etc. Straighforward
	 * No need to go in details.
	 */
	public void loadMainNecessaryThings() throws OWLOntologyCreationException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException, OWLOntologyStorageException {
		objectMap = new HashMap<String, Object>();
		 String[] construct1 = {"OwlClass","ObjectComplement","ObjectIntersection","ObjectOneOf","ObjectUnionOf"};
		 String[] construct2 = {"DisjointUnion","DisjointWith","EquivalentClass","RdfsSubClassOf"}; //"AllDisjointClasses",
		 String[] construct3 = {"AsymmetricProperty","EquivalentObjectProperty","FunctionalObjectProperty",
				  "InverseFunctionalProperty","InverseOfProperty","IrreflexiveProperty",
				  "ObjectPropertyDisjointWith","ReflexiveProperty","SymmetricProperty",
				  "TransitiveProperty","RdfsObjectDomain","RdfsObjectRange","PropertyChainAxiom","RdfsObjectSubPropertyOf","ObjectProperty"}; //"AllDisjointObjectProperties",
		 String[] construct4 = {"ObjectAllValuesFrom","ObjectHasSelf","ObjectHasValue","ObjectSomeValuesFrom","ObjectQualifiedCardinality","ObjectMaxQualifiedCardinality","ObjectMinQualifiedCardinality"};
		 String[] construct5 = {"EquivalentDataProperty","FunctionalDataProperty","DataPropertyDisjointWith","RdfsDataDomain","RdfsDataRange","RdfsDataSubPropertyOf"}; //"AllDisjointDataProperties",
		 String[] construct6 = {"DataAllValues","DataHasValue", "DataMaxQualifiedCardinality","DataMinQualifiedCardinality","DataQualifiedCardinality","DataSomeValuesFrom"};
		 String[] construct7 = {"DataComplementOf","DataIntersectionOf", "DataOneOf","DataUnionOf"};
		 String[] construct10 = {"HasKey", "ClassAssertionAxioms","ObjectPropertyAssertionAxioms","DataPropertyAssertionAxioms"};
		 
		 String[][] overall = {construct1,construct2,construct3,construct4,construct5,construct6,construct7,construct10}; 
		  int count = 0;
		  reverse_indexes = new LinkedHashMap<String,Integer>();
		  for ( int i = 0 ; i < overall.length ; i++ ) {
			  for( int j = 0 ; j < overall[i].length ; j++ ) reverse_indexes.put(overall[i][j],count++);
		  }
		  
		  String IOR = "http://benchmark/OWL2Bench/";
		  man = OWLManager.createOWLOntologyManager();
		  my_ontology = man.createOntology();
		  
	      OWLDataFactory factory = my_ontology.getOWLOntologyManager().getOWLDataFactory();
	      DefaultPrefixManager pm = new DefaultPrefixManager(IOR);
	      ClassPool classpool1 = new ClassPool(factory, pm);
	      PropertyPool propertypool1 = new PropertyPool(factory, pm);
	      FeaturePool fp1 = new FeaturePool(classpool1, propertypool1);
		  
	      rdfFormat = new RDFXMLOntologyFormat(); rdfFormat.copyPrefixesFrom(pm);
	      turtleFormat  = new TurtleOntologyFormat(); turtleFormat.copyPrefixesFrom(pm);
	      xmlFormat = new OWLXMLOntologyFormat(); xmlFormat.copyPrefixesFrom(pm);
	      manchesterFormat = new ManchesterOWLSyntaxOntologyFormat(); manchesterFormat.copyPrefixesFrom(pm);
	      functionalFormat = new OWLFunctionalSyntaxOntologyFormat(); functionalFormat.copyPrefixesFrom(pm);

	      // 1 - ce | 2 - ca | 3 - oa | 4 - or | 5 - da | 6 - dr | 7 - drc | 8 - ac
	      ClassEnumerationcategory ce = new ClassEnumerationcategory(factory, pm, fp1, my_ontology);  //1
	      ClassExpressionAxiomsCategory ca = new ClassExpressionAxiomsCategory(factory, pm, fp1, my_ontology); //2
	      ObjectPropertyAxiomCategory oa = new ObjectPropertyAxiomCategory(factory, pm, fp1, my_ontology); //3
	      ObjectPropertyRestrictinoCategory or = new ObjectPropertyRestrictinoCategory(factory, pm, fp1, my_ontology); //4
	      DataPropertyAxiomsCategory da = new DataPropertyAxiomsCategory(factory, pm, fp1, my_ontology); //5
	      DataPropertyRestrictionCategory dr = new DataPropertyRestrictionCategory(factory, pm, fp1, my_ontology); //6
	      DataRangesCategory drc = new DataRangesCategory(factory, pm, fp1, my_ontology); //7
	      AssertionCategory ac = new AssertionCategory(factory, pm, fp1, my_ontology); //10

	      for ( int i = 0 ; i < overall.length ; i++ ) {
	    	  for ( int j = 0 ; j < overall[i].length ; j++ ) {
	    		  if ( (i+1) == 1 ) objectMap.put(overall[i][j], ce);
	    		  else if ( (i+1) == 2 ) objectMap.put(overall[i][j], ca);
	    		  else if ( (i+1) == 3 ) objectMap.put(overall[i][j], oa);
	    		  else if ( (i+1) == 4 ) objectMap.put(overall[i][j], or);
	    		  else if ( (i+1) == 5 ) objectMap.put(overall[i][j], da);
	    		  else if ( (i+1) == 6 ) objectMap.put(overall[i][j], dr);
	    		  else if ( (i+1) == 7 ) objectMap.put(overall[i][j], drc);
	    		  else if ( (i+1) == 8 ) objectMap.put(overall[i][j], ac);  
	    	  }
	      }

	}

	/**
	 * @param sorted - Construct Hashmap, THE ORDER in which we ITERATE it is the ORDER in which we insert into ontology.
	 * @param inputs - User input arrays where each index represents number of times that construct SHOULD appear in onotlogy.
	 * @param fileName - fileName to STORE resultant ontology
	 * 
	 * This is the main generating function what it does is first generates FilenameCostructMaping mappings.
	 * In 'FilenameCostructMaping', we make 2 kinds of Hashmap. 
	 * 1) - Construct to 'line-separator'. In each construct's AXIOMS file, we have written AXIOMS file in format
	 * of <"subject 'line-separator' objects">, a FIXED FORMAT. 
	 * So, we have made a hashmap correspoding each construct with 'line-separator.
	 * 
	 * 2) - Construct to 'fileName' mapping. We have made a mention of telling , which file to read axioms from. ie 
	 * For a particular contruct, use this CORRESPONDING txt file to read the axioms, that mapping
	 *  
	 * CommonFrameWork is JAVA file, in which we have written MAIN LOGIC to INSERT construct into the ontology.
	 * 'addToOntology' is the method, puts construct and its CORRESPONDING AXIOMS into the ontology.
	 * Its details will have been written in THAT file. But please DONT READ them now. FOLLOW the ORDER mentioned in ORDER.txt.
	 * 
	 * Here we are iterating in the ORDER of the hashmap provided by the variant ( userInput, axiomCount )
	 * Checking if its user-input is greater than 0 ( that means user has inputted the value for that construct )
	 * 
	 * Then after we have inserted all user selected constructs into ontology.
	 * We are SHOULD have got 'globalHashMap' filled with concepts/properties USED FOR ONTOLOGIES.
	 * In 'LastDance' JAVA file, we inserting DOMAIN/RANGE , SUBCLASS/SUBPROPERTY axioms of these globalHashMap ones.
	 * More Details Provided in its Java File.
	 * 
	 * 'Reasoner' JAVA file is to check CONSISTENCY of the ontology, we just generated using user inputs. 
	 * Maam's Code copy pasted directly, NO NEED TO READ that java file.
	 */
	public void generateOntology(LinkedHashMap<String, Integer> sorted,int[] inputs,String fileName,String resultFormat,boolean extraAxioms) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException, OWLOntologyStorageException, OWLOntologyCreationException, NoSuchMethodException, SecurityException {
		notToBeIncludedAxioms = new ArrayList<OWLAxiom>();
		alreadyAssertionAxiom = new HashMap<String,String>();
		rdfsSubsOnly = new LinkedHashMap<String,Integer>();
		domainRangeOnly = new HashMap<String,String>();
		discardProperties = new HashMap<String,String>();
		discardConcepts = new HashMap<String,String>();
		
		FilenameConstructMapping filenameSepartor = new FilenameConstructMapping(); filenameSepartor.mapping();
		CommonFramework cf = new CommonFramework(); File fileout = new File(fileName);
		LinkedHashMap<String, String> lastConstructs = new LinkedHashMap<String,String>();
		
		for ( String iter : sorted.keySet() ) {
			String key = iter;
			if ( reverse_indexes.containsKey(key) && !key.contains("AssertionAxioms") ) {	//Only for TBox Axioms First
				int index = reverse_indexes.get(key);
				if ( inputs[index] > 0 ) {
					if ( !FilenameConstructMapping.constructsForLast.containsKey(key) ) {
//						System.out.println(key+" | "+sorted.get(key)+" inp-> "+inputs[index]+" || "+fileName);
						cf.addToOntology(FilenameConstructMapping.fileMap.get(key),FilenameConstructMapping.separatorMap.get(key),inputs[index]);
					} else lastConstructs.put(key,"");
				}
			}
		}
		
		if ( lastConstructs.containsKey("RdfsObjectDomain") ) 
			cf.addToOntology(FilenameConstructMapping.fileMap.get("RdfsObjectDomain"),FilenameConstructMapping.separatorMap.get("RdfsObjectDomain"),inputs[reverse_indexes.get("RdfsObjectDomain")]);
		else if ( lastConstructs.containsKey("RdfsObjectRange") ) 
			cf.addToOntology(FilenameConstructMapping.fileMap.get("RdfsObjectRange"),FilenameConstructMapping.separatorMap.get("RdfsObjectRange"),inputs[reverse_indexes.get("RdfsObjectRange")]);
		else if ( lastConstructs.containsKey("RdfsDataDomain") ) 
			cf.addToOntology(FilenameConstructMapping.fileMap.get("RdfsDataDomain"),FilenameConstructMapping.separatorMap.get("RdfsDataDomain"),inputs[reverse_indexes.get("RdfsDataDomain")]);
		else if ( lastConstructs.containsKey("RdfsDataRange") )
			cf.addToOntology(FilenameConstructMapping.fileMap.get("RdfsDataRange"),FilenameConstructMapping.separatorMap.get("RdfsDataRange"),inputs[reverse_indexes.get("RdfsDataRange")]);
		
		if ( lastConstructs.containsKey("RdfsSubClassOf") ) 
			cf.addToOntology(FilenameConstructMapping.fileMap.get("RdfsSubClassOf"),FilenameConstructMapping.separatorMap.get("RdfsSubClassOf"),inputs[reverse_indexes.get("RdfsSubClassOf")]);
		else if ( lastConstructs.containsKey("RdfsObjectSubPropertyOf") )
			cf.addToOntology(FilenameConstructMapping.fileMap.get("RdfsObjectSubPropertyOf"),FilenameConstructMapping.separatorMap.get("RdfsObjectSubPropertyOf"),inputs[reverse_indexes.get("RdfsObjectSubPropertyOf")]);
		else if ( lastConstructs.containsKey("RdfsDataSubPropertyOf") )
			cf.addToOntology(FilenameConstructMapping.fileMap.get("RdfsDataSubPropertyOf"),FilenameConstructMapping.separatorMap.get("RdfsDataSubPropertyOf"),inputs[reverse_indexes.get("RdfsDataSubPropertyOf")]);
		
		/*
		 * This extra axioms does is "Suppose EXTRA axiom" check mark is clicked.
		 * Then we will make EXTRA domain-range, subclass/subprop axioms for Roles/Concepts which we are storing
		 * Util.commonConstructs hashmap ( common hashmap to store each roles/concepts, as they are coming ).
		 * Which can be used for RANKING in further contructs's axioms after reading them from txt file.
		 */
		if ( extraAxioms ) {
			LastDance lastOne = new LastDance();
			lastOne.oneLastTime();
		}
		
		if ( lastConstructs.containsKey("ObjectPropertyDisjointWith") ) 
			cf.addToOntology(FilenameConstructMapping.fileMap.get("ObjectPropertyDisjointWith"),FilenameConstructMapping.separatorMap.get("ObjectPropertyDisjointWith"),inputs[reverse_indexes.get("ObjectPropertyDisjointWith")]);
		else if ( lastConstructs.containsKey("DataPropertyDisjointWith") ) 
			cf.addToOntology(FilenameConstructMapping.fileMap.get("DataPropertyDisjointWith"),FilenameConstructMapping.separatorMap.get("DataPropertyDisjointWith"),inputs[reverse_indexes.get("DataPropertyDisjointWith")]);
		else if ( lastConstructs.containsKey("DisjointWith") ) 
			cf.addToOntology(FilenameConstructMapping.fileMap.get("DisjointWith"),FilenameConstructMapping.separatorMap.get("DisjointWith"),inputs[reverse_indexes.get("DisjointWith")]);
		
//		System.out.println(Util.commonConstructs.size()+" || "+Util.underscoreCommonConstructs.size());
		int classCount = inputs[reverse_indexes.get("ClassAssertionAxioms")] > 0 ? inputs[reverse_indexes.get("ClassAssertionAxioms")] : 0;
		int objectCount = inputs[reverse_indexes.get("ObjectPropertyAssertionAxioms")] > 0 ? inputs[reverse_indexes.get("ObjectPropertyAssertionAxioms")] : 0;
		int dataCount = inputs[reverse_indexes.get("DataPropertyAssertionAxioms")] > 0 ? inputs[reverse_indexes.get("DataPropertyAssertionAxioms")] : 0;
		
		//Only For ABox/Asserions Axioms, If Chosen By User.
		if ( classCount > 0 || objectCount > 0 || dataCount > 0 ) AssertionCategory.convertLineToAssertionAxiom(classCount,objectCount,dataCount,extraAxioms);

		//To Save In Different Format
		if ( resultFormat.equals("RDF") ) man.saveOntology((OWLOntology) my_ontology, rdfFormat, new FileOutputStream(fileout));
		else if ( resultFormat.equals("Turtle") ) man.saveOntology((OWLOntology) my_ontology, turtleFormat, new FileOutputStream(fileout));
		else if ( resultFormat.equals("XML") ) man.saveOntology((OWLOntology) my_ontology, xmlFormat, new FileOutputStream(fileout));
		else if ( resultFormat.equals("Manchester") ) man.saveOntology((OWLOntology) my_ontology, manchesterFormat, new FileOutputStream(fileout));
		else if ( resultFormat.equals("Functional") ) man.saveOntology((OWLOntology) my_ontology, functionalFormat, new FileOutputStream(fileout));

		Reasoners reasoner = new Reasoners();
		reasoner.run(fileout, fileName);
	}
	
 /**
 * @param inputs - User input arrays where each index represents number of times that construct SHOULD appear in onotlogy.
 *  
 *  What this method does is first create object of Util class.
 *  In Util Class, there is method which INITIALIZES global HashMap to be used further to store 
 *  the occurences of concepts/properties present in each contructs AXIOMS text file.
 */
public void generate(int[] inputs,String outputFormat,boolean extraAxioms) throws OWLOntologyStorageException, OWLOntologyCreationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException{
		/*
	     * It loads up the OBJECT Hashmap. There are around 50 constructs present. Each of the constructs comes
	     * under one of the 7 categories present. We store mapping of each construct with its Category.
	     * This Object hashmap will later be used to DIRECTLY call the construct using Reflection concept. 
	     * It prevents use of writing lots of lots IF/ELSE for each construct. Using this mapping, we can DIRECTLY call the method.
	     */
	    loadMainNecessaryThings();

	    /*
	     *  In Util Class, there is method which INITIALIZES global HashMap to be used further to store 
	     *  the occurences of concepts/properties present in each contructs AXIOMS text file.
	     */
	    Util.initializeGlobalHashMaps();
	    
	    /* --> UserCount Minimum
	     * userCount is first variant of the algorithm 1 based on usage of GlobalHashMap.
	     * what the method 'initialUserInputCount' does is, for all given user input for each constructs.
	     * It provides a hashmap with each construct as its key and user value as its value in ASCENDING ORDER.
	     * That is constructs with LEAST values come on top and with BIG values come at the bottom.
	     * The order in which we traverse this HashMap , is the order in which CONSTRUCTS are inserted into
	     * the ONTOLOGIES. That is the construct with lowest user input will be inserted first into ONTOLOGIES
	     * GlobalHashMap. 
	     */
		LinkedHashMap<String, Integer> sortedUserInput = Util.initialUserInputCount(inputs,reverse_indexes);
		/*
		 * This is the main method used for generating ontologies. 
		 * It takes HashMap , the order in which we will INSERT contructs into Ontologies.
		 * 'inputs' is an user input array corresponding to each construct.
		 */
		generateOntology(sortedUserInput,inputs,"userInputCountOntology.owl", outputFormat, extraAxioms);
		
		/*
		 * To Re-initialize Global-HashMap for different variant of algo. 
		 * Because We dont want to use concepts/properties of PREVIOUS variant.
		 */
		loadMainNecessaryThings();
		Util.initializeGlobalHashMaps();
		/* --> Randomize UserCount Minimum
		 * Here we are using 'userCount' variant, but this time, instead of traversing it IN ASCENDING ORDER,
		 * We have SHUFFLED the order of HashMap. To traverse it in Random Order.
		 */
		LinkedHashMap<String, Integer> randomUserCount = Util.randomizeMapping(sortedUserInput);
		generateOntology(randomUserCount,inputs,"randomUserInputCountOntology.owl", outputFormat, extraAxioms);
		
		System.out.println(" -------------------------------------------------------------------------------------------------- ");
		
		loadMainNecessaryThings();
		Util.initializeGlobalHashMaps();
		/* --> AxiomCount Minimum
		 * 'axiomCount' is the second variant of the algorithm, in which we want to CHOOSE, TRAVERSE and INCLUDE
		 * in the ontologies. The method 'initialAxiomsCount' generates a HASHMAP in order in which keys
		 * contains construct name and value is number of axioms ( line number ) present in Construct's txt file.
		 * That is, constructs which have less axioms will be at the top. So constructs with less axiom counts
		 * will be inserted in ontologies and global hashmap first.
		 */
        LinkedHashMap<String, Integer> sorted = Util.initialAxiomsCount();
		generateOntology(sorted,inputs,"axiomCountOntology.owl", outputFormat, extraAxioms);
		
        loadMainNecessaryThings();
		Util.initializeGlobalHashMaps();
		/* --> Randomize AxiomCount Minimum
		 * Here we are using 'axiomCount' variant but this time, instead of traversing it in ASCENDING ORDER, 
		 * We have SHUFFLED the order of HashMap, we are traversing it in Random Order.
		 */
		LinkedHashMap<String, Integer> randomAxiomCount = Util.randomizeMapping(sorted);
		generateOntology(randomAxiomCount,inputs,"randomAxiomCountOntology.owl", outputFormat, extraAxioms);
		
		System.out.println(" ---------------------------------------THE_END--------------------------------------------------- ");
		
		//problematic constructs -> objectHasSelf and objectHasValue.
		//randomAxiomCount giving FALSE for DataAllValues, DataMax/Min/Cardinality
		//randomUserCount gets false for selecting ObjectAllValuesFrom, DataIntersectionOf
   }
}