//gets a random faculty, internal professor, external professor, student or staff instance when required for other axioms. 
//For. Eg. to assign advisor, or to assign hasSameHomeTownWith property, evaluationcommittee etc
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;


import org.apache.commons.collections4.Get;

public class GetRandomPerson {
    String person,personCategory,advisor,profile;
    Generator gen;
    int univNum,randomUnivNum,womenCollegeNum,collegeNum,randomWomenCollegeNum,coEdCollegeNum,randomCollegeNum,randomCoEdCollegeNum,deptNum,randomDeptNum,personNum,randomPersonNum;
    University univ,otherUniv;
    College clg;
    Department dept;
    Student student;
    Employee employee;
    String TOKEN[]={"UGStudent","PGStudent", "PhDStudent","AssistantProfessor","AssociateProfessor","FullProfessor","VisitingProfessor","Lecturer","PostDoc","SystemStaff","ClericalStaff","OtherStaff"};
    int otherUnivIndex;
    public GetRandomPerson(){

    }
    public String getRandomStudentOrFaculty(Generator gen, University universities[]) //across universities for publications
    {
        this.gen=gen;
        this.profile=gen.profile;
        this.univNum=gen.univNum;
        this.randomUnivNum=GetRandomNo.getRandomFromRange(0,univNum-1);

        this.univ=universities[randomUnivNum];
        if ((profile.matches("DL")) || (profile.matches("RL"))) {
        if(GetRandomNo.getRandomFromRange(0,10)==0) {
            this.womenCollegeNum = univ.womenCollegeNum;
            this.randomWomenCollegeNum = GetRandomNo.getRandomFromRange(0,womenCollegeNum - 1);
            this.clg=univ.womenColleges[randomWomenCollegeNum];
        }
        else {
            this.coEdCollegeNum = univ.coEdCollegeNum;
            //System.out.println(coEdCollegeNum);
            this.randomCoEdCollegeNum = GetRandomNo.getRandomFromRange(0,coEdCollegeNum - 1);
            this.clg=univ.coEdColleges[randomCoEdCollegeNum];
        }
        }
        else
        {
        	this.collegeNum=univ.collegeNum;
            this.randomCollegeNum = GetRandomNo.getRandomFromRange(0,collegeNum - 1);
            this.clg=univ.colleges[randomCollegeNum];   	
        }
        this.deptNum=clg.deptNum;
        this.randomDeptNum=GetRandomNo.getRandomFromRange(0,deptNum-1);
        this.dept=clg.depts[randomDeptNum];
        personCategory=TOKEN[GetRandomNo.getRandomFromRange(0,8)];
        if(personCategory=="UGStudent") {
            this.personNum = dept.ugStudentNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.student=dept.ugStudents[randomPersonNum];
            person=student.studentInstance;
        }
        else if(personCategory=="PGStudent") {
            this.personNum = dept.pgStudentNum;
            if(personNum!=0)
            {
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.student=dept.pgStudents[randomPersonNum];
            person=student.studentInstance;
            }
        }
        if(personCategory=="PhDStudent") {
            this.personNum = dept.phdStudentNum;
            if(personNum!=0)
            {
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
                this.student=dept.phdStudents[randomPersonNum];
                person=student.studentInstance;
            }
        }
        if(personCategory=="AssistantProfessor") {
            this.personNum = dept.assistantProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.assistantProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        if(personCategory=="AssociateProfessor") {
            this.personNum = dept.associateProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.associateProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        if(personCategory=="FullProfessor") {
            this.personNum = dept.fullProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.fullProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        if(personCategory=="VisitingProfessor") {
            this.personNum = dept.visitingProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.visitingProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        if(personCategory=="Lecturer") {
            this.personNum = dept.lecturerNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.lecturers[randomPersonNum];
            person=employee.employeeInstance;
        }
        if(personCategory=="PostDoc") {
            this.personNum = dept.postDocNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.postDocs[randomPersonNum];
            person=employee.employeeInstance;
        }
        return person;
    }
    public String getRandomInternalProfessor(Department dept)
    {
        this.dept= dept;
       person= dept.fullProfessors[GetRandomNo.getRandomFromRange(0,dept.fullProfessorNum-1)].employeeInstance;
        return person;
    }
    public String getRandomExternalProfessor( University universities[], int univIndex){ //for advisors, for external comittee{

        if(univIndex !=0 && univIndex!= (universities.length-1)) {
            if (GetRandomNo.getRandomFromRange(0,1) == 0) {
                otherUnivIndex = GetRandomNo.getRandomFromRange(0,univIndex - 1);
            } else {
                otherUnivIndex = GetRandomNo.getRandomFromRange(univIndex + 1,universities.length-1);
            }
        }
        else if (univIndex==0 && universities.length!=1)
        {
            otherUnivIndex=GetRandomNo.getRandomFromRange(1,universities.length-1);
        }
        else if (univIndex==universities.length-1 && universities.length!=1 ){
            otherUnivIndex=GetRandomNo.getRandomFromRange(0,universities.length-2);
        }
        else if (universities.length==1)
        {
            otherUnivIndex=univIndex; //if 1 university then external advisors cant be assigned so professors from same univrsity
        }

        this.otherUniv=universities[otherUnivIndex];

        if(GetRandomNo.getRandomFromRange(0,10)==0) {
            this.womenCollegeNum = otherUniv.womenCollegeNum;
            this.randomWomenCollegeNum = GetRandomNo.getRandomFromRange(0,womenCollegeNum - 1);
            this.clg=otherUniv.womenColleges[randomWomenCollegeNum];
        }
        else {
            this.coEdCollegeNum = otherUniv.coEdCollegeNum;
            this.randomCoEdCollegeNum = GetRandomNo.getRandomFromRange(0,coEdCollegeNum - 1);
            this.clg=otherUniv.coEdColleges[randomCoEdCollegeNum];
        }
        this.randomDeptNum=GetRandomNo.getRandomFromRange(0,clg.deptNum-1);
        this.dept=clg.depts[randomDeptNum];
            this.person = dept.fullProfessors[GetRandomNo.getRandomFromRange(0,dept.fullProfessorNum - 1)].employeeInstance;
        return person;
    }

    public String getRandomStudentFacultyOrStaff(Generator gen, University universities[]) //across universities for publications
    {
        this.gen=gen;
        this.univNum=gen.univNum;
        this.profile=gen.profile;
        this.randomUnivNum=GetRandomNo.getRandomFromRange(0,univNum-1);

        this.univ=universities[randomUnivNum];
        if ((profile.matches("DL")) || (profile.matches("RL"))) {
        if(GetRandomNo.getRandomFromRange(0,10)==0) {
            this.womenCollegeNum = univ.womenCollegeNum;
            this.randomWomenCollegeNum = GetRandomNo.getRandomFromRange(0,womenCollegeNum - 1);
            this.clg=univ.womenColleges[randomWomenCollegeNum];
        }
        else {
            this.coEdCollegeNum = univ.coEdCollegeNum;
            this.randomCoEdCollegeNum = GetRandomNo.getRandomFromRange(0,coEdCollegeNum - 1);
            this.clg=univ.coEdColleges[randomCoEdCollegeNum];
        }
        }
        else
        {
        	this.collegeNum=univ.collegeNum;
            this.randomCollegeNum = GetRandomNo.getRandomFromRange(0,collegeNum - 1);
            this.clg=univ.colleges[randomCollegeNum];   	
        }
        this.deptNum=clg.deptNum;
        this.randomDeptNum=GetRandomNo.getRandomFromRange(0,deptNum-1);
        this.dept=clg.depts[randomDeptNum];
        personCategory=TOKEN[GetRandomNo.getRandomFromRange(0,11)];
        if(personCategory=="UGStudent") {
            this.personNum = dept.ugStudentNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.student=dept.ugStudents[randomPersonNum];
            person=student.studentInstance;
        }
        else if(personCategory=="PGStudent") {
            this.personNum = dept.pgStudentNum;
            if(personNum!=0)
            {
                this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
                this.student=dept.pgStudents[randomPersonNum];
                person=student.studentInstance;
            }
        }
        else if(personCategory=="PhDStudent") {
            this.personNum = dept.phdStudentNum;
            if(personNum!=0)
            {
                this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);

                this.student=dept.phdStudents[randomPersonNum];
                person=student.studentInstance;
            }
        }
        else if(personCategory=="AssistantProfessor") {
            this.personNum = dept.assistantProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.assistantProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="AssociateProfessor") {
            this.personNum = dept.associateProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.associateProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="FullProfessor") {
            this.personNum = dept.fullProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.fullProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="VisitingProfessor") {
            this.personNum = dept.visitingProfessorNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.visitingProfessors[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="Lecturer") {
            this.personNum = dept.lecturerNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.lecturers[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="PostDoc") {
            this.personNum = dept.postDocNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.postDocs[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="SystemStaff") {
            this.personNum = dept.systemStaffNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.systemStaffs[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="ClericalStaff") {
            this.personNum = dept.clericalStaffNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.clericalStaffs[randomPersonNum];
            person=employee.employeeInstance;
        }
        else if(personCategory=="OtherStaff") {
            this.personNum = dept.otherStaffNum;
            this.randomPersonNum = GetRandomNo.getRandomFromRange(0,personNum - 1);
            this.employee=dept.otherStaffs[randomPersonNum];
            person=employee.employeeInstance;
        }
        return person;
    }



}
