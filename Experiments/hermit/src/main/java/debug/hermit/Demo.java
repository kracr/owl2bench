package debug.hermit;


import java.io.File;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;


/**
 * This example demonstrates how HermiT can be used to check the consistency of the Pizza ontology
 */
public class Demo {

	public static void main(String[] args) throws Exception {
		
		File c=new File("C:\\Users\\Gunjan\\univ-bench-2el-rdfxmlformat.owl");
		IRI physicalIRI = IRI.create(c);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(physicalIRI);
        System.out.println(ontology.getLogicalAxiomCount());
        System.out.println(ontology.getAxiomCount());
    	// First, we create an OWLOntologyManager object. The manager will load and save ontologies.
        //OWLOntologyManager m=OWLManager.createOWLOntologyManager();
        // We use the OWL API to load the Pizza ontology.
        //OWLOntology o=m.loadOntologyFromOntologyDocument(IRI.create("http://owl.cs.manchester.ac.uk/co-ode-files/ontologies/pizza.owl"));
        // Now, we instantiate HermiT by creating an instance of the Reasoner class in the package org.semanticweb.HermiT.
        Reasoner hermit=new Reasoner(ontology);
        // Finally, we output whether the ontology is consistent.
        System.out.println(hermit.isConsistent());
	}
}
