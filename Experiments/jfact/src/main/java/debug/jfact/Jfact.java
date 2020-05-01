package debug.jfact;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import uk.ac.manchester.cs.jfact.JFactFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Jfact {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws OWLOntologyCreationException {

		File c=new File(args[0]);
		String task = args[1];
		//File c=new File("C:\\Users\\Gunjan\\Documents\\GitHub\\owl2dl-benchmark\\UNIV-BENCH-OWL2EL.owl");
		IRI physicalIRI = IRI.create(c);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(physicalIRI);	
		System.out.println("Total Logical Axiom Count......."+ ontology.getLogicalAxiomCount());
		OWLReasonerFactory reasonerFactory = new JFactFactory();
		long startTime = System.nanoTime();
		OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);	
		long endTime = System.nanoTime();
        long duration = ((endTime - startTime));
        System.out.println("Time taken for Reasoner Creation " + duration );
		if(task.matches("C")) {
	        System.out.println("Started Consistency Checking");
	         startTime = System.nanoTime();
	        reasoner.isConsistent();
	         endTime = System.nanoTime();
	         duration = ((endTime - startTime));
	        System.out.println("Time taken for Consistency Check " + duration );
	        
	        
	        }
	      else if(task.matches("R")) {
	        	System.out.println("Started Instance Checking");
	         startTime = System.nanoTime();
	        for (OWLNamedIndividual individual: ontology.getIndividualsInSignature()) {
	        	reasoner.getTypes(individual,false);
	    		}
	         endTime = System.nanoTime();
	         duration = ((endTime - startTime));
	        System.out.println("Time taken for Realization " + duration );}

	        else if(task.matches("CT")) {
	        	System.out.println("Started Classification Time");
	         startTime = System.nanoTime();
	        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
	         endTime = System.nanoTime();
	         duration = ((endTime - startTime));
	        System.out.println("Time taken for Classification " + duration );}
	        /*
	        startTime = System.nanoTime();
	        for (OWLClass clazz : ontology.getClassesInSignature()) {
	    		reasoner.getInstances(
	    		clazz, false);
	    		}
	        endTime = System.nanoTime();
	        duration = ((endTime - startTime));
	        System.out.println("Time taken for Retrieval " + duration );
	    	//System.out.println(ontology.getIndividualsInSignature());
	    	 * 
	    	 */
	  
	}
}
