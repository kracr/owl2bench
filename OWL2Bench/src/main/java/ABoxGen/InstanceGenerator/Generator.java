/* Main Class that takes as input University Number, Seed and OWL 2 Profile (EL,QL,RL,DL).*/
/* Generates instances according to the specified number of universities. */
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */


package ABoxGen.InstanceGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
    String ontologyFormat;
    int publicationNum_Min=150; //per university
    int publicationNum_Max=200; //per university
    int researchGroupNum_Min=4;
    int researchGroupNum_Max=8;
    int collegeNum_Min=5;
    int collegeNum_Max=10;
    int womenCollegeNum_Min=1;
    int womenCollegeNum_Max=2;
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
    ConfigFile configFile;
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
     
            System.out.println("Please give arguments in the following order: No. of Universities (int), OWL 2 Profile (EL/QL/RL/DL), Seed value (int)");
            System.out.println("For example: 1 DL 1");
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
        this.configFile=new ConfigFile();
        if(configFile.ontologyFormat!=null)
        {
            this.ontologyFormat=configFile.ontologyFormat;
        }
        //configurations
        //If user wants to modify the min-max parameters, they can change the null value in ConfigFile. 
        if(configFile.publicationNum_Min!=null)
        {
            this.publicationNum_Min=configFile.publicationNum_Min;
        }
        if(configFile.publicationNum_Max!=null)
        {
            this.publicationNum_Max=configFile.publicationNum_Max;
        }
        if(configFile.researchGroupNum_Min!=null)
        {
            this.researchGroupNum_Min=configFile.researchGroupNum_Min;
        }
        if(configFile.researchGroupNum_Max!=null)
        {
            this.researchGroupNum_Max=configFile.researchGroupNum_Max;
        }
        if(configFile.collegeNum_Min!=null)
        {
            this.collegeNum_Min=configFile.collegeNum_Min;
        }
        if(configFile.collegeNum_Max!=null)
        {
            this.collegeNum_Max=configFile.collegeNum_Max;
        }

        if(configFile.womenCollegeNum_Min!=null)
        {
            this.womenCollegeNum_Min=configFile.womenCollegeNum_Min;
        }
        if(configFile.womenCollegeNum_Max!=null)
        {
            this.womenCollegeNum_Max=configFile.womenCollegeNum_Max;
        }
        if(configFile.RANum_Min!=null)
        {
            this.RANum_Min=configFile.RANum_Min;
        }
        if(configFile.RANum_Max!=null)
        {
            this.RANum_Max=configFile.RANum_Max;
        }
        if(configFile.sameHomeTownNum_Min!=null)
        {
            this.sameHomeTownNum_Min=configFile.sameHomeTownNum_Min;
        }
        if(configFile.sameHomeTownNum_Max!=null)
        {
            this.sameHomeTownNum_Max=configFile.sameHomeTownNum_Max;
        }
        if(configFile.knowsNum_Min!=null)
        {
            this.knowsNum_Min=configFile.knowsNum_Min;
        }
        if(configFile.knowsNum_Max!=null)
        {
            this.knowsNum_Max=configFile.knowsNum_Max;
        }
        if(configFile.isCrazyAboutNum_Min!=null)
        {
            this.isCrazyAboutNum_Min=configFile.isCrazyAboutNum_Min;
        }
        if(configFile.isCrazyAboutNum_Max!=null)
        {
            this.isCrazyAboutNum_Max=configFile.isCrazyAboutNum_Max;
        }
        if(configFile.lovesNum_Min!=null)
        {
            this.lovesNum_Min=configFile.lovesNum_Min;
        }
        if(configFile.lovesNum_Max!=null)
        {
            this.lovesNum_Max=configFile.lovesNum_Max;
        }
        if(configFile.likesNum_Min!=null)
        {
            this.likesNum_Min=configFile.likesNum_Min;
        }
        if(configFile.likesNum_Max!=null)
        {
            this.likesNum_Max=configFile.likesNum_Max;
        }
        if(configFile.dislikesNum_Min!=null)
        {
            this.dislikesNum_Min=configFile.dislikesNum_Min;
        }
        if(configFile.dislikesNum_Max!=null)
        {
            this.dislikesNum_Max=configFile.dislikesNum_Max;
        }
        if(configFile.deptNum_Min!=null)
        {
            this.deptNum_Min=configFile.deptNum_Min;
        }
        if(configFile.deptNum_Max!=null)
        {
            this.deptNum_Max=configFile.deptNum_Max;
        }
        if(configFile.numOfElectives_Min!=null)
        {
            this.numOfElectives_Min=configFile.numOfElectives_Min;
        }
        if(configFile.numOfElectives_Max!=null)
        {
            this.numOfElectives_Max=configFile.numOfElectives_Max;
        }
        if(configFile.numOfElectivesOutsideDept_Min!=null)
        {
            this.numOfElectivesOutsideDept_Min=configFile.numOfElectivesOutsideDept_Min;
        }
        if(configFile.internalAdvisorNum_Min!=null)
        {
            this.internalAdvisorNum_Min=configFile.internalAdvisorNum_Min;
        }
        if(configFile.internalAdvisorNum_Max!=null)
        {
            this.internalAdvisorNum_Max=configFile.internalAdvisorNum_Max;
        }
        if(configFile.externalAdvisorNum_Min!=null)
        {
            this.externalAdvisorNum_Min=configFile.externalAdvisorNum_Min;
        }
        if(configFile.externalAdvisorNum_Max!=null)
        {
            this.externalAdvisorNum_Max=configFile.externalAdvisorNum_Max;
        }
        if(configFile.ugStudentNum_Min!=null){
            this.ugStudentNum_Min=configFile.ugStudentNum_Min;
        }
        if(configFile.ugStudentNum_Max!=null){
            this.ugStudentNum_Max=configFile.ugStudentNum_Max;
        }
        if(configFile.pgStudentNum_Min!=null){
            this.pgStudentNum_Min=configFile.pgStudentNum_Min;
        }
        if(configFile.pgStudentNum_Max!=null){
            this.pgStudentNum_Max=configFile.pgStudentNum_Max;
        }
        if(configFile.phdStudentNum_Min!=null){
            this.phdStudentNum_Min=configFile.phdStudentNum_Min;
        }
        if(configFile.phdStudentNum_Max!=null){
            this.phdStudentNum_Max=configFile.phdStudentNum_Max;
        }
        if(configFile.assistantProfessorNum_Min!=null){
            this.assistantProfessorNum_Min=configFile.assistantProfessorNum_Min;
        }
        if(configFile.assistantProfessorNum_Max!=null){
            this.assistantProfessorNum_Max=configFile.assistantProfessorNum_Max;
        }
        if(configFile.associateProfessorNum_Min!=null){
            this.associateProfessorNum_Min=configFile.associateProfessorNum_Min;
        }
        if(configFile.associateProfessorNum_Max!=null){
            this.associateProfessorNum_Max=configFile.associateProfessorNum_Max;
        }
        if(configFile.fullProfessorNum_Min!=null){
            this.fullProfessorNum_Min=configFile.fullProfessorNum_Min;
        }
        if(configFile.fullProfessorNum_Max!=null){
            this.fullProfessorNum_Max=configFile.fullProfessorNum_Max;
        }
        if(configFile.visitingProfessorNum_Min!=null){
            this.visitingProfessorNum_Min=configFile.visitingProfessorNum_Min;
        }
        if(configFile.visitingProfessorNum_Max!=null){
            this.visitingProfessorNum_Max=configFile.visitingProfessorNum_Max;
        }
        if(configFile.lecturerNum_Min!=null){
            this.lecturerNum_Min=configFile.lecturerNum_Min;
        }
        if(configFile.lecturerNum_Max!=null){
            this.lecturerNum_Max=configFile.lecturerNum_Max;
        }
        if(configFile.postDocNum_Min!=null){
            this.postDocNum_Min=configFile.postDocNum_Min;
        }
        if(configFile.postDocNum_Max!=null){
            this.postDocNum_Max=configFile.postDocNum_Max;
        }
        if(configFile.systemStaffNum_Min!=null){
            this.systemStaffNum_Min=configFile.systemStaffNum_Min;
        }
        if(configFile.systemStaffNum_Max!=null){
            this.systemStaffNum_Max=configFile.systemStaffNum_Max;
        }
        if(configFile.clericalStaffNum_Min!=null){
            this.clericalStaffNum_Min=configFile.clericalStaffNum_Min;
        }
        if(configFile.clericalStaffNum_Max!=null){
            this.clericalStaffNum_Max=configFile.clericalStaffNum_Max;
        }
        if(configFile.otherStaffNum_Min!=null){
            this.otherStaffNum_Min=configFile.otherStaffNum_Min;
        }
        if(configFile.otherStaffNum_Max!=null){
            this.otherStaffNum_Max=configFile.otherStaffNum_Max;
        }
        if(configFile.progNum_Min!=null){
            this.progNum_Min=configFile.progNum_Min;
        }
        if(configFile.progNum_Max!=null){
            this.progNum_Max=configFile.progNum_Max;
        }

        this.generate();
    }

    private void generate() {

        try {
        	//excel file used to generate real world names for person and universities
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
        this.configFile=new ConfigFile();
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



