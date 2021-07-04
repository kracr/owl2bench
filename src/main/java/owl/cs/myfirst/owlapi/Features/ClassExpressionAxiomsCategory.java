package owl.cs.myfirst.owlapi.Features;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class ClassExpressionAxiomsCategory {
	 public static OWLDataFactory factory;
	  public static FeaturePool featurePool;
	  public PrefixManager pm;
	  public static OWLOntology ontology;
	
	  public ClassExpressionAxiomsCategory() { }
	  
	public ClassExpressionAxiomsCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) throws ClassNotFoundException, IOException {
//		ClassExpressionAxiomsCategory.factory = factory;
//		ClassExpressionAxiomsCategory.pm = pm;
//		ClassExpressionAxiomsCategory.featurePool = featurePool;
//		ClassExpressionAxiomsCategory.ontology = ontology;
		this.factory = factory;
		this.pm = pm;
		this.featurePool = featurePool;
		this.ontology = ontology;
	}
	
	public static void convertLineToOwlAxiom(String prop) {
		OWLClass owlClass = featurePool.getExclusiveClass(prop);
	    OWLAxiom axiom = factory.getOWLDeclarationAxiom(owlClass);
	    ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
	}
	
	public static void convertLineToRdfsSubClassOf(ArrayList<String> allConcepts) {
		String subject = allConcepts.get(0);
		String object = allConcepts.get(1);
	    OWLClass superClass = featurePool.getExclusiveClass(object);
	    OWLClass subClass = featurePool.getExclusiveClass(subject);
	    OWLAxiom subClassOfAxiom = factory.getOWLSubClassOfAxiom(subClass, superClass);
	    ontology.getOWLOntologyManager().addAxiom(ontology, subClassOfAxiom);
	}
	
	public static void convertLineToEquivalentClass(ArrayList<String> allConcepts) {
		ArrayList<OWLClass> props = new ArrayList<OWLClass>();
		for ( String key : allConcepts ) {
			OWLClass obj =  featurePool.getExclusiveClass(key);
			props.add(obj);
		}
//		for ( int i = 0 ; i < props.size() ; i++ ) {
//			for ( int j = i+1 ; j < props.size() ; j++ ) {
//				OWLAxiom axiom = factory.getOWLEquivalentClassesAxiom(props.get(i),props.get(j));
//				ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
//			}
//		}
		ontology.getOWLOntologyManager().addAxiom(ontology,factory.getOWLEquivalentClassesAxiom(props));
	}
	
	public static void convertLineToDisjointWith(ArrayList<String> allConcepts) {
		ArrayList<OWLClass> props = new ArrayList<OWLClass>();
		for ( String key : allConcepts ) {
			OWLClass obj =  featurePool.getExclusiveClass(key);
			props.add(obj);
		}
		for ( int i = 0 ; i < props.size() ; i++ ) {
			for ( int j = i+1 ; j < props.size() ; j++ ) {
				ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDisjointClassesAxiom(props.get(i),props.get(j)));
			}
		}
	}
	
	public static void convertLineToDisjointUnion(ArrayList<String> allConcepts) {
		ArrayList<OWLClass> props = new ArrayList<OWLClass>();
		OWLClass disjointUnionClass = featurePool.getExclusiveClass(allConcepts.get(0));
		for ( int i = 1 ; i < allConcepts.size() ; i++ ) {
		    OWLClass obj = featurePool.getExclusiveClass(allConcepts.get(i));
		    props.add(obj);
		}
		OWLAxiom axiom = factory.getOWLDisjointUnionAxiom(disjointUnionClass,props);
		ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
	
	} 
	
	public static void convertLineToAllDisjointClasses(ArrayList<String> allConcepts) {
		ArrayList<OWLClass> props = new ArrayList<OWLClass>();
		for ( String key : allConcepts ) {
			OWLClass obj = featurePool.getExclusiveClass(key);
			props.add(obj);
		}
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDisjointClassesAxiom(props));
	}
}
