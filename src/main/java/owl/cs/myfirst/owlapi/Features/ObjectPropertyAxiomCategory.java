package owl.cs.myfirst.owlapi.Features;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;

import com.cs.myfirst.owlapi.Features.BoilerplateCode.CommonFramework;

import owl.cs.myfirst.owlapi.app;
import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class ObjectPropertyAxiomCategory {
	protected static OWLDataFactory factory;
	  protected static FeaturePool featurePool;
	  protected static PrefixManager pm;
	  protected static OWLOntology ontology;
	  
	  public ObjectPropertyAxiomCategory() {}
	  
	  public ObjectPropertyAxiomCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) throws ClassNotFoundException, IOException, OWLOntologyCreationException {
			ObjectPropertyAxiomCategory.factory = factory;
			ObjectPropertyAxiomCategory.pm = pm;
			ObjectPropertyAxiomCategory.featurePool = featurePool;
			ObjectPropertyAxiomCategory.ontology = ontology;
	  }
	  
		public static void convertLineToAllDisjointObjectProperties(ArrayList<String> allConcepts) {
			ArrayList<OWLObjectProperty> props = new ArrayList<OWLObjectProperty>();
			for ( String key : allConcepts ) {
				OWLObjectProperty obj = featurePool.getExclusiveProperty(key);
				props.add(obj);
			}
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(props));
		}
		
		public static void convertLineToObjectPropertyDisjointWith(ArrayList<String> allConcepts) {
			ArrayList<OWLObjectProperty> props = new ArrayList<OWLObjectProperty>();
			for ( String key : allConcepts ) {
				OWLObjectProperty obj = featurePool.getExclusiveProperty(key);
				props.add(obj);
			}
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLDisjointObjectPropertiesAxiom(props.get(0),props.get(1)));
		}
		
		public static void convertLineToAsymmetricProperty(ArrayList allConcepts) {
			String prop = (String) allConcepts.get(0);
			OWLObjectProperty property = featurePool.getExclusiveProperty(prop);
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLAsymmetricObjectPropertyAxiom(property));
		}
		
		public static void convertLineToEquivalentObjectProperty(ArrayList<String> allConcepts) {
			ArrayList<OWLObjectProperty> props = new ArrayList<OWLObjectProperty>();
			for ( String key : allConcepts ) {
				OWLObjectProperty obj = featurePool.getExclusiveProperty(key);
				props.add(obj);
			}
//			for ( int i = 0 ; i < props.size() ; i++ ) {
//				for ( int j = i+1 ; j < props.size() ; j++ ) {
//					ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLEquivalentObjectPropertiesAxiom(props.get(i),props.get(j)));
//				}
//			}
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLEquivalentObjectPropertiesAxiom(props));
		}
		
		public static void convertLineToFunctionalObjectProperty(ArrayList<String> allConcepts) {
			String prop = allConcepts.get(0);
			OWLObjectProperty property = featurePool.getExclusiveProperty(prop);
			 ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLFunctionalObjectPropertyAxiom(property)); 
		}
		
		public static void convertLineToInverseFunctionalProperty(ArrayList<String> allConcepts) {
			String prop = allConcepts.get(0);
			OWLObjectProperty property = featurePool.getExclusiveProperty(prop);
		    OWLAxiom axiom = factory.getOWLInverseFunctionalObjectPropertyAxiom(property);
		    ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
		}
		
		public static void convertLineToInverseOfProperty(ArrayList<String> allConcepts) {
			String subject = allConcepts.get(0);
			String object = allConcepts.get(1);
			OWLObjectProperty p1 = featurePool.getExclusiveProperty(subject);
			OWLObjectProperty p2 = featurePool.getExclusiveProperty(object);	
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLInverseObjectPropertiesAxiom(p1, p2));
		}
		
		public static void convertLineToIrreflexiveProperty(ArrayList<String> allConcepts) {
			String prop = allConcepts.get(0);
			OWLObjectProperty property = featurePool.getExclusiveProperty(prop);
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLIrreflexiveObjectPropertyAxiom(property));
		}
		
		public static void convertLineToObjectProperty(ArrayList<String> allConcepts) {
			String subject = allConcepts.get(0);
		    OWLObjectProperty objectProperty = featurePool.getExclusiveProperty(subject);
		    OWLClass domain = featurePool.getReusableClass();
//		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(objectProperty, domain));
//		    ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLObjectPropertyRangeAxiom(objectProperty, range));
		}
		
		public static void convertLineToPropertyChainAxiom(ArrayList<String> allConcepts) {
			ArrayList<OWLObjectProperty> props = new ArrayList<OWLObjectProperty>();
			for ( int i = 0 ; i < allConcepts.size()-1 ; i++ ) {
				OWLObjectProperty obj = featurePool.getExclusiveProperty(allConcepts.get(i));
				props.add(obj);
			}
			OWLObjectProperty superProp = featurePool.getExclusiveProperty(allConcepts.get(allConcepts.size()-1));
			
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSubPropertyChainOfAxiom(props,superProp));
		}
		
		public static void convertLineToReflexiveProperty(ArrayList<String> allConcepts) {
			String prop = allConcepts.get(0);
			OWLObjectProperty property = featurePool.getExclusiveProperty(prop);
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLReflexiveObjectPropertyAxiom(property));
		}
		
		public static void convertLineToSymmetricProperty(ArrayList<String> allConcepts) {
			String prop = allConcepts.get(0);
			OWLObjectProperty property = featurePool.getExclusiveProperty(prop);
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLSymmetricObjectPropertyAxiom(property));
		}
		
		public static void convertLineToTransitiveProperty(ArrayList<String> allConcepts) {
			String prop = allConcepts.get(0);
			OWLObjectProperty property = featurePool.getExclusiveProperty(prop);
			ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLTransitiveObjectPropertyAxiom(property));
		} 
		
		public static void convertLineToRdfsObjectDomain(ArrayList<String> allConcepts) {
			String subject = allConcepts.get(0);
			String object = allConcepts.get(1);

			OWLClass domain = featurePool.getExclusiveClass(object);
			OWLObjectProperty property = featurePool.getExclusiveProperty(subject);
			OWLAxiom axiom = factory.getOWLObjectPropertyDomainAxiom(property,domain);
			ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
		}
		
		public static void convertLineToRdfsObjectRange(ArrayList<String> allConcepts) {
			String subject = allConcepts.get(0);
			String object = allConcepts.get(1);
			
			OWLClass range = featurePool.getExclusiveClass(object);
			OWLObjectProperty property = featurePool.getExclusiveProperty(subject);
			OWLAxiom axiom = factory.getOWLObjectPropertyRangeAxiom(property,range);
			ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
		}
		
		public static void convertLineToRdfsObjectSubPropertyOf(ArrayList<String> allConcepts) {
			String subject = allConcepts.get(0);
			String object= allConcepts.get(1);
			if(object.equals("OWL:TopObjectProperty")) {
				OWLObjectProperty property = factory.getOWLTopObjectProperty();
				OWLObjectProperty subproperty = featurePool.getExclusiveProperty(subject);
				OWLAxiom axiom = factory.getOWLSubObjectPropertyOfAxiom(subproperty,property);
				ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
			}
			else {
				OWLObjectProperty property = featurePool.getExclusiveProperty(object);
				OWLObjectProperty subproperty = featurePool.getExclusiveProperty(subject);
				OWLAxiom axiom = factory.getOWLSubObjectPropertyOfAxiom(subproperty,property);
				ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
			}
		}
}
