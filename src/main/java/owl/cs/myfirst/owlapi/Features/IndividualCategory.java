package owl.cs.myfirst.owlapi.Features;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.PrefixManager;

import owl.cs.myfirst.owlapi.Generator.FeaturePool;

public class IndividualCategory {
	protected static OWLDataFactory factory;
	protected static FeaturePool featurePool;
	protected static PrefixManager pm;
	protected static OWLOntology ontology;
	
	public IndividualCategory(OWLDataFactory factory, PrefixManager pm, FeaturePool featurePool, OWLOntology ontology ) {
		IndividualCategory.factory = factory;
		IndividualCategory.pm = pm;
		IndividualCategory.featurePool = featurePool;
		IndividualCategory.ontology = ontology;
	}

	public static void convertLineToNamedIndividual(ArrayList<String> allConcepts) {
//		OWLClass hasKeyClass = featurePool.getExclusiveClass(allConcepts.get(0));
//		OWLDataProperty hasKeyProperty = factory.getOWLDataProperty(allConcepts.get(1), pm);
//		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLHasKeyAxiom(hasKeyClass, hasKeyProperty));
	}
	
	public static void convertLineToAnonymousIndividual(ArrayList<String> allConcepts) {
//		OWLClass hasKeyClass = featurePool.getExclusiveClass(allConcepts.get(0));
//		OWLDataProperty hasKeyProperty = factory.getOWLDataProperty(allConcepts.get(1), pm);
//		ontology.getOWLOntologyManager().addAxiom(ontology, factory.getOWLHasKeyAxiom(hasKeyClass, hasKeyProperty));
	}
	
}
