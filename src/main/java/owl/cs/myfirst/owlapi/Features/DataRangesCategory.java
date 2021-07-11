package owl.cs.myfirst.owlapi.Features;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataComplementOf;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataIntersectionOf;
import org.semanticweb.owlapi.model.OWLDataOneOf;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataUnionOf;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class DataRangesCategory {
	protected static OWLDataFactory factory;
	protected static FeaturePool featurePool;
	protected static PrefixManager pm;
	protected static OWLOntology ontology;
	
	public DataRangesCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) throws ClassNotFoundException, IOException {
		DataRangesCategory.factory = factory;
		DataRangesCategory.pm = pm;
		DataRangesCategory.featurePool = featurePool;
		DataRangesCategory.ontology = ontology;
	}		
	
	public static void convertLineToDataComplementOf(ArrayList<String> allConcepts) {
		String prop = allConcepts.get(0);
	    OWLDatatype datatype = factory.getOWLDatatype(allConcepts.get(1), pm);
	    OWLDataComplementOf complement = factory.getOWLDataComplementOf(datatype);
	    OWLClass domain = featurePool.getReusableClass();
//	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(factory.getOWLDataProperty(prop, pm), domain));
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyRangeAxiom(factory.getOWLDataProperty(prop, pm), complement));
	    //addToGenericDomainAndNewRange(factory.getOWLDataProperty(prop, pm), complement);
	}
	
	public static void convertLineToDataIntersectionOf(ArrayList<String> allConcepts) {
		ArrayList<OWLDatatype> props = new ArrayList<OWLDatatype>();
		for ( int i = 1 ; i < allConcepts.size() ; i++ ) {
			OWLDatatype obj = factory.getOWLDatatype(allConcepts.get(i), pm);
			props.add(obj);
		}
	    OWLDataIntersectionOf intersection = factory.getOWLDataIntersectionOf(props); 
	    OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(0), pm);
	    OWLClass domain = featurePool.getReusableClass();
//	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(property, domain));
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyRangeAxiom(property, intersection));
	    //addToGenericDomainAndNewRange(property, intersection);
	}
	
	public static void convertLineToDataOneOf(ArrayList<String> allConcepts) {
		OWLDatatype datatype = factory.getOWLDatatype(allConcepts.get(0), pm);
		ArrayList<OWLLiteral> props = new ArrayList<OWLLiteral>();
		for ( int i = 1 ; i < allConcepts.size() ; i=i+2 ) {
//			OWLLiteral obj = factory.getOWLLiteral(allConcepts.get(i), OWL2Datatype.XSD_STRING );
			OWLLiteral obj = factory.getOWLLiteral(allConcepts.get(i),allConcepts.get(i+1));
			props.add(obj);
		}
		OWLDataOneOf owlDataOneOf = factory.getOWLDataOneOf(props);
//		System.out.println(owlDataOneOf);
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDatatypeDefinitionAxiom(datatype, owlDataOneOf));
		//addAxiomToOntology(factory.getOWLDatatypeDefinitionAxiom(datatype, owlDataOneOf));
	}
	
	public static void convertLineToDataUnionOf(ArrayList<String> allConcepts) {
		//hasPublicationDate xsd:dateTime xsd:dateTimeStamp
		ArrayList<OWLDatatype> props = new ArrayList<OWLDatatype>();
		for ( int i = 1 ; i < allConcepts.size() ; i++ ) {
			OWLDatatype obj = factory.getOWLDatatype(allConcepts.get(i), pm);
			props.add(obj);
		}
		OWLDataUnionOf union = factory.getOWLDataUnionOf(props); 
	    OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(0), pm);
	    OWLClass domain = featurePool.getReusableClass();
//	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyDomainAxiom(property, domain));
	    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDataPropertyRangeAxiom(property, union));
//	    addToGenericDomainAndNewRange(property, union);
	}
}

