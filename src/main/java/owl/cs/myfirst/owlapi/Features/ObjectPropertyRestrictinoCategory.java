package owl.cs.myfirst.owlapi.Features;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasSelf;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.PrefixManager;
import owl.cs.myfirst.owlapi.app;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class ObjectPropertyRestrictinoCategory {
	protected static OWLDataFactory factory;
	protected static FeaturePool featurePool;
	protected static PrefixManager pm;
	protected static OWLOntology ontology;
	
	public ObjectPropertyRestrictinoCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) throws ClassNotFoundException, IOException {
		ObjectPropertyRestrictinoCategory.factory = factory;
		ObjectPropertyRestrictinoCategory.pm = pm;
		ObjectPropertyRestrictinoCategory.featurePool = featurePool;
		ObjectPropertyRestrictinoCategory.ontology = ontology;
	}
	
	public static void convertLineToObjectAllValuesFrom(ArrayList<String> allConcepts) {
		String p0 = allConcepts.get(0);
		String p1 = allConcepts.get(1);
		String p2 = allConcepts.get(2);
//		OWLObjectProperty property = featurePool.getExclusiveProperty(p1);
//	    OWLClass range = featurePool.getExclusiveClass(p4);
//	    OWLClass qualifier = featurePool.getExclusiveClass(p0);
//	    OWLObjectMaxCardinality maxCardinality = factory.getOWLObjectMaxCardinality(Integer.parseInt(p3), property, qualifier);
//	    addAxiomToOntology(factory.getOWLSubClassOfAxiom(range, maxCardinality));
	    OWLObjectProperty property = featurePool.getExclusiveProperty(p1);
	    OWLClass range = featurePool.getExclusiveClass(p2);
	    OWLObjectAllValuesFrom restriction = factory.getOWLObjectAllValuesFrom(property, range);
	    OWLClass allValuesFrom = featurePool.getExclusiveClass(p0);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(allValuesFrom, restriction));
	    
	    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(allValuesFrom, restriction));
	}
	
	public static void convertLineToObjectHasSelf(ArrayList<String> allConcepts) {
		String subject = allConcepts.get(0);
		String object = allConcepts.get(1);
	    OWLObjectProperty property = featurePool.getExclusiveProperty(object);
	    OWLClass hasSelf = featurePool.getExclusiveClass(subject);
	    OWLObjectHasSelf restriction = factory.getOWLObjectHasSelf(property);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLEquivalentClassesAxiom(hasSelf, restriction));
	    
	    app.notToBeIncludedAxioms.add(factory.getOWLEquivalentClassesAxiom(hasSelf, restriction));
	}
	
	public static void addObjectHasValueIndidividuals(String indiv, String classTerm) throws IOException {
		HashMap<String, String> namedIndiviMap = new HashMap<String,String>();
		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/DL/OwlNamedIndividual.txt"));
		String line = reader.readLine();
		while ( (line = reader.readLine()) != null ) {
			if ( line.length() > 0 ) {
				String[] outputs = line.split(" classAssertion ");
				namedIndiviMap.put(outputs[1], outputs[0]);
			}
		}
		reader.close();
		
		if ( namedIndiviMap.containsKey(classTerm) ) app.alreadyAssertionAxiom.put(indiv, namedIndiviMap.get(classTerm));
	}
	
	public static void convertLineToObjectHasValue(ArrayList<String> allConcepts) throws IOException {
		String p1 = allConcepts.get(0);
		String p2 = allConcepts.get(1);
		String p3 = allConcepts.get(2);
	    OWLObjectProperty property = featurePool.getExclusiveProperty(p2);
	    OWLIndividual value = factory.getOWLNamedIndividual(p3, pm);
	    OWLObjectHasValue restriction = factory.getOWLObjectHasValue(property, value);
	    OWLClass hasValue = featurePool.getExclusiveClass(p1);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(hasValue, restriction));
	    
	    addObjectHasValueIndidividuals(p1,p3);
	    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(hasValue, restriction));
	}
	
	public static void convertLineToObjectSomeValuesFrom(ArrayList<String> allConcepts) {
		String p0 = allConcepts.get(0);
		String p1 = allConcepts.get(1);
		String p2 = allConcepts.get(2);
	    OWLObjectProperty property = featurePool.getExclusiveProperty(p1);
	    OWLClass range = featurePool.getExclusiveClass(p2);
	    OWLClass someValuesFrom = featurePool.getExclusiveClass(p0);
	    OWLObjectSomeValuesFrom restriction = factory.getOWLObjectSomeValuesFrom(property, range);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(someValuesFrom, restriction));
	    
	    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(someValuesFrom, restriction));
	}
	
	public static void convertLineToObjectMaxQualifiedCardinality(ArrayList<String> allConcepts) {
		String p0 = allConcepts.get(0);
		String p1 = allConcepts.get(1);
		String p3 = allConcepts.get(2);
		if ( p3.contains("_") ) {
			p3 = p3.substring(0,p3.indexOf("_"));
		}
		String p4 = allConcepts.get(3);
		OWLObjectProperty property = featurePool.getExclusiveProperty(p1);
	    OWLClass range = featurePool.getExclusiveClass(p4);
	    OWLClass qualifier = featurePool.getExclusiveClass(p0);
	    OWLObjectMaxCardinality maxCardinality = factory.getOWLObjectMaxCardinality(Integer.parseInt(p3), property, qualifier);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(range, maxCardinality));
	    
	    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(range, maxCardinality));
	}
	
	public static void convertLineToObjectMinQualifiedCardinality(ArrayList<String> allConcepts) {
		String p0 = allConcepts.get(0);
		String p1 = allConcepts.get(1);
		String p3 = allConcepts.get(2);
		if ( p3.contains("_") ) {
			p3 = p3.substring(0,p3.indexOf("_"));
		}
		String p4 = allConcepts.get(3);
		OWLObjectProperty property = featurePool.getExclusiveProperty(p1);
	    OWLClass range = featurePool.getExclusiveClass(p4);
	    OWLClass qualifier = featurePool.getExclusiveClass(p0);
	    OWLObjectMinCardinality minCardinality = factory.getOWLObjectMinCardinality(Integer.parseInt(p3), property, qualifier);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(range, minCardinality));
	    
	    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(range, minCardinality));
	}
	
	public static void convertLineToObjectQualifiedCardinality(ArrayList<String> allConcepts) {
		String p0 = allConcepts.get(0);
		String p1 = allConcepts.get(1);
		String p3 = allConcepts.get(2);
		if ( p3.contains("_") ) {
			p3 = p3.substring(0,p3.indexOf("_"));
		}
		String p4 = allConcepts.get(3);
		OWLObjectProperty property = featurePool.getExclusiveProperty(p1);
	    OWLClass range = featurePool.getExclusiveClass(p4);
	    OWLClass qualifier = featurePool.getExclusiveClass(p0);
	    OWLObjectExactCardinality exactCardinality = factory.getOWLObjectExactCardinality(Integer.parseInt(p3), property, qualifier);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(range, exactCardinality));
	    
	    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(range, exactCardinality));
	}
}
