package owl.cs.myfirst.owlapi;
import java.io.File;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

import openllet.owlapi.OpenlletReasoner;
import openllet.owlapi.OpenlletReasonerFactory;
import uk.ac.manchester.cs.jfact.JFactFactory;

//<dependency>
//  <groupId>commons-io</groupId>
//  <artifactId>commons-io</artifactId>
//  <version>${version.commons-io}</version>
//</dependency>

//<dependency>
//<groupId>com.hermit-reasoner</groupId>
//<artifactId>org.semanticweb.hermit</artifactId>
//<version>1.3.8.1</version>
//</dependency>

public class Reasoners {
	
	public static void jfactReasoner(File c) throws OWLOntologyCreationException {
		IRI physicalIRI = IRI.create(c);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(physicalIRI);	
//		System.out.println("Total Logical Axiom Count......."+ ontology.getLogicalAxiomCount());
		OWLReasonerFactory reasonerFactory = new JFactFactory();
		long startTime = System.nanoTime();
		OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);	
		long endTime = System.nanoTime();
        long duration = ((endTime - startTime));
//        System.out.println("Time taken for Reasoner Creation " + duration );
//        System.out.println();
        
//	    System.out.println("Started Consistency Checking");
	    startTime = System.nanoTime();
	    System.out.println("Consistency = " + reasoner.isConsistent() );
	    endTime = System.nanoTime();
	    duration = ((endTime - startTime));
//	    System.out.println("Time taken for Consistency Check " + duration );
//	    System.out.println();
	        
//	    System.out.println("Started Instance Checking");
//	    startTime = System.nanoTime();
//	    for (OWLNamedIndividual individual: ontology.getIndividualsInSignature()) {
//	    	reasoner.getTypes(individual,false);
//	    }
//	    endTime = System.nanoTime();
//	    duration = ((endTime - startTime));
//	    System.out.println("Time taken for Realization " + duration );
//	    System.out.println();
//	    
//	    System.out.println("Started Classification Time");
//	    startTime = System.nanoTime();
//	    reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
//	    endTime = System.nanoTime();
//	    duration = ((endTime - startTime));
//	    System.out.println("Time taken for Classification " + duration );
//	    System.out.println();
//	    System.out.println();
	}
	
	public static void openlletReasoner(File c) throws OWLOntologyCreationException {
		IRI physicalIRI = IRI.create(c);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(physicalIRI);
//		System.out.println("Total Logical Axiom Count......."+ ontology.getLogicalAxiomCount());
		long startTime = System.nanoTime();
        final OpenlletReasoner reasoner = OpenlletReasonerFactory.getInstance().createReasoner(ontology);
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime));
//        System.out.println("Time taken for Reasoner Creation " + duration );
//        System.out.println();
        
//        System.out.println("Started Consistency Checking");
        startTime = System.nanoTime();
        System.out.println("Consistency = " + reasoner.isConsistent() );
        endTime = System.nanoTime();
        duration = ((endTime - startTime));
//        System.out.println("Time taken for Consistency Check " + duration );
//        System.out.println();    
            
//        System.out.println("Started Instance Checking");
//        startTime = System.nanoTime();
//        for (OWLNamedIndividual individual: ontology.getIndividualsInSignature()) {
//        	//for (OWLNamedIndividual individual: ontology.individualsInSignature()) {
//        	reasoner.getTypes(individual,false);
//    	}
//        endTime = System.nanoTime();
//        duration = ((endTime - startTime));
//        System.out.println("Time taken for Realization " + duration );
//        System.out.println();
//        
//        System.out.println("Started Classification Time");
//        startTime = System.nanoTime();
//        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
//        endTime = System.nanoTime();
//        duration = ((endTime - startTime));
//        System.out.println("Time taken for Classification " + duration );
//        System.out.println();
//        System.out.println();
	}
	
	public void hermitReasoner(File c) throws OWLOntologyCreationException {
		IRI physicalIRI = IRI.create(c);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(physicalIRI);
//		System.out.println("Total Logical Axiom Count......."+ ontology.getLogicalAxiomCount());
		long startTime = System.nanoTime();
        Reasoner reasoner=new Reasoner(ontology);
        
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime));
//        System.out.println("Time taken for Reasoner Creation " + duration );
//        System.out.println();
        
//        System.out.println("Started Consistency Checking");
        startTime = System.nanoTime();
        System.out.println("Consistency = " + reasoner.isConsistent() );
         endTime = System.nanoTime();
         duration = ((endTime - startTime));
//        System.out.println("Time taken for Consistency Check " + duration );
//        System.out.println();

//        System.out.println("Started Instance Checking");
//         startTime = System.nanoTime();
//        for (OWLNamedIndividual individual: ontology.getIndividualsInSignature()) {
//        	reasoner.getTypes(individual,false);
//    		}
//         endTime = System.nanoTime();
//         duration = ((endTime - startTime));
//        System.out.println("Time taken for Realization " + duration );
//        System.out.println();
//        
//        System.out.println("Started Classification Time");
//         startTime = System.nanoTime();
//        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
//         endTime = System.nanoTime();
//        duration = ((endTime - startTime));
//        System.out.println("Time taken for Classification " + duration );
//        System.out.println();
//        System.out.println();
        
	}
	
	public void run(File fileout,String fileName) throws OWLOntologyCreationException {
		System.out.println(" ----- JFACT REASONER ----- "+fileName);
		jfactReasoner(fileout);
//		System.out.println();
		System.out.println(" -----  OPENLLET REASONER ----- "+fileName);
		openlletReasoner(fileout);
//		System.out.println();
//	    System.out.println(" ------ HERMIT REASONER ------ "+fileName);
//	    hermitReasoner(fileout);
	}
}
