package owl.cs.myfirst.owlapi.Features;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.PrefixManager;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;
import com.cs.myfirst.owlapi.Features.BoilerplateCode.LastDance;
import com.cs.myfirst.owlapi.Features.BoilerplateCode.Util;

import owl.cs.myfirst.owlapi.WriteAxiomsFromOwlApi;
import owl.cs.myfirst.owlapi.app;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class AssertionCategory {
	protected static OWLDataFactory factory;
	protected static FeaturePool featurePool;
	protected static PrefixManager pm;
	protected static OWLOntology ontology;
	static List<String> allConcepts;
	static HashMap<String,String> hasKeyPropMap;
	
	public AssertionCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) {
		AssertionCategory.factory = factory;
		AssertionCategory.pm = pm;
		AssertionCategory.featurePool = featurePool;
		AssertionCategory.ontology = ontology;
		
		hasKeyPropMap = new HashMap<String,String>();
	}
	
	public static void convertLineToHasKey(ArrayList<String> allConcepts) {
		OWLClass hasKeyClass = featurePool.getExclusiveClass(allConcepts.get(0));
		OWLDataProperty hasKeyProperty = factory.getOWLDataProperty(allConcepts.get(1), pm);
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLHasKeyAxiom(hasKeyClass, hasKeyProperty));
		
		hasKeyPropMap.put(allConcepts.get(1), allConcepts.get(0));
	}
	 
	public static void convertLineToAssertionAxiom(int classCount, int objectCount, int dataCount, boolean extraAxioms) throws OWLOntologyCreationException, IOException {
		classCount = classCount - app.alreadyAssertionAxiom.size();
		
		OWLOntology tempOntology = app.man.createOntology();
		tempOntology.add(ontology.getAxioms());
		tempOntology.remove(app.notToBeIncludedAxioms);
		
		HashMap<String,String> objectDomain; HashMap<String,String> objectRange; HashMap<String,String> dataDomain; HashMap<String,String> dataRange;
		if (extraAxioms) {
			objectDomain = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.OBJECT_PROPERTY_DOMAIN).stream()
					.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
					.collect(Collectors.toMap(props -> props[1].toString(),props -> props[2].toString(),(prop1,prop2) -> prop1));
			
			objectRange = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.OBJECT_PROPERTY_RANGE).stream()
					.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
					.collect(Collectors.toMap(props -> props[1].toString(),props -> props[2].toString(),(prop1,prop2) -> prop1));
			
			dataDomain = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.DATA_PROPERTY_DOMAIN).stream()
					.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
					.collect(Collectors.toMap(props -> props[1].toString(),props -> props[2].toString(),(prop1,prop2) -> prop1));
			
			dataRange = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.DATA_PROPERTY_RANGE).stream()
					.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
					.collect(Collectors.toMap(props -> props[1].toString(),props -> props[2].toString(),(prop1,prop2) -> prop1));
			
			//DataComplement, DataOneOf, , instances ???
			//rdf:Literla, xsd:string,  mai KOI BHI RANDOM STRING 
		} else {
			objectDomain = LastDance.constructAxiomsHashMap("RdfsObjectDomain.txt", " domain ");
			objectRange = LastDance.constructAxiomsHashMap("RdfsObjectRange.txt", " range ");
			dataDomain = LastDance.constructAxiomsHashMap("RdfsDataDomain.txt", " domain ");
			dataRange = LastDance.constructAxiomsHashMap("RdfsDataRange.txt", " range ");
		}
		
//		System.out.println(objectDomain);
//		System.out.println(objectRange);
//		System.out.println(dataDomain);
//		System.out.println(dataRange);
//		System.out.println(tempOntology.getAxioms(AxiomType.DATA_PROPERTY_RANGE));
		
		HashMap<String,String> objectPropsDomain = (HashMap<String, String>) tempOntology.getObjectPropertiesInSignature().stream()
		.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).trim()).collect(Collectors.toMap(Function.identity(),Function.identity()));
		for( String key : objectPropsDomain.keySet() ) {
			String item = extraAxioms == false && key.contains("_") ? key.substring(0,key.indexOf("_")) : key;
			if ( objectDomain.containsKey(item) ) objectPropsDomain.put(key, objectDomain.get(item));
			else objectPropsDomain.put(key, "anyInstance");
		}
		
		HashMap<String,String> objectPropsRange = (HashMap<String, String>) tempOntology.getObjectPropertiesInSignature().stream()
		.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).trim()).collect(Collectors.toMap(Function.identity(),Function.identity()));
		for( String key : objectPropsRange.keySet() ) {
			String item = extraAxioms == false && key.contains("_") ? key.substring(0,key.indexOf("_")) : key;
			if ( objectRange.containsKey(item) ) objectPropsRange.put(key, objectRange.get(item));
			else objectPropsRange.put(key.trim(), "anyInstance");
		}
		
		HashMap<String,String> dataPropsDomain = (HashMap<String, String>) tempOntology.getDataPropertiesInSignature().stream()
		.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).trim()).collect(Collectors.toMap(Function.identity(),Function.identity()));
		for( String key : dataPropsDomain.keySet() ) {
			String item = extraAxioms == false && key.contains("_") ? key.substring(0,key.indexOf("_")) : key;
			if ( dataDomain.containsKey(item) ) dataPropsDomain.put(key, dataDomain.get(item));
			else dataPropsDomain.put(key, "anyInstance");
		}
		
		HashMap<String,String> dataPropsRange = (HashMap<String, String>) tempOntology.getDataPropertiesInSignature().stream()
		.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).trim()).collect(Collectors.toMap(Function.identity(),Function.identity()));
		for( String key : dataPropsRange.keySet() ) {
			if ( dataRange.containsKey(key) ) dataPropsRange.put(key, dataRange.get(key));
			else dataPropsRange.put(key, "anyInstance");
		}
		
		allConcepts = tempOntology.getClassesInSignature().stream()
				.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString())).collect(Collectors.toList());
		
//		System.out.println(objectPropsDomain);
//		System.out.println(objectPropsRange);
//		System.out.println(dataPropsDomain);
//		System.out.println(dataPropsRange);
		
		if ( classCount > 0 ) {
			int count = 0;
			while ( count < classCount ) {
				Collections.shuffle(allConcepts, new Random(2));
				for ( String item : allConcepts ) {
					if ( CommonFramework.isIsLiteral(item) && app.discardConcepts.containsKey(item) ) continue;
					
					OWLClass classExpression = featurePool.getExclusiveClass(item.trim());
					OWLIndividual individual = factory.getOWLNamedIndividual(randomInvidual(item,count));

					ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLClassAssertionAxiom(classExpression, individual));
//					System.out.println(factory.getOWLClassAssertionAxiom(classExpression, individual));
					
					count++;
					if ( count >= classCount ) break;
				}
			}	
		}
		System.out.println(" -------------------------------------------------------------- ");
		if ( objectCount > 0 && objectPropsDomain.size() > 0 && objectPropsRange.size() > 0 ) {
			int count = 0;
			while ( count < objectCount ) {
				ArrayList<String> terms = new ArrayList<String>(objectPropsDomain.keySet());
				Collections.shuffle(terms, new Random(2));
				for ( String item : terms ) {
					if ( CommonFramework.isIsLiteral(item) && app.discardProperties.containsKey(item) ) {
//						System.out.println(item);
						continue;
					}
					OWLIndividual object = null; String chosenDomain = "";
						
					if ( hasKeyPropMap.containsKey(item.trim()) ) chosenDomain = randomInvidual(hasKeyPropMap.get(item.trim()),count);
					else chosenDomain = randomInvidual(objectPropsDomain.get(item),count);
						
					OWLIndividual subject = factory.getOWLNamedIndividual(chosenDomain);
					OWLObjectProperty property = featurePool.getExclusiveProperty(item.trim());
					
					String domainRoot = chosenDomain.contains("_") ? chosenDomain.substring(0,chosenDomain.indexOf("_")) : chosenDomain;
					String rangeRoot = objectPropsRange.get(item).contains("_") ? 
							objectPropsRange.get(item).substring(0,objectPropsRange.get(item).indexOf("_")).toLowerCase() 
							: objectPropsRange.get(item).toLowerCase();

//					System.out.println(item+" || "+chosenDomain+" || "+domainRoot+" || "+rangeRoot);

					if ( domainRoot.contains(rangeRoot) ) object = factory.getOWLNamedIndividual(chooseDifferent(domainRoot, count));	
					else object = factory.getOWLNamedIndividual(randomInvidual(objectPropsRange.get(item),count));
					
					ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLObjectPropertyAssertionAxiom(property, subject, object));
//					System.out.println(factory.getOWLObjectPropertyAssertionAxiom( property, subject,  object));
					
					count++;
					if ( count >= objectCount ) break;
				}
			}
		}
		System.out.println(" -------------------------------------------------------------- ");
		if ( dataCount > 0 && dataPropsDomain.size() > 0 && dataPropsRange.size() > 0 ) {
			int count = 0;
			while ( count < dataCount ) {
				ArrayList<String> terms = new ArrayList<String>(dataPropsDomain.keySet());
				Collections.shuffle(terms, new Random(2));
				for ( String item : terms ) {
					if ( CommonFramework.isIsLiteral(item) ) continue;
					
					OWLIndividual subject = factory.getOWLNamedIndividual(randomInvidual(dataPropsDomain.get(item),count));
					OWLDataProperty property = factory.getOWLDataProperty(item.trim(), pm);
					OWLLiteral object = null;
					
					if ( CommonFramework.isIsLiteral(dataPropsDomain.get(item)) ) object = randomDataRange(dataPropsRange.get(item),true);
					else object = randomDataRange(dataPropsRange.get(item),false);
					
					ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyAssertionAxiom(property,subject,object));
//					System.out.println(factory.getOWLDataPropertyAssertionAxiom(property,subject,object));
					
					count++;
					if ( count >= dataCount ) break;
				}
			}
		}
	}
	
	public static String chooseDifferent(String item, int count) {
		ArrayList<String> alreadyIndi = new ArrayList<String>(allConcepts);
		Collections.shuffle(alreadyIndi, new Random(2));
		
		for ( String iter : alreadyIndi ) {
			if ( !iter.contains(item) ) return iter+"_"+String.valueOf(count);
		}
		
		return "randomConcept_"+String.valueOf(count);
	}
	
	public static String randomInvidual(String key, int count) {
		String result = "";
		if ( !key.equals("anyInstance") ) {	
			String item = key.contains("_") ? key.substring(0,key.indexOf("_")) : key;
			String num = Util.commonConstructs.containsKey(item) ? String.valueOf(Util.commonConstructs.get(item)) : "";

			result = key.toLowerCase()+num+"_"+String.valueOf(count);
		} else {
			ArrayList<String> alreadyIndi = new ArrayList<String>(allConcepts);
			Collections.shuffle(alreadyIndi, new Random(2));
			
			result = alreadyIndi.get(0).toLowerCase()+"_"+String.valueOf(count);
		}
//		System.out.println(result.trim());
		return result.trim();
	}
	
	public static OWLLiteral randomDataRange(String key, boolean known) {
		Random random = new Random();
		OWLLiteral result = null;
		int caseDataType = known == false ? random.nextInt(9) : -1;
		
		if ( caseDataType == 0 || caseDataType == 1 || key.equals("xsd:integer") || key.equals("xsd:int") || key.equals("xsd:positiveInteger") 
				|| key.equals("xsd:nonNegativeInteger") || key.equals("xsd:unsignedInt") 
				|| key.equals("xsd:unsignedShort") || key.equals("xsd:short") ) 
			result = factory.getOWLLiteral(random.nextInt(1000));
		
		else if ( caseDataType == 2 || key.equals("xsd:decimal") || key.equals("xsd:float") ) 
			result = factory.getOWLLiteral(random.nextFloat());
		
		else if ( caseDataType == 3 || key.equals("xsd:long") || key.equals("xsd:unsignedLong") ) 
			result = factory.getOWLLiteral(random.nextLong());
		
		else if ( caseDataType == 4 || key.equals("xsd:double") ) result = factory.getOWLLiteral(random.nextDouble());
		else if ( caseDataType == 5 || key.equals("xsd:boolean") ) result = factory.getOWLLiteral(random.nextBoolean()); 
		else result = factory.getOWLLiteral(RandomStringUtils.randomAlphabetic(6));
		
		return result;
	}
}
