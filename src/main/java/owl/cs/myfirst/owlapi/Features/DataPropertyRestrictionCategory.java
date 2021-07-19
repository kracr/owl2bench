package owl.cs.myfirst.owlapi.Features;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;

import owl.cs.myfirst.owlapi.app;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class DataPropertyRestrictionCategory {
	protected static OWLDataFactory factory;
	protected static FeaturePool featurePool;
	protected static PrefixManager pm;
	protected static OWLOntology ontology;
	
		public DataPropertyRestrictionCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) throws ClassNotFoundException, IOException {
		  DataPropertyRestrictionCategory.factory = factory;
		  DataPropertyRestrictionCategory.pm = pm;
		  DataPropertyRestrictionCategory.featurePool = featurePool;
		  DataPropertyRestrictionCategory.ontology = ontology;
		}
	  
		public static void convertLineToDataAllValues(ArrayList<String> allConcepts) {
			//Person hasEmailAddress xsd:string
		    OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(1), pm);
		    OWLDatatype value = factory.getOWLDatatype(allConcepts.get(2), pm);
		    OWLDataAllValuesFrom restriction = factory.getOWLDataAllValuesFrom(property, value);
		    OWLClass allValuesFrom = featurePool.getExclusiveClass(allConcepts.get(0));
		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(allValuesFrom, restriction));
		    
		    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(allValuesFrom, restriction));
		}
		
		public static void convertLineToDataHasValue(ArrayList<String> allConcepts) {	
			//RetiringPerson hasAge 59 
		    OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(1), pm);
		    OWLDataHasValue restriction = factory.getOWLDataHasValue(property, factory.getOWLLiteral(allConcepts.get(2)));
		    OWLClass hasValue = featurePool.getExclusiveClass(allConcepts.get(0));
		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(hasValue, restriction));
		    
		    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(hasValue, restriction));
		}
		
		public static void convertLineToDataSomeValuesFrom(ArrayList<String> allConcepts) {		
			//Person hasName xsd:string
		    OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(1), pm);		    
		    OWLDatatype datatype = factory.getOWLDatatype(allConcepts.get(2), pm);
		    OWLClass someValuesFrom = featurePool.getExclusiveClass(allConcepts.get(0));
		    OWLDataSomeValuesFrom restriction = factory.getOWLDataSomeValuesFrom(property, datatype);
		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(someValuesFrom, restriction));
		    
		    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(someValuesFrom, restriction));
		}
		
		public static void convertLineToDataMaxQualifiedCardinality(ArrayList<String> allConcepts) {	
			//Person hasTitle 5 xsd:string 
			OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(1), pm);
		    OWLDatatype affectedDatatype = factory.getOWLDatatype(allConcepts.get(3), pm);
		    OWLDataMaxCardinality cardinality = factory.getOWLDataMaxCardinality(Integer.parseInt(allConcepts.get(2)), property, affectedDatatype);
		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(featurePool.getExclusiveClass(allConcepts.get(0)), cardinality));
		    
		    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(featurePool.getExclusiveClass(allConcepts.get(0)), cardinality));
		}
		
		public static void convertLineToDataMinQualifiedCardinality(ArrayList<String> allConcepts) {			
			//Person hasTitle 5 xsd:string 
		    OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(1), pm);
		    OWLDatatype affectedDatatype = factory.getOWLDatatype(allConcepts.get(3), pm);
		    OWLDataMinCardinality cardinality = factory.getOWLDataMinCardinality(Integer.parseInt(allConcepts.get(2)), property, affectedDatatype);
		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(featurePool.getExclusiveClass(allConcepts.get(0)), cardinality));
		    
		    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(featurePool.getExclusiveClass(allConcepts.get(0)), cardinality));
		}
		
		public static void convertLineToDataQualifiedCardinality(ArrayList<String> allConcepts) {		
			//Person hasCode 1 xsd:string
		    OWLDataProperty property = factory.getOWLDataProperty(allConcepts.get(1), pm);
		    OWLDatatype affectedDatatype = factory.getOWLDatatype(allConcepts.get(3), pm);
		    OWLDataExactCardinality cardinality = factory.getOWLDataExactCardinality(Integer.parseInt(allConcepts.get(2)), property, affectedDatatype);
		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubClassOfAxiom(featurePool.getExclusiveClass(allConcepts.get(0)), cardinality));
		    
		    app.notToBeIncludedAxioms.add(factory.getOWLSubClassOfAxiom(featurePool.getExclusiveClass(allConcepts.get(0)), cardinality));
		}
}
