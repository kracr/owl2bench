package convert.convert;


	/* Main Class that takes as input University Number, Seed and OWL 2 Profile (EL,QL,RL,DL).*/
	/* Generates instances according to the specified number of universities. */
	/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
	 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Iterator;
	import org.semanticweb.owlapi.util.DefaultPrefixManager;
	import org.semanticweb.owlapi.apibinding.OWLManager;
//import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.*;
	import org.semanticweb.owlapi.formats.*;

	public class Convert {
	  
	  

	
	    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
	    	  //String ontologyFormat="omn";
	    	  //File file = new File("OWL2RL-1-output.owl");
	    	  String ontologyFormat=args[0];
	    	  String name=args[1];
	    	  File file = new File(name);
	  	    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	  	    PrefixManager pm = new DefaultPrefixManager("http://benchmark/OWL2Bench");
	  	    IRI ontologyIRI = IRI.create(pm.getDefaultPrefix());
	  	    //OWLOntology ontology = createOWLOntology(pm);
	  	    //File file2 = new File("univ-bench-2dl-rdfxmlformat.owl");
	  	    OWLOntology o = manager.loadOntologyFromOntologyDocument(file);
	  	    //File file2;
	  	  
	  	    //OWLOntology o;
	  	    OWLDataFactory factory = manager.getOWLDataFactory();
	  	    //OWLOntology ontology1;
	    	//input 
	    	OWLXMLDocumentFormat owx = new OWLXMLDocumentFormat();
	        TurtleDocumentFormat ttl=new TurtleDocumentFormat();
	        //RDfXMLSyntaxDocumentFormat rdf= new  RDfXMLDocumentFormat();
	        FunctionalSyntaxDocumentFormat ofn = new FunctionalSyntaxDocumentFormat();
	        ManchesterSyntaxDocumentFormat omn = new ManchesterSyntaxDocumentFormat();

	            if(ontologyFormat.matches("owx")) {
	            	manager.saveOntology(o,owx,IRI.create(file.toURI()));
	            	System.out.println("Saved Ontology Format is OWL/XML");
	            }
	            else if(ontologyFormat.matches("ttl")) {
	            	manager.saveOntology(o,ttl,IRI.create(file.toURI()));
	            	System.out.println("Saved Ontology Format is Turtle");
	            }
	            else if(ontologyFormat.matches("ofn")) {
	            	manager.saveOntology(o,ofn,IRI.create(file.toURI()));
	            	System.out.println("Saved Ontology Format is OWL Functional");
	            }
	            else if(ontologyFormat.matches("omn")) {
	            	manager.saveOntology(o,omn,IRI.create(file.toURI()));
	            	System.out.println("Saved Ontology Format is Manchester");
	            }
	            else {
	            OWLDocumentFormat format = manager.getOntologyFormat(o);
	            manager.saveOntology(o,format,IRI.create(file.toURI()));
	            System.out.println("Saved Ontology Format is RDF/XML");
	            }
	            //OWLDocumentFormat format = manager.getOntologyFormat(o);
	            //System.out.println("Ontology Format="+ format);
	            //System.out.println("Finished Writing to file "+ System.getProperty("user.dir")+ "/" + "OWL2"+this.profile + "-" + univNum + "-output.owl" );
	        } 
	    }

	//TBox
	    
	   
	        
	        
	 
	        





