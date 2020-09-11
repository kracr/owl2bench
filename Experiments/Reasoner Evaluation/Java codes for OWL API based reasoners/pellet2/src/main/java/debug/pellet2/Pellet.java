package debug.pellet2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;

  
public class Pellet {
	public static void main(String[] args) throws Exception {

		File c=new File(args[0]);
		String task= args[1];
		//File c=new File("C:\\Users\\Gunjan\\Documents\\GitHub\\owl2dl-benchmark\\UNIV-BENCH-OWL2EL.owl");
		IRI physicalIRI = IRI.create(c);
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(physicalIRI);
		OWLReasonerFactory reasonerFactory = new PelletReasonerFactory();
		
		
		long startTime = System.nanoTime();
		OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        long endTime = System.nanoTime();
        long duration0 = ((endTime - startTime));
        //System.out.println("Time taken for Reasoner Creation =" + duration0 );
		
        
		 if(task.matches("consistency")) {
		        System.out.println("Started Consistency Checking");
		        startTime = System.nanoTime();
		        System.out.println(" " + reasoner.isConsistent() );
		         endTime = System.nanoTime();
		         long duration = (((endTime - startTime))+duration0)/1000000000;
		         System.out.println("Pellet : Consistency Check in seconds =" + duration );
		     
		        }
		 
		 
		 else if(task.matches("realisation")) {
		        	System.out.println("Started Instance Checking");
		         startTime = System.nanoTime();
		        for (OWLNamedIndividual individual: ontology.getIndividualsInSignature()) {
		        	reasoner.getTypes(individual,false);
		    		}
		         endTime = System.nanoTime();
		         long duration = (((endTime - startTime))+duration0)/1000000000;
		         System.out.println("Pellet : Realisation in seconds =" + duration );}
		        
		 
		 
		 else if(task.matches("classification")) {
		        	System.out.println("Started Classification Time");
		         startTime = System.nanoTime();
		        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		         endTime = System.nanoTime();
		         long duration = (((endTime - startTime))+duration0)/1000000000;
		         System.out.println("Pellet : Classification in seconds =" + duration );}
     
    }

}
