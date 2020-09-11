package debug.hermit;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.Node;
//import uk.ac.manchester.cs.owl.owlapi.tutorial.LabelExtractor;
public class Hermit {

	public static void main(String[] args) throws Exception {

		File c=new File(args[0]);
		String task= args[1];
		//File c=new File("C:\\Users\\Gunjan\\Documents\\GitHub\\owl2dl-benchmark\\OWL2EL-1-output.owl");
		IRI physicalIRI = IRI.create(c);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(physicalIRI);
		//System.out.println("Total Logical Axiom Count......."+ ontology.getLogicalAxiomCount());
		
		
		long startTime = System.nanoTime();
        Reasoner reasoner=new Reasoner(ontology);
        long endTime = System.nanoTime();
        long duration0 = ((endTime - startTime));
        //System.out.println("Time taken for Reasoner Creation =" + duration0 );
        
        
        if(task.matches("consistency")) {
        System.out.println("Started Consistency Checking");
        startTime = System.nanoTime();
        System.out.println(" " + reasoner.isConsistent() );
         endTime = System.nanoTime();
         long duration = (((endTime - startTime))+duration0)/1000000000;
        System.out.println("HermiT : Consistency Check in seconds =" + duration );
        }
        
        
        else if(task.matches("realisation")) {
        	System.out.println("Started Instance Checking");
         startTime = System.nanoTime();
        for (OWLNamedIndividual individual: ontology.getIndividualsInSignature()) {
        	reasoner.getTypes(individual,false);
    		}
         endTime = System.nanoTime();
         long duration = (((endTime - startTime))+duration0)/1000000000;
        System.out.println("HermiT : Realisation in seconds =" + duration );}
        
        
        else if(task.matches("classification")) {
        	System.out.println("Started Classification Time");
         startTime = System.nanoTime();
        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
         endTime = System.nanoTime();
         long duration = (((endTime - startTime))+duration0)/1000000000;
        System.out.println("HermiT : Classification in seconds =" + duration );}
        
 
	}
}




