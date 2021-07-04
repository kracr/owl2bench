package owl.cs.myfirst.owlapi.Features;

import java.awt.List;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class ClassEnumerationcategory {
	
	  protected static OWLDataFactory factory;
	  protected static FeaturePool featurePool;
	  protected static PrefixManager pm;
	  protected static OWLOntology ontology;
	
	public ClassEnumerationcategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) throws ClassNotFoundException, IOException {
	  ClassEnumerationcategory.factory = factory;
	  ClassEnumerationcategory.pm = pm;
	  ClassEnumerationcategory.featurePool = featurePool;
	  ClassEnumerationcategory.ontology = ontology;
	}
	  
	public void convertLineToOwlClass(ArrayList allConcepts) {
		String prop = (String) allConcepts.get(0);
		OWLClass owlClass = featurePool.getExclusiveClass(prop);
	    OWLAxiom axiom = factory.getOWLDeclarationAxiom(owlClass);
	    ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
	}

	public void convertLineToObjectComplement(ArrayList allConcepts) {
		String subject = (String) allConcepts.get(0);
		String object= (String) allConcepts.get(1);
		OWLClass nonComplement = featurePool.getExclusiveClass(object);
	    OWLObjectComplementOf complement = factory.getOWLObjectComplementOf(nonComplement);
	    OWLClass owlClass = featurePool.getExclusiveClass(subject);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(owlClass, complement));
	}
	
	public void convertLineToObjectIntersection(ArrayList allConcepts) {
		ArrayList<OWLClass> props = new ArrayList<OWLClass>();
		for ( int i = 1 ; i < allConcepts.size() ; i++ ) {
			OWLClass obj = featurePool.getExclusiveClass((String)allConcepts.get(i));
			props.add(obj);
		}
		OWLObjectIntersectionOf spork = factory.getOWLObjectIntersectionOf(props); 
		OWLClass owlClass = featurePool.getExclusiveClass((String)allConcepts.get(0));
		OWLAxiom axiom = factory.getOWLEquivalentClassesAxiom(owlClass, spork);
		ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
	}
	
	public void convertLineToObjectOneOf(ArrayList allConcepts) {
		ArrayList<OWLNamedIndividual> props = new ArrayList<OWLNamedIndividual>();
		for ( int i = 1 ; i < allConcepts.size() ; i++ ) {
			OWLNamedIndividual obj = factory.getOWLNamedIndividual((String)allConcepts.get(i), pm);
			props.add(obj);
		}
		OWLObjectOneOf oneOf = factory.getOWLObjectOneOf(props); 
		OWLClass oneOfClass = featurePool.getExclusiveClass((String)allConcepts.get(0));
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLEquivalentClassesAxiom(oneOfClass, oneOf));
	}
	
	public void convertLineToObjectUnionOf(ArrayList allConcepts) {
		ArrayList<OWLClass> props = new ArrayList<OWLClass>();
		for ( int i = 1 ; i < allConcepts.size() ; i++ ) {
			OWLClass obj = featurePool.getExclusiveClass((String)allConcepts.get(i));
			props.add(obj);
		}
		OWLObjectUnionOf unionOf = factory.getOWLObjectUnionOf(props); 
		OWLClass unionClass = featurePool.getExclusiveClass((String)allConcepts.get(0));
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(unionClass, unionOf));
	}
}
