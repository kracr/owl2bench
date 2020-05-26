/* Main Class that takes as input University Number, Seed and OWL 2 Profile (EL,QL,RL,DL).*/
/* Generates instances according to the specified number of universities. */
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */


package ABoxGen.InstanceGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.formats.*;
//import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.OWLDocumentFormat;

public class Generator {
    String ontologyFormat="rdf";
    int publicationNum_Min=150; //per university
    int publicationNum_Max=200; //per university
    int researchGroupNum_Min=4;
    int researchGroupNum_Max=8;
    int collegeNum_Min=5;
    int collegeNum_Max=10;
    double ratio_womenCollege=0.2;
    int womenCollegeNum_Min=(int)(collegeNum_Min*ratio_womenCollege);
    int womenCollegeNum_Max=(int)(collegeNum_Max*ratio_womenCollege);
    int deptNum_Min=3;
    int deptNum_Max=5;
    int RANum_Min=4;
    int RANum_Max=6;
    int progNum_Min=1;
    int progNum_Max=3;
    int ugStudentNum_Min=30;
    int ugStudentNum_Max=45;
    int pgStudentNum_Min=15;
    int pgStudentNum_Max=20;
    int phdStudentNum_Min=2;
    int phdStudentNum_Max=5;
    int ugCourseNum_Min=10;
    int ugCourseNum_Max=15;
    int electiveCourseNum_Min=10;
    int electiveCourseNum_Max=15;
    int assistantProfessorNum_Min=8;
    int assistantProfessorNum_Max=12;
    int associateProfessorNum_Min=5;
    int associateProfessorNum_Max=10;
    int fullProfessorNum_Min=5;
    int fullProfessorNum_Max=10;
    int visitingProfessorNum_Min=2;
    int visitingProfessorNum_Max=5;
    int lecturerNum_Min=5;
    int lecturerNum_Max=10;
    int postDocNum_Min=2;
    int postDocNum_Max=7;
    int systemStaffNum_Min=2;
    int systemStaffNum_Max=5;
    int clericalStaffNum_Min=2;
    int clericalStaffNum_Max=8;
    int otherStaffNum_Min=15;
    int otherStaffNum_Max=25;
    int internalAdvisorNum_Min=1;
    int internalAdvisorNum_Max=2;
    int externalAdvisorNum_Min=0;
    int externalAdvisorNum_Max=2;
    int numOfElectives_Min=1;
    int numOfElectives_Max=4;
    int numOfElectivesOutsideDept_Min=1;
    int sameHomeTownNum_Min=0;
    int sameHomeTownNum_Max=2;
    int knowsNum_Min=0;
    int knowsNum_Max=2;
    int likesNum_Min=0;
    int likesNum_Max=4;
    int lovesNum_Min =0;
    int lovesNum_Max=1;
    int isCrazyAboutNum_Min=0;
    int isCrazyAboutNum_Max=1;
    int dislikesNum_Min=0;
    int dislikesNum_Max=1;
    String[] TOKEN_CollegeDiscipline= new String[]{"Engineering", "FineArts", "HumanitiesAndSocial","Management", "Science"};
    String[] TOKEN_Engineering = new String[]{"AeronauticalEngineering","BiomedicalEngineering","ChemicalEngineering","CivilEngineering","ComputerEngineering","ElectricalEngineering","IndustryEngineering","MaterialScienceEngineering","MechanicalEngineering","PetroleumEngineering"};
    String[] TOKEN_Management = new String[]{"DesignManagement", "FinancialAndAccountingManagement", "HumanResourceManagement", "MarketingManagement", "OperationsManagement", "ProjectManagement", "PublicRelationsManagement", "SalesManagement", "SupplyChainManagement", "RiskManagement"};
    String[] TOKEN_FineArts= new String[]{"Architecture", "AsianArts", "Drama", "LatinArts", "MediaArtsAndSciences", "MedievalArts", "ModernArts","MusicsClass", "PerformingArts", "TheatreAndDance"};
    String[] TOKEN_Science=new String[]{"Astronomy", "Biology", "Chemistry", "ComputerScience", "GeoScience", "MarineScience", "MaterialScience", "Mathematics", "Physics", "Statistics"};
    String[] TOKEN_HumanitiesAndSocial=new String[]{"Anthropology", "Economics", "English", "History", "Humanities", "Linguistics", "ModernLanguages", "Philosophy", "Psychology", "Religions"};
    //org.slf4j.simplelogger.defaultLogLevel=error;
   
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    PrefixManager pm = new DefaultPrefixManager("http://benchmark/OWL2Bench");
    IRI ontologyIRI = IRI.create(pm.getDefaultPrefix());
    OWLOntology ontology = createOWLOntology(pm);
    //File file2 = new File("univ-bench-2dl-rdfxmlformat.owl");
    //OWLOntology o = loadOWLOntology(file2);
    File file2;
    OWLOntology o;
    OWLDataFactory factory = manager.getOWLDataFactory();
    OWLOntology ontology1;
    int univNum,publicationNum;
    String profile;
    University[] universities;
    Publication[] publications;
    InterlinkedProperties interlinks;
    AssignAdvisor assignAdvisor;
    AssignDegree assignDegree;
  
    HashSet<String> universityName = new HashSet();
    HashMap<Integer,String> map1 = new HashMap<>();
    HashMap<Integer,String> map2 = new HashMap<>();
    HashMap<Integer,String> map3 = new HashMap<>();

    public Generator() {
    }

    public static void main(String[] args) {
    	//input 
        int univNum=1;
        int seed =1; //For about 50K triple Seed value: QL=1 (51K), EL=2 (52K),  DL/RL=3 (48K)		      
        String profile= "EL";  
        
        if(args.length==3)
        {
            univNum=Integer.parseInt(args[0]);
            profile= args[1];
            seed=Integer.parseInt(args[2]);
        }
        else if(args.length==2)
        {
            univNum=Integer.parseInt(args[0]);
            profile= args[1];
            System.out.println("Default Seed value is 1");
        }
        else
        {
     
                System.out.println("Please give arguments in the following order: No. of Universities (int), OWL 2 Profile (EL/QL/RL/DL), Seed (optional) ");
                System.out.println("For example: 1 DL 1 or 1 DL (where default seed value is 1");
        }
        //System.out.println(profile);
        new Generator().start(univNum, seed, profile);
    }

//TBox
    
    public void start(int univNum, int seed, String profile) {
    	//System.out.println(profile);
    	this.profile=profile;
    	//System.out.println(".." + profile);
    	//use TBox corresponing to user input
    	 if(profile.matches("EL")){
    		 System.out.println("Loading TBox UNIV-BENCH-OWL2EL.owl");
    		 file2 = new File("UNIV-BENCH-OWL2EL.owl");
    	 }
    	 else if (profile.matches("QL")) {

    		 System.out.println("Loading TBox UNIV-BENCH-OWL2QL.owl");
    		 file2 = new File("UNIV-BENCH-OWL2QL.owl");
    	 }
    	 else if (profile.matches("RL")) {

    		 System.out.println("Loading TBox UNIV-BENCH-OWL2RL.owl");
    		 file2 = new File("UNIV-BENCH-OWL2RL.owl");
    	 }
    	 else if (profile.matches("DL"))
    	 {
    		 System.out.println("Loading TBox UNIV-BENCH-OWL2DL.owl");
    		 file2 = new File("UNIV-BENCH-OWL2DL.owl");
    	 }
    	OWLOntology o = loadOWLOntology(file2);
        OWLDocumentFormat format = manager.getOntologyFormat(o);

        this.univNum = univNum;
        GetRandomNo.setSeed((long) seed);

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            // load a properties fi
            prop.load(input);
        //If user wants to modify the min-max parameters, they can change the null value in ConfigFile. 
            this.ontologyFormat=prop.getProperty("ontologyFormat");
            this.publicationNum_Min=Integer.parseInt(prop.getProperty("publicationNum_Min"));
            this.publicationNum_Max=Integer.parseInt(prop.getProperty("publicationNum_Max"));
            this.researchGroupNum_Min=Integer.parseInt(prop.getProperty("researchGroupNum_Min"));
            this.researchGroupNum_Max=Integer.parseInt(prop.getProperty("researchGroupNum_Max"));
            this.collegeNum_Min=Integer.parseInt(prop.getProperty("collegeNum_Min"));
            this.collegeNum_Max=Integer.parseInt(prop.getProperty("collegeNum_Max"));
            this.womenCollegeNum_Min=(int)(collegeNum_Min*ratio_womenCollege);
            this.womenCollegeNum_Max=(int)(collegeNum_Max*ratio_womenCollege);
            this.RANum_Min=Integer.parseInt(prop.getProperty("RANum_Min"));
            this.RANum_Max=Integer.parseInt(prop.getProperty("RANum_Max"));
            this.sameHomeTownNum_Min=Integer.parseInt(prop.getProperty("sameHomeTownNum_Min"));
            this.sameHomeTownNum_Max=Integer.parseInt(prop.getProperty("sameHomeTownNum_Max"));
            this.knowsNum_Min=Integer.parseInt(prop.getProperty("knowsNum_Min"));
            this.knowsNum_Max=Integer.parseInt(prop.getProperty("knowsNum_Max"));
            this.isCrazyAboutNum_Min=Integer.parseInt(prop.getProperty("isCrazyAboutNum_Min"));
            this.isCrazyAboutNum_Max=Integer.parseInt(prop.getProperty("isCrazyAboutNum_Max"));
            this.lovesNum_Min=Integer.parseInt(prop.getProperty("lovesNum_Min"));
            this.lovesNum_Max=Integer.parseInt(prop.getProperty("lovesNum_Max"));
            this.likesNum_Min=Integer.parseInt(prop.getProperty("likesNum_Min"));
            this.likesNum_Max=Integer.parseInt(prop.getProperty("likesNum_Max"));
            this.dislikesNum_Min=Integer.parseInt(prop.getProperty("dislikesNum_Min"));
            this.dislikesNum_Max=Integer.parseInt(prop.getProperty("dislikesNum_Max"));
            this.deptNum_Min=Integer.parseInt(prop.getProperty("deptNum_Min"));
            this.deptNum_Max=Integer.parseInt(prop.getProperty("deptNum_Max"));
            this.numOfElectives_Min=Integer.parseInt(prop.getProperty("numOfElectives_Min"));
            this.numOfElectives_Max=Integer.parseInt(prop.getProperty("numOfElectives_Max"));
            this.numOfElectivesOutsideDept_Min=Integer.parseInt(prop.getProperty("numOfElectivesOutsideDept_Min"));
            this.internalAdvisorNum_Min=Integer.parseInt(prop.getProperty("internalAdvisorNum_Min"));
            this.internalAdvisorNum_Max=Integer.parseInt(prop.getProperty("internalAdvisorNum_Max"));
            this.externalAdvisorNum_Min=Integer.parseInt(prop.getProperty("externalAdvisorNum_Min"));
            this.externalAdvisorNum_Max=Integer.parseInt(prop.getProperty("externalAdvisorNum_Max"));
            this.ugStudentNum_Min=Integer.parseInt(prop.getProperty("ugStudentNum_Min"));
            this.ugStudentNum_Max=Integer.parseInt(prop.getProperty("ugStudentNum_Max"));
            this.pgStudentNum_Min=Integer.parseInt(prop.getProperty("pgStudentNum_Min"));
            this.pgStudentNum_Max=Integer.parseInt(prop.getProperty("pgStudentNum_Max"));
            this.phdStudentNum_Min=Integer.parseInt(prop.getProperty("phdStudentNum_Min"));
            this.phdStudentNum_Max=Integer.parseInt(prop.getProperty("phdStudentNum_Max"));
            this.assistantProfessorNum_Min=Integer.parseInt(prop.getProperty("assistantProfessorNum_Min"));
            this.assistantProfessorNum_Max=Integer.parseInt(prop.getProperty("assistantProfessorNum_Max"));
            this.associateProfessorNum_Min=Integer.parseInt(prop.getProperty("associateProfessorNum_Min"));
            this.associateProfessorNum_Max=Integer.parseInt(prop.getProperty("associateProfessorNum_Max"));
            this.fullProfessorNum_Min=Integer.parseInt(prop.getProperty("fullProfessorNum_Min"));
            this.fullProfessorNum_Max=Integer.parseInt(prop.getProperty("fullProfessorNum_Max"));
            this.visitingProfessorNum_Min=Integer.parseInt(prop.getProperty("visitingProfessorNum_Min"));
            this.visitingProfessorNum_Max=Integer.parseInt(prop.getProperty("visitingProfessorNum_Max"));
            this.lecturerNum_Min=Integer.parseInt(prop.getProperty("lecturerNum_Min"));
            this.lecturerNum_Max=Integer.parseInt(prop.getProperty("lecturerNum_Max"));
            this.postDocNum_Min=Integer.parseInt(prop.getProperty("postDocNum_Min"));
            this.postDocNum_Max=Integer.parseInt(prop.getProperty("postDocNum_Max"));
            this.systemStaffNum_Min=Integer.parseInt(prop.getProperty("systemStaffNum_Min"));
            this.systemStaffNum_Max=Integer.parseInt(prop.getProperty("systemStaffNum_Max"));
            this.clericalStaffNum_Min=Integer.parseInt(prop.getProperty("clericalStaffNum_Min"));
            this.clericalStaffNum_Max=Integer.parseInt(prop.getProperty("clericalStaffNum_Max"));
            this.otherStaffNum_Min=Integer.parseInt(prop.getProperty("otherStaffNum_Min"));
            this.otherStaffNum_Max=Integer.parseInt(prop.getProperty("otherStaffNum_Max"));
            this.progNum_Min=Integer.parseInt(prop.getProperty("progNum_Min"));
            this.progNum_Max=Integer.parseInt(prop.getProperty("progNum_Max"));
    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        this.generate();
    }

    private void generate() {

        try {//excel file used to generate real world names for person and universities
        	//InputStream fileInputStream = getClass().getResourceAsStream(System.getProperty("resources/RandomNames.xlsx"));
        	FileInputStream fileInputStream =new FileInputStream("RandomNames.xlsx"); 
            //C:\\Users\\Gunjan\\Desktop\\owlbenchmarkingreferences\\OntoBench-master\\UnivBench2DL\\RandomNames.xlsx
            // new FileInputStream("C:\\Users\\Gunjan\\Desktop\\RandomNames.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet1 = workbook.getSheetAt(0);
            Sheet sheet2 = workbook.getSheetAt(1);
            Sheet sheet3 = workbook.getSheetAt(2);
            DataFormatter dataFormatter1 = new DataFormatter();
            DataFormatter dataFormatter2 = new DataFormatter();
            DataFormatter dataFormatter3 = new DataFormatter();
            int i=0;
            for (Row row: sheet1) {
                    for (Cell cell : row) {
                        String cellValue = dataFormatter1.formatCellValue(cell);
                        map1.put(i++,cellValue);
                    }
                }
            i=0;
            for (Row row: sheet2) {
                for (Cell cell : row) {
                    String cellValue = dataFormatter2.formatCellValue(cell);
                    map2.put(i++,cellValue);
                }
            }
            i=0;
            for (Row row: sheet3) {
                for (Cell cell : row) {
                    String cellValue = dataFormatter3.formatCellValue(cell);
                    map3.put(i++,cellValue);
                }
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        //System.out.println(map1.size());
        //System.out.println(map2.size());
       // System.out.println(map3.size());

        this.universities = new University[this.univNum];

        //generates university instances 
        for (int i = 0; i < this.univNum; ++i) {
            this.universities[i] = new University(this, i);
            //System.out.println("University = " + (i+1));
        }
        //Generate publications
        //create links across universities using Publication 'hasAuthor' some Person. 1 publication can have authors from different universities
        //also authors of 1 publication are interlinked using 'hasCollaboration' Property.
        this.publicationNum=GetRandomNo.getRandomFromRange(publicationNum_Min*univNum,publicationNum_Max*univNum);
        this.publications= new Publication[this.publicationNum];
        
        //generates publications for each university
        for (int i = 0; i < this.publicationNum; ++i) {
            this.publications[i] = new Publication(this,universities,i);
        }

        //links across universities using friendOf, sameHomeTownWith, likes, loves, isCrazyAbout, dislikes
        this.interlinks=new InterlinkedProperties(this,universities);
        
        //assign internal and external professors as advisor
        //more interlinks
        this.assignAdvisor=new AssignAdvisor(this,universities);
        
        // hasUnderGraduateDegreeFrom,hasMastersDegreeFrom,hasDoctoralDegreeFrom
        this.assignDegree=new AssignDegree(this,universities);
        
   
        OWLXMLDocumentFormat owx = new OWLXMLDocumentFormat();
        TurtleDocumentFormat ttl=new TurtleDocumentFormat();
        //RDfXMLSyntaxDocumentFormat rdf= new  RDfXMLDocumentFormat();
        FunctionalSyntaxDocumentFormat ofn = new FunctionalSyntaxDocumentFormat();
        ManchesterSyntaxDocumentFormat omn = new ManchesterSyntaxDocumentFormat();
        
        
        
        //System.out.println("Counts..."+ format+ "...."+ o.getLogicalAxiomCount());
       
        try  {
            File file = new File(System.getProperty("user.dir")+ "/" + "OWL2"+this.profile + "-" + univNum + ".owl");
try {
            if (!file.exists()) {
                //file.mkdir();
            	file.createNewFile();
            } }catch (IOException e) {
   		System.out.println("Exception Occurred:");
	        e.printStackTrace();
	  }
			
            
            System.out.println("Total Axiom Count="+ o.getAxiomCount());
            System.out.println("Total Logical Axiom Count="+ o.getLogicalAxiomCount());
            if(this.ontologyFormat.matches("owx")) {
            	manager.saveOntology(o,owx,IRI.create(file.toURI()));
            	System.out.println("Saved Ontology Format is OWL/XML");
            }
            else if(this.ontologyFormat.matches("ttl")) {
            	manager.saveOntology(o,ttl,IRI.create(file.toURI()));
            	System.out.println("Saved Ontology Format is Turtle");
            }
            else if(this.ontologyFormat.matches("ofn")) {
            	manager.saveOntology(o,ofn,IRI.create(file.toURI()));
            	System.out.println("Saved Ontology Format is OWL Functional");
            }
            else if(this.ontologyFormat.matches("omn")) {
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
            System.out.println("Finished Writing to file "+ System.getProperty("user.dir")+ "/" + "OWL2"+this.profile + "-" + univNum + ".owl" );
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

       o.getOWLOntologyManager().addAxiom(o, axiom);
       //we keep appending all the assertion axioms to TBox file.
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



