/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;


public class ConfigFile {
	
		//Commented lines after null represent default values used for min and max
	String ontologyFormat="rdf"; //format of generated default RDF-XML
	//owx for OWL_XML, ofn for OWL_Functional, omn for OWL_Manchester, ttl for Turtle , rdf for RDF_XML,
	Integer publicationNum_Min=null;   		// default=300 per university
    Integer publicationNum_Max=null;   		// default=500;
    Integer researchGroupNum_Min=null;    	// default=10;
    Integer researchGroupNum_Max=null;		// default=20;
    Integer collegeNum_Min=null;			// default=15;
    Integer collegeNum_Max=null;			// default=25;
    Integer womenCollegeNum_Min=null;		// default=2;
    Integer womenCollegeNum_Max=null;		// default=4;
    Integer deptNum_Min=null;				// default=6;
    Integer deptNum_Max=null;				// default=10;
    Integer RANum_Min=null;					// default=5;
    Integer RANum_Max=null;					// default=10;
    Integer progNum_Min=null;				// default=1;
    Integer progNum_Max=null;				// default=3;
    Integer ugStudentNum_Min=null;			// default=60;
    Integer ugStudentNum_Max=null;			// default=100;
    Integer pgStudentNum_Min=null;			// default=20;
    Integer pgStudentNum_Max=null;			// default=40;
    Integer phdStudentNum_Min=null;			// default=2;
    Integer phdStudentNum_Max=null;			// default=10;
    Integer ugCourseNum_Min=null;           // default=20;
    Integer ugCourseNum_Max=null;           // default=35;
    Integer electiveCourseNum_Min=null;     // default=25;
    Integer electiveCourseNum_Max=null;     // default=35;
    Integer assistantProfessorNum_Min=null; // default=15;
    Integer assistantProfessorNum_Max=null; // default=20;
    Integer associateProfessorNum_Min=null; // default=8;
    Integer associateProfessorNum_Max=null; // default=15;
    Integer fullProfessorNum_Min=null;		// default=5;
    Integer fullProfessorNum_Max=null;		// default=10;
    Integer visitingProfessorNum_Min=null;  // default=2;
    Integer visitingProfessorNum_Max=null;  // default=5;
    Integer lecturerNum_Min=null;			// default=5;
    Integer lecturerNum_Max=null;			// default=10;
    Integer postDocNum_Min=null;			// default=2;
    Integer postDocNum_Max=null;			// default=7;
    Integer systemStaffNum_Min=null;		// default=2;
    Integer systemStaffNum_Max=null;		// default=5;
    Integer clericalStaffNum_Min=null;		// default=2;
    Integer clericalStaffNum_Max=null;		// default=8;
    Integer otherStaffNum_Min=null;			// default=15;
    Integer otherStaffNum_Max=null;			// default=25;
    Integer internalAdvisorNum_Min=null;	// default=1;
    Integer internalAdvisorNum_Max=null;	// default=2;
    Integer externalAdvisorNum_Min=null;	// default=0;
    Integer externalAdvisorNum_Max=null;	// default=2;
    Integer numOfElectives_Min=null;		// default=1;
    Integer numOfElectives_Max=null;		// default=4;
    Integer numOfElectivesOutsideDept_Min=null;// default=1;
    Integer sameHomeTownNum_Min=null;		// default=0;
    Integer sameHomeTownNum_Max=null;		// default=3;
    Integer knowsNum_Min=null;			// default=1;
    Integer knowsNum_Max=null;			// default=4;
    Integer likesNum_Min=null;				// default=1;
    Integer likesNum_Max=null;				// default=3;
    Integer lovesNum_Min =null;				// default=0;
    Integer lovesNum_Max=null;				// default=2;
    Integer isCrazyAboutNum_Min=null;		// default=0;
    Integer isCrazyAboutNum_Max=null;		// default=1;
    Integer dislikesNum_Min=null;			// default=0;
    Integer dislikesNum_Max=null;			// default=1;  
	

	/*
	For about 50K triples in each profile
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
    int sameHomeTownNum_Max=3;
    //int knowsNum_Min=1;
    //int knowsNum_Max=4;
    int likesNum_Min=1;
    int likesNum_Max=3;
    int lovesNum_Min =0;
    int lovesNum_Max=2;
    int isCrazyAboutNum_Min=0;
    int isCrazyAboutNum_Max=1;
    int dislikesNum_Min=0;
    int dislikesNum_Max=1;
    */
}
