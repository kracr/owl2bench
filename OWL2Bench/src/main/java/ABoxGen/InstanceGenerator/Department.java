/*Each department has a certain number of Student, Faculties and courses.range is specified in generator.java */
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class Department {

    int deptIndex,count=0,count2=0;
    int collegeIndex, progNum, ugStudentNum, pgStudentNum, phdStudentNum,courseNum, ugCourseNum, electiveCourseNum;
    int totalFacultyNum, assistantProfessorNum, associateProfessorNum, fullProfessorNum, visitingProfessorNum, lecturerNum, postDocNum, systemStaffNum, clericalStaffNum, otherStaffNum;
    Student[] ugStudents, pgStudents, phdStudents;
    Employee[] faculty,assistantProfessors, associateProfessors, fullProfessors, visitingProfessors, lecturers, postDocs, systemStaffs, clericalStaffs, otherStaffs;
    Course[] ugCourses,electiveCourses;
    Generator gen;
    Boolean womenStudents;
    String departmentInstance, programInstance, departmentName;
    String collegeDiscipline,profile;
    String deptName;
    Program ugProgram,pgProgram,phdProgram;
    HashSet<String> personPerUniversity;
    String chair;
    ConfigFile configFile;

    public Department(College college,int deptIndex,Boolean womenStudents) {
    	this.profile=college.profile;
        this.personPerUniversity=college.personPerUniversity;
        this.collegeIndex = college.collegeIndex;
        this.deptIndex = deptIndex;
        this.gen = college.gen;
        this.womenStudents = womenStudents;
        this.collegeDiscipline = college.collegeDiscipline;
        configFile=new ConfigFile();

        //employee count
        this.assistantProfessorNum = GetRandomNo.getRandomFromRange(gen.assistantProfessorNum_Min,gen.assistantProfessorNum_Max);
        this.associateProfessorNum = GetRandomNo.getRandomFromRange(gen.associateProfessorNum_Min,gen.associateProfessorNum_Max);
        this.fullProfessorNum = GetRandomNo.getRandomFromRange(gen.fullProfessorNum_Min,gen.fullProfessorNum_Max);
        this.visitingProfessorNum = GetRandomNo.getRandomFromRange(gen.visitingProfessorNum_Min,gen.visitingProfessorNum_Max);
        this.lecturerNum = GetRandomNo.getRandomFromRange(gen.lecturerNum_Min,gen.lecturerNum_Max);
        this.postDocNum = GetRandomNo.getRandomFromRange(gen.postDocNum_Min,gen.postDocNum_Max);
        this.totalFacultyNum = assistantProfessorNum + associateProfessorNum + fullProfessorNum + visitingProfessorNum + lecturerNum;
        this.systemStaffNum = GetRandomNo.getRandomFromRange(gen.systemStaffNum_Min,gen.systemStaffNum_Max);
        this.clericalStaffNum = GetRandomNo.getRandomFromRange(gen.clericalStaffNum_Min,gen.clericalStaffNum_Max);
        this.otherStaffNum = GetRandomNo.getRandomFromRange(gen.otherStaffNum_Min,gen.otherStaffNum_Max);
        //students count
        this.courseNum=totalFacultyNum+postDocNum;
        //number of courses
        this.ugCourseNum = GetRandomNo.getRandomFromRange((courseNum/2),2*(courseNum/3)); //ug students take 4 ug courses
        this.electiveCourseNum = courseNum-ugCourseNum;
        // GetRandomNo.//getRandomFromRange(gen.electiveCourseNum_Min,gen.electiveCourseNum_Max); ug students any 2 elective
        //number of programs in the deptt
        this.progNum = GetRandomNo.getRandomFromRange(gen.progNum_Min,gen.progNum_Max);
        //may be added to employee later
        deptName = assignDepartmentName(collegeDiscipline);
        departmentName= "Department of "+ deptName;
        //if department belongs to women college , implies all women students
        if (this.womenStudents) {
            this.departmentInstance = college.collegeInstance + "D" + this.deptIndex;
            gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(departmentInstance),gen.getLiteral(departmentName));
            gen.classAssertion(gen.getClass("Department"),gen.getNamedIndividual(departmentInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isPartOf"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(college.collegeInstance));
            generateStudents(womenStudents,progNum);
        } else {
        	this.departmentInstance = college.collegeInstance + "D" + this.deptIndex;
        	gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(departmentInstance),gen.getLiteral(departmentName));
            gen.classAssertion(gen.getClass("Department"),gen.getNamedIndividual(departmentInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isPartOf"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(college.collegeInstance));
            generateStudents(womenStudents,progNum);
        }

        generateEmployees();
        generateCourses();
        //department chair
        chair=fullProfessors[GetRandomNo.getRandomFromRange(0,fullProfessorNum-1)].employeeInstance;
        gen.objectPropertyAssertion(gen.getObjectProperty("isHeadOf"),gen.getNamedIndividual(chair),gen.getNamedIndividual(departmentInstance));


    }

    public void generateStudents(Boolean womenStudents,int progNum) {
        this.womenStudents = womenStudents;

        //if department has just one progarm, that means UG program. So, generate ug students
        if (progNum == 1) {
            this.ugProgram= new Program(this,"UG");
            //this.programInstance=this.ugProgram.programInstance;
            this.ugStudentNum = GetRandomNo.getRandomFromRange(gen.ugStudentNum_Min,gen.ugStudentNum_Max); //change numbers later
            this.ugStudents = new Student[this.ugStudentNum];
            for (int i = 0; i < this.ugStudentNum; ++i) {
                this.ugStudents[i] = new Student(this , i,"UG");
            }
        }
        //if 2 programs, means ug and pg program. So generate both ug and pg students
        else if (progNum == 2) {
            this.ugProgram= new Program(this,"UG");
            //this.programInstance=this.ugProgram.programInstance;
            this.ugStudentNum = GetRandomNo.getRandomFromRange(gen.ugStudentNum_Min,gen.ugStudentNum_Max); //change numbers later
            this.pgStudentNum = GetRandomNo.getRandomFromRange(gen.pgStudentNum_Min,gen.pgStudentNum_Max);
            this.ugStudents = new Student[this.ugStudentNum];
            this.pgStudents = new Student[this.pgStudentNum];

            for (int i = 0; i < this.ugStudentNum; ++i) {
                this.ugStudents[i] = new Student(this,i,"UG");
            }

            this.pgProgram= new Program(this,"PG");
            //this.programInstance=this.pgProgram.programInstance;
            for (int i = 0; i < this.pgStudentNum; ++i) {
                this.pgStudents[i] = new Student(this,i,"PG");
            }
        }
        //if dept has 3 programs, generate ug, pg and phd students
        else if (progNum == 3) {
            this.ugProgram= new Program(this,"UG");
            //this.programInstance=this.ugProgram.programInstance;
            this.ugStudentNum = GetRandomNo.getRandomFromRange(gen.ugStudentNum_Min,gen.ugStudentNum_Max); //change numbers later
            this.pgStudentNum = GetRandomNo.getRandomFromRange(gen.pgStudentNum_Min,gen.pgStudentNum_Max);
            this.phdStudentNum = GetRandomNo.getRandomFromRange(gen.phdStudentNum_Min,gen.phdStudentNum_Max);
            this.ugStudents = new Student[this.ugStudentNum];
            this.pgStudents = new Student[this.pgStudentNum];
            this.phdStudents = new Student[this.phdStudentNum];
            for (int i = 0; i < this.ugStudentNum; ++i) {
                this.ugStudents[i] = new Student(this,i,"UG");
            }

            this.pgProgram= new Program(this,"PG");
            //this.programInstance=this.pgProgram.programInstance;
            for (int i = 0; i < this.pgStudentNum; ++i) {
                this.pgStudents[i] = new Student(this,i,"PG");
            }
            this.phdProgram= new Program(this,"PhD");
            //this.programInstance=this.phdProgram.programInstance;
            for (int i = 0; i < this.phdStudentNum; ++i) {
                this.phdStudents[i] = new Student(this,i,"PhD");
            }
        }
    }
    //generate different types of employees
    public void generateEmployees() {
        this.assistantProfessors = new Employee[this.assistantProfessorNum];
        this.associateProfessors = new Employee[this.associateProfessorNum];
        this.fullProfessors = new Employee[this.fullProfessorNum];
        this.visitingProfessors = new Employee[this.visitingProfessorNum];
        this.lecturers = new Employee[this.lecturerNum];
        this.systemStaffs = new Employee[this.systemStaffNum];
        this.clericalStaffs = new Employee[this.clericalStaffNum];
        this.otherStaffs= new Employee[this.otherStaffNum];
        this.postDocs= new Employee[this.postDocNum];
        this.faculty=new Employee[this.courseNum];
        for (int i = 0; i < this.assistantProfessorNum; ++i) {
            this.assistantProfessors[i] = new Employee(this,i,"AssistantProfessor","AP");
            faculty[count]=assistantProfessors[i];
            count=count+1;
        }
        for (int i = 0; i < this.associateProfessorNum; ++i) {
            this.associateProfessors[i] = new Employee(this,i,"AssociateProfessor","AssocP");
            faculty[count]=associateProfessors[i];
            count=count+1;
        }
        for (int i = 0; i < this.fullProfessorNum; ++i) {
            this.fullProfessors[i] = new Employee(this,i,"FullProfessor","FP");
            faculty[count]=fullProfessors[i];
            count=count+1;
        }
        for (int i = 0; i < this.visitingProfessorNum; ++i) {
            this.visitingProfessors[i] = new Employee(this,i,"VisitingProfessor","VP");
            faculty[count]=visitingProfessors[i];
            count=count+1;
        }
        for (int i = 0; i < this.lecturerNum; ++i) {
            this.lecturers[i] = new Employee(this,i,"Lecturer","L");
            faculty[count]=lecturers[i];
            count=count+1;
        }
        for (int i = 0; i < this.postDocNum; ++i) {
            this.postDocs[i] = new Employee(this,i,"PostDoc","PD");
            faculty[count]=postDocs[i];
            count=count+1;
        }
        for (int i = 0; i < this.systemStaffNum; ++i) {
            this.systemStaffs[i] = new Employee(this,i,"SystemStaff","SS");
        }
        for (int i = 0; i < this.clericalStaffNum; ++i) {
            this.clericalStaffs[i] = new Employee(this,i,"ClericalStaff","CS");
        }
        for (int i = 0; i < this.otherStaffNum; ++i) {
            this.otherStaffs[i] = new Employee(this,i,"OtherStaff","OS");
        }

    }
    //generate courses (UG mandatory courses and electives
    public void generateCourses() {
        this.ugCourses= new Course[this.ugCourseNum];
        this.electiveCourses= new Course[this.electiveCourseNum];
        for (int i = 0; i < this.ugCourseNum; ++i) {
            this.ugCourses[i] = new Course(this,i,"UGCourse");
            count2=count2+1;
        }
        for (int i = 0; i < this.electiveCourseNum; ++i) {
            this.electiveCourses[i] = new Course(this, count2,"ElectiveCourse");
            count2=count2+1;
        }

    }
    //give names to dept , like Department of Mechanical Engineering
    public String assignDepartmentName(String collegeDiscipline) {
        if (collegeDiscipline == "Engineering") {
            return gen.TOKEN_Engineering[GetRandomNo.getRandomFromRange(0,9)];
        }
        else if (collegeDiscipline == "Management") {
            return gen.TOKEN_Management[GetRandomNo.getRandomFromRange(0,9)];
        }
        else if (collegeDiscipline == "FineArts") {
            return gen.TOKEN_FineArts[GetRandomNo.getRandomFromRange(0,9)];
        }
        else if (collegeDiscipline == "Science") {
            return gen.TOKEN_Science[GetRandomNo.getRandomFromRange(0,9)];
        }
        else if (collegeDiscipline == "HumanitiesAndSocial") {
           return gen.TOKEN_HumanitiesAndSocial[GetRandomNo.getRandomFromRange(0,9)];
        }
        else
            return "Dept";
    }
}
