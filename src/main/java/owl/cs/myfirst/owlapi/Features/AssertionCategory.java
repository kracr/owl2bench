package owl.cs.myfirst.owlapi.Features;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.model.parameters.Imports;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;
import com.cs.myfirst.owlapi.Features.BoilerplateCode.LastDance;

import owl.cs.myfirst.owlapi.WriteAxiomsFromOwlApi;
import owl.cs.myfirst.owlapi.app;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class AssertionCategory {
	protected static OWLDataFactory factory;
	protected static FeaturePool featurePool;
	protected static PrefixManager pm;
	protected static OWLOntology ontology;
	
	public AssertionCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) {
		AssertionCategory.factory = factory;
		AssertionCategory.pm = pm;
		AssertionCategory.featurePool = featurePool;
		AssertionCategory.ontology = ontology;
	}
	
	public static void convertLineToHasKey(ArrayList<String> allConcepts) {
		OWLClass hasKeyClass = featurePool.getExclusiveClass(allConcepts.get(0));
		OWLDataProperty hasKeyProperty = factory.getOWLDataProperty(allConcepts.get(1), pm);
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLHasKeyAxiom(hasKeyClass, hasKeyProperty));
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
					.collect(Collectors.toMap(props -> props[1],props -> props[2],(prop1,prop2) -> prop1));
			
			objectRange = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.OBJECT_PROPERTY_RANGE).stream()
					.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
					.collect(Collectors.toMap(props -> props[1],props -> props[2],(prop1,prop2) -> prop1));
			
			dataDomain = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.DATA_PROPERTY_DOMAIN).stream()
					.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
					.collect(Collectors.toMap(props -> props[1],props -> props[2],(prop1,prop2) -> prop1));
			
			dataRange = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.DATA_PROPERTY_RANGE).stream()
					.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
					.collect(Collectors.toMap(props -> props[1],props -> props[2],(prop1,prop2) -> prop1));
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
		
		System.out.println(tempOntology.getAxiomCount()+" || "+ontology.getAxiomCount());
		HashMap<String,String> objectPropsDomain; HashMap<String,String> objectPropsRange; HashMap<String,String> dataPropsDomain; HashMap<String,String> dataPropsRange;
		
//		HashMap<String,String> objectPropsDomain = (HashMap<String, String>) tempOntology.getAxioms(AxiomType.OBJECT_PROPERTY_ASSERTION).stream()
//				.map(props -> WriteAxiomsFromOwlApi.totriple(props.toString()).split(" "))
//				.collect(Collectors.toMap(props -> props[1],props -> props[2],(prop1,prop2) -> prop1));

		System.out.println(ontology.getAxioms(AxiomType.CLASS_ASSERTION));
		System.out.println(ontology.getAxioms(AxiomType.DATA_PROPERTY_ASSERTION));
		System.out.println(ontology.getAxioms(AxiomType.OBJECT_PROPERTY_ASSERTION));
	
		if ( classCount > 0 ) {
			int count = 0;
			while ( count < objectCount ) {
				String[] terms = (String[]) tempOntology.getAxioms(AxiomType.CLASS_ASSERTION).toArray();
				Collections.shuffle(Arrays.asList(terms), new Random(2));
				for ( String item : terms ) {
					
					OWLClass classExpression = featurePool.getExclusiveClass(item);
					OWLIndividual individual = factory.getOWLNamedIndividual(randomInvidual(item));
					
					ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLClassAssertionAxiom(classExpression, individual));
					if ( count >= objectCount ) break;
				}
			}	
		}
		
//		if ( objectCount > 0 && objectPropsDomain.size() > 0 && objectPropsRange.size() > 0 ) {
//			int count = 0;
//			while ( count < objectCount ) {
//				ArrayList<String> terms = new ArrayList<String>(objectPropsDomain.keySet());
//				Collections.shuffle(terms, new Random(2));
//				for ( String item : terms ) {
//					
//					OWLIndividual subject = factory.getOWLNamedIndividual(randomInvidual(objectPropsDomain.get(item)));
//					OWLIndividual object = factory.getOWLNamedIndividual(randomInvidual(objectPropsRange.get(item)));
//					OWLObjectProperty property = featurePool.getExclusiveProperty(item);
//					
//					ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLObjectPropertyAssertionAxiom( property, subject,  object));
//					if ( count >= objectCount ) break;
//				}
//			}
//		}
		
//		if ( dataCount > 0 && dataPropsDomain.size() > 0 && dataPropsRange.size() > 0 ) {
//			int count = 0;
//			while ( count < objectCount ) {
//				ArrayList<String> terms = new ArrayList<String>(dataPropsDomain.keySet());
//				Collections.shuffle(terms, new Random(2));
//				for ( String item : terms ) {
//					
//					OWLIndividual subject = factory.getOWLNamedIndividual(randomInvidual(dataPropsDomain.get(item)));
//					OWLObjectProperty property = featurePool.getExclusiveProperty(item);
//					if ( CommonFramework.isIsLiteral(dataPropsDomain.get(item)) ) {
//						
//					} else {
//						
//					}
//					
//					ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyAssertionAxiom(property,subject,  object));
//					if ( count >= objectCount ) break;
//				}
//			}
//		}
	}
	
	public static String randomInvidual(String key) {
		String result = "";
		if ( !key.equals("anyInstance") ) {
			int num = key.contains("_") ? Integer.parseInt(key.substring(key.indexOf("_")+1)) : 0;
			
		} else {
			
		}
		return result;
	}
}
