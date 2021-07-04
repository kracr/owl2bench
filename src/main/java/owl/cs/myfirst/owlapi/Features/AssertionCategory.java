package owl.cs.myfirst.owlapi.Features;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.PrefixManager;

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
	
	public static void convertLineToOwlHasKey(ArrayList<String> allConcepts) {
		OWLClass hasKeyClass = featurePool.getExclusiveClass(allConcepts.get(0));
		OWLDataProperty hasKeyProperty = factory.getOWLDataProperty(allConcepts.get(1), pm);
		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLHasKeyAxiom(hasKeyClass, hasKeyProperty));
	}
	
	
//	protected void addProperty(OWLClass domain, OWLObjectProperty property, OWLClassExpression range) {
//	   addAxiomToOntology(factory.getOWLObjectPropertyDomainAxiom(property, domain));
//	   addAxiomToOntology(factory.getOWLObjectPropertyRangeAxiom(property, range));
//	}
//	protected void addProperty(OWLClass domain, OWLDataProperty property, OWLDataRange range) {
//	   addAxiomToOntology(factory.getOWLDataPropertyDomainAxiom(property, domain));
//	   addAxiomToOntology(factory.getOWLDataPropertyRangeAxiom(property, range));
//	}
//	protected void addAxiomToOntology(OWLAxiom axiom) {
//	    ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
//	}
//	public void initDL() {
//		String construct = " hasKey ";
//		String constructpath = "/DL/OwlHasKeyFeature.txt";
//		BufferedReader reader;
//		try {
//			reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+constructpath));
//			String line = reader.readLine();
//			while (line != null) {
//				String[] contents = line.split(construct);
//				OWLClass hasKeyClass = featurePool.getExclusiveClass(contents[0]);
//			    OWLDataProperty hasKeyProperty1 = factory.getOWLDataProperty(contents[1], pm);
//			    //OWLDatatype hasKeyRange1 = OWL2Datatype.XSD_STRING.getDatatype(factory);
//			    addAxiomToOntology(factory.getOWLHasKeyAxiom(hasKeyClass, hasKeyProperty1));
//				line = reader.readLine();
//			}
//			reader.close();
//		} catch (IOException e) { e.printStackTrace(); }
//	}
//	

}
