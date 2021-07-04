package owl.cs.myfirst.owlapi.Features;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;

import owl.cs.myfirst.owlapi.Features.ClassEnumerationcategory;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class DataPropertyAxiomsCategory {
	protected static OWLDataFactory factory;
	  protected static FeaturePool featurePool;
	  protected static PrefixManager pm;
	  protected static OWLOntology ontology;
	  
	  public DataPropertyAxiomsCategory() { }
	  
	public DataPropertyAxiomsCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) throws ClassNotFoundException, IOException {
		DataPropertyAxiomsCategory.factory = factory;
		DataPropertyAxiomsCategory.pm = pm;
		DataPropertyAxiomsCategory.featurePool = featurePool;
		DataPropertyAxiomsCategory.ontology = ontology;
	}

	public static void convertLineToAllDisjointDataProperties(ArrayList<String> allConcepts) {
		ArrayList<OWLDataProperty> props = new ArrayList<OWLDataProperty>();
		for ( String key : allConcepts ) {
			OWLDataProperty obj = factory.getOWLDataProperty(key, pm);
			props.add(obj);
		}
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDisjointDataPropertiesAxiom(props));
	}
	
	public static void convertLineToDataPropertyDisjointWith(ArrayList<String> allConcepts) {
		ArrayList<OWLDataProperty> props = new ArrayList<OWLDataProperty>();
		for ( String key : allConcepts ) {
			OWLDataProperty obj = factory.getOWLDataProperty(key, pm);
			props.add(obj);
		}
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDisjointDataPropertiesAxiom(props.get(0),props.get(1)));
	}
	
	public static void convertLineToEquivalentDataProperty(ArrayList<String> allConcepts) {
		ArrayList<OWLDataProperty> props = new ArrayList<OWLDataProperty>();
		for ( String key : allConcepts ) {
			OWLDataProperty obj =  factory.getOWLDataProperty(key, pm);
			props.add(obj);
		}
//		for ( int i = 0 ; i < props.size() ; i++ ) {
//			for ( int j = i+1 ; j < props.size() ; j++ ) {
//				 ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLEquivalentDataPropertiesAxiom(props.get(i),props.get(j)));
//				//addAxiomToOntology(factory.getOWLEquivalentDataPropertiesAxiom(props.get(i),props.get(j)));
//			}
//		}
		ontology.getOWLOntologyManager().addAxiom(ontology,factory.getOWLEquivalentDataPropertiesAxiom(props));
	}
	
	public static void convertLineToFunctionalDataProperty(ArrayList<String> allConcepts) {
		String prop = allConcepts.get(0);
		OWLDataProperty dataProperty = factory.getOWLDataProperty(prop, pm);
	    OWLDatatype datatype = OWL2Datatype.XSD_NON_NEGATIVE_INTEGER.getDatatype(factory);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLFunctionalDataPropertyAxiom(dataProperty));
	}
	
	public static void convertLineToRdfsDataDomain(ArrayList<String> allConcepts) {
		String subject = allConcepts.get(0);
		String object = allConcepts.get(1);
		OWLClass domain = featurePool.getExclusiveClass(object);
	    OWLDataProperty objectProperty = factory.getOWLDataProperty(subject, pm);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(objectProperty, domain));
	}
	
	public static void convertLineToRdfsDataRange(ArrayList<String> allConcepts) {
		String subject = allConcepts.get(0);
		String object = allConcepts.get(1);
		OWLDatatype datatype = OWL2Datatype.RDFS_LITERAL.getDatatype(factory);
	    OWLDataProperty objectProperty = factory.getOWLDataProperty(subject, pm);
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyRangeAxiom(objectProperty, datatype));
	}
	
	public static void convertLineToRdfsDataSubPropertyOf(ArrayList<String> allConcepts) {
		String subject = allConcepts.get(0);
		String object= allConcepts.get(1);
	    if(object.contains("OWL:topDataProperty")) {
	    	OWLDataProperty subProperty = factory.getOWLDataProperty(subject, pm);
	    	OWLDataProperty superProperty = factory.getOWLTopDataProperty();
	    	ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubDataPropertyOfAxiom(subProperty, superProperty));
		}
		else {
			OWLDataProperty subProperty = factory.getOWLDataProperty(subject, pm);
		    OWLDataProperty superProperty = factory.getOWLDataProperty(object, pm);
		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubDataPropertyOfAxiom(subProperty, superProperty));
	}}
}
