package ABoxGen.InstanceGenerator;

public class ConfigFile {
    int publicationNum_Min=300; //per university
    int publicationNum_Max=500; //per university
    int researchGroupNum_Min=10;
    int researchGroupNum_Max=20;
    int collegeNum_Min=15;
    int collegeNum_Max=25;
    int womenCollegeNum_Min=2;
    int womenCollegeNum_Max=4;
    int deptNum_Min=6;
    int deptNum_Max=10;
    int RANum_Min=5;
    int RANum_Max=10;
    int progNum_Min=1;
    int progNum_Max=3;
    int ugStudentNum_Min=60;
    int ugStudentNum_Max=100;
    int pgStudentNum_Min=20;
    int pgStudentNum_Max=40;
    int phdStudentNum_Min=2;
    int phdStudentNum_Max=10;
    int ugCourseNum_Min=20;
    int ugCourseNum_Max=35;
    int electiveCourseNum_Min=25;
    int electiveCourseNum_Max=35;
    int assistantProfessorNum_Min=15;
    int assistantProfessorNum_Max=20;
    int associateProfessorNum_Min=8;
    int associateProfessorNum_Max=15;
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
    int isFriendOfNum_Min=1;
    int isFriendOfNum_Max=4;
    int likesNum_Min=1;
    int likesNum_Max=3;
    int lovesNum_Min =0;
    int lovesNum_Max=2;
    int isCrazyAboutNum_Min=0;
    int isCrazyAboutNum_Max=1;
    int dislikesNum_Min=0;
    int dislikesNum_Max=1;


    String[] TOKEN_CollegeDiscipline= new String[]{"Engineering", "FineArts", "HumanitiesAndSocial","Management", "Science"};
    String[] TOKEN_Engineering = new String[]{"AeronauticalEngineering","BiomedicalEngineering","ChemicalEngineering","CivilEngineering","ComputerEngineering","ElectricalEngineering","IndustryEngineering","MaterialScienceEngineering","MechanicalEngineering","PetroleumEngineering"};
    String[] TOKEN_Management = new String[]{"DesignManagement", "FinancialAndAccountingManagement", "HumanResourceManagement", "MarketingManagement", "OperationsManagement", "ProjectManagement", "PublicRelationsManagement", "SalesManagement", "SupplyChainManagement", "RiskManagement"};
    String[] TOKEN_FineArts= new String[]{"Architecture", "AsianArts", "Drama", "LatinArts", "MediaArtsAndSciences", "MedievalArts", "ModernArts"," MusicsClass", "PerformingArts", "TheatreAndDance"};
    String[] TOKEN_Science=new String[]{"Astronomy", "Biology", "Chemistry", "ComputerScience", "GeoScience", "MarineScience", "MaterialScience", "Mathematics", "Physics", "Statistics"};
    String[] TOKEN_HumanitiesAndSocial=new String[]{"Anthropology", "Economics", "English", "History", "Humanities", "Linguistics", "ModernLanguages", "Philosophy", "Psychology", "Religions"};




}
