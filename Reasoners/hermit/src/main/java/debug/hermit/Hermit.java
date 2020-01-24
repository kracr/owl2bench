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
		
        Reasoner reasoner=new Reasoner(ontology);
        if(task.matches("CC")) {
        System.out.println("Started Consistency Checking");
        long startTime = System.nanoTime();
        reasoner.isConsistent();
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime));
        System.out.println("Time taken for Consistency Check " + duration );}
        else if(task.matches("CT")) {
        	System.out.println("Started Classification Time");
        long startTime = System.nanoTime();
        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime));
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
        else if(task.matches("IC")) {
        	System.out.println("Started Instance Checking");
        long startTime = System.nanoTime();
        for (OWLNamedIndividual individual: ontology.getIndividualsInSignature()) {
        	reasoner.getTypes(individual,false);
    		}
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime));
        System.out.println("Time taken for Realization " + duration );}
    	

	}
}




