/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */


package ABoxGen.InstanceGenerator;

public class ConfigFile {
	
		//Commented lines after null represent default values used for min and max
	
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
	    Integer isFriendOfNum_Min=null;			// default=1;
	    Integer isFriendOfNum_Max=null;			// default=4;
	    Integer likesNum_Min=null;				// default=1;
	    Integer likesNum_Max=null;				// default=3;
	    Integer lovesNum_Min =null;				// default=0;
	    Integer lovesNum_Max=null;				// default=2;
	    Integer isCrazyAboutNum_Min=null;		// default=0;
	    Integer isCrazyAboutNum_Max=null;		// default=1;
	    Integer dislikesNum_Min=null;			// default=0;
	    Integer dislikesNum_Max=null;			// default=1;
	
	/*
    Integer publicationNum_Min=50;   // default 300 per university
    Integer publicationNum_Max=100;   //;   //500;
    Integer researchGroupNum_Min=null;    //10;
    Integer researchGroupNum_Max=null;//=20;
    Integer collegeNum_Min=2;//15;
    Integer collegeNum_Max=3;//=25;
    Integer womenCollegeNum_Min=0;//=2;
    Integer womenCollegeNum_Max=1;//=4;
    Integer deptNum_Min=2;//=6;
    Integer deptNum_Max=3;//=10;
    Integer RANum_Min=null;//=5;
    Integer RANum_Max=null;//=10;
    Integer progNum_Min=null;//=1;
    Integer progNum_Max=null;//=3;
    Integer ugStudentNum_Min=5;//=60;
    Integer ugStudentNum_Max=10;//=100;
    Integer pgStudentNum_Min=5;//=20;
    Integer pgStudentNum_Max=10;//=40;
    Integer phdStudentNum_Min=null;//=2;
    Integer phdStudentNum_Max=5;//=10;
    //Integer ugCourseNum_Min=null;//=20;
    //Integer ugCourseNum_Max=null;//=35;
    //Integer electiveCourseNum_Min=null;//=25;
    //Integer electiveCourseNum_Max=null;//=35;
    Integer assistantProfessorNum_Min=null;//=15;
    Integer assistantProfessorNum_Max=null;//=20;
    Integer associateProfessorNum_Min=null;//=8;
    Integer associateProfessorNum_Max=null;//=15;
    Integer fullProfessorNum_Min=null;//=5;
    Integer fullProfessorNum_Max=null;//=10;
    Integer visitingProfessorNum_Min=null;//=2;
    Integer visitingProfessorNum_Max=null;//=5;
    Integer lecturerNum_Min=null;//=5;
    Integer lecturerNum_Max=null;//=10;
    Integer postDocNum_Min=null;//=2;
    Integer postDocNum_Max=null;//=7;
    Integer systemStaffNum_Min=null;//=2;
    Integer systemStaffNum_Max=null;//=5;
    Integer clericalStaffNum_Min=null;//=2;
    Integer clericalStaffNum_Max=null;//=8;
    Integer otherStaffNum_Min=null;//=15;
    Integer otherStaffNum_Max=null;//=25;
    Integer internalAdvisorNum_Min=null;//=1;
    Integer internalAdvisorNum_Max=null;//=2;
    Integer externalAdvisorNum_Min=null;//=0;
    Integer externalAdvisorNum_Max=null;//=2;
    Integer numOfElectives_Min=null;//=1;
    Integer numOfElectives_Max=null;//=4;
    Integer numOfElectivesOutsideDept_Min=null;//=1;
    Integer sameHomeTownNum_Min=null;//=0;
    Integer sameHomeTownNum_Max=null;//=3;
    Integer isFriendOfNum_Min=null;//=1;
    Integer isFriendOfNum_Max=null;//=4;
    Integer likesNum_Min=null;//=1;
    Integer likesNum_Max=null;//=3;
    Integer lovesNum_Min =null;//=0;
    Integer lovesNum_Max=null;//=2;
    Integer isCrazyAboutNum_Min=null;//=0;
    Integer isCrazyAboutNum_Max=null;//=1;
    Integer dislikesNum_Min=null;//=0;
    Integer dislikesNum_Max=null;//=1;
*/
}
