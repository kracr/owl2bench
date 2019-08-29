package ABoxGen.InstanceGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Generator {

    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    PrefixManager pm = new DefaultPrefixManager("http://benchmark/univ-bench-2dl");
    IRI ontologyIRI = IRI.create(pm.getDefaultPrefix());
    OWLOntology ontology = createOWLOntology(pm);
    File file2 = new File("C:\\Users\\Gunjan\\Documents\\univ-bench-2dl-rdfxmlformat.owl");
    OWLOntology o = loadOWLOntology(file2);
    OWLDataFactory factory = manager.getOWLDataFactory();
    OWLOntology ontology1;
    int univNum,publicationNum;
    University[] universities;
    Publication[] publications;
    InterlinkedProperties interlinks;
    AssignAdvisor assignAdvisor;
    AssignDegree assignDegree;
    EvaluationCommittee evaluationCommittee;
    ConfigFile configFile;
    HashSet<String> universityName = new HashSet();

    public Generator() {
    }

    public static void main(String[] args) {
        int univNum=1;
        int seed = 350;
        if(args.length!=0 && args.length==2)
        {
            univNum=Integer.parseInt(args[0]);
            seed=Integer.parseInt(args[1]);
        }
        else if (args.length!=0 && args.length==1)
        {
            univNum=Integer.parseInt(args[0]);
        }
        new Generator().start(univNum, seed);
    }


    public void start(int univNum, int seed) {
        //profile= new OWL2Profile();
        OWLDocumentFormat format = manager.getOntologyFormat(o);
        System.out.println("Counts..."+ format);
        //OWLReasoner reasoner = PelletReasonerFactory.getInstance().createReasoner(o);

       // System.out.println(o);
        this.univNum = univNum;
        GetRandomNo.setSeed((long) seed);
        this.generate();
    }

    private void generate() {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream("C:\\Users\\Gunjan\\Desktop\\owlbenchmarkingreferences\\OntoBench-master\\UnivBench2DL\\RandomNames.xlsx");
            // new FileInputStream("C:\\Users\\Gunjan\\Desktop\\RandomNames.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet("FirstNames");
            //int lastRowNum= worksheet.getLastRowNum();
            //int randomRow=GetRandomNo.getRandomFromRange(0,lastRowNum);
            //int randomRow=GetRandomNo.getRandomFromRange(0,1000);
            int randomRow1=GetRandomNo.getRandomFromRange(0,32951);
            int randomRow2=GetRandomNo.getRandomFromRange(0,151670);

        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        this.universities = new University[this.univNum];
        this.configFile=new ConfigFile();

        for (int i = 0; i < this.univNum; ++i) {
            this.universities[i] = new University(this, i);
        }
        //Generate publications
        //create links across universities using Publication 'hasAuthor' some Person. 1 publication can have authors from different universities
        //also authors of 1 publication are interlinked using 'hasCollaboration' Property.
        this.publicationNum=GetRandomNo.getRandomFromRange(configFile.publicationNum_Min*univNum,configFile.publicationNum_Max*univNum);
        this.publications= new Publication[this.publicationNum];
        for (int i = 0; i < this.publicationNum; ++i) {
            this.publications[i] = new Publication(this,universities,i);
        }

        //links across universities using friendOf, sameHomeTownWith, likes, loves, isCrazyAbout, dislikes
        this.interlinks=new InterlinkedProperties(this,universities);
        //assign internal and external professors as advisor
        this.assignAdvisor=new AssignAdvisor(this,universities);
        // hasUnderGraduateDegreeFrom,hasMastersDegreeFrom,hasDoctoralDegreeFrom
        this.assignDegree=new AssignDegree(this,universities);
        //every deptt has evaluation committe
        this.evaluationCommittee=new EvaluationCommittee(this,universities);
       // ontology.axioms().forEach(System.out::println);
        //System.out.println("Counts..."+ ontology.getLogicalAxiomCount() + "   "+ontology.getAxiomCount());

        try  {
            File file = new File(System.getProperty("user.dir")+ "\\output1.owl");
           // File file = new File("C:\\Users\\Gunjan\\Desktop\\owlbenchmarkingreferences\\OntoBench-master\\UnivBench2DL\\output1.owl");
           // File file = new File("D:\\output1.owl");

            if (!file.exists()) {
                file.mkdir();
            }
            OWLDocumentFormat format = manager.getOntologyFormat(o);
            System.out.println("Counts..."+ format);
            manager.saveOntology(o,format,IRI.create(file.toURI()));
        } catch (OWLOntologyStorageException e) {
            e.printStackTrace();
        }
    }

    public OWLOntology createOWLOntology(PrefixManager pm) {
        if (pm.getDefaultPrefix() == null) {
            throw new IllegalStateException("Default ontology prefix must not be null.");
        }
        try  {
            ontology1= manager.createOntology(IRI.create(pm.getDefaultPrefix()));
            // use the inputStream to read a file
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        // Cast to a mutable ontology to pass OWLApi's strange checks
        return ontology1;
    }

    public OWLOntology loadOWLOntology(File file2)
    {
        try{
            o = manager.loadOntologyFromOntologyDocument(file2);
        }catch (OWLOntologyCreationException e)
        {
            e.printStackTrace();
        }

        return o;

    }
    public OWLIndividual getNamedIndividual(String name) {

        OWLIndividual individual = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + "#" + name));
        return individual;
    }

    public OWLObjectProperty getObjectProperty(String name) {

        OWLObjectProperty property = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#" + name));
        return property;

    }

    public OWLDataProperty getDataProperty(String name) {

        OWLDataProperty property = factory.getOWLDataProperty(IRI.create(ontologyIRI + "#" + name));
        return property;

    }

    public OWLClass getClass(String name) {

        OWLClass classname = factory.getOWLClass(IRI.create(ontologyIRI + "#" + name));
        return classname;

    }

    public OWLLiteral getLiteral(String name) {

        OWLLiteral literal = factory.getOWLLiteral(name);
        return literal;

    }

    public void addAxiomToOntology(OWLAxiom axiom) {
        //System.out.println(axiom);

        //ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
       o.getOWLOntologyManager().addAxiom(o, axiom);
    }

    public void classAssertion(OWLClassExpression classExpression, OWLIndividual individual) {

        addAxiomToOntology(factory.getOWLClassAssertionAxiom(classExpression, individual));
    }

    public void dataPropertyAssertion(OWLDataProperty property, OWLIndividual subject, OWLLiteral object) {
        addAxiomToOntology(factory.getOWLDataPropertyAssertionAxiom(property,subject,  object));
    }

    public void objectPropertyAssertion(OWLObjectProperty property, OWLIndividual subject, OWLIndividual object) {
        addAxiomToOntology(factory.getOWLObjectPropertyAssertionAxiom( property, subject,  object));
    }


}



