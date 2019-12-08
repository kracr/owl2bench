package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class Department {

    int deptIndex;
    int collegeIndex, progNum, ugStudentNum, pgStudentNum, phdStudentNum, ugCourseNum, electiveCourseNum;
    int totalFacultyNum, assistantProfessorNum, associateProfessorNum, fullProfessorNum, visitingProfessorNum, lecturerNum, postDocNum, systemStaffNum, clericalStaffNum, otherStaffNum;
    Student[] ugStudents, pgStudents, phdStudents;
    Employee[] assistantProfessors, associateProfessors, fullProfessors, visitingProfessors, lecturers, postDocs, systemStaffs, clericalStaffs, otherStaffs;
    Course[] ugCourses,electiveCourses;
    Generator gen;
    Boolean womenStudents;
    String departmentInstance, programInstance;
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

        //number of courses
        this.ugCourseNum = GetRandomNo.getRandomFromRange(gen.ugCourseNum_Min,gen.ugCourseNum_Max); //ug students take 4 ug courses
        this.electiveCourseNum = GetRandomNo.getRandomFromRange(gen.electiveCourseNum_Min,gen.electiveCourseNum_Max); //ug students any 2 elective
        //number of programs in the deptt
        this.progNum = GetRandomNo.getRandomFromRange(gen.progNum_Min,gen.progNum_Max);
        //may be added to employee later
        deptName = assignDepartmentName(collegeDiscipline);

        if (this.womenStudents) {
            this.departmentInstance = college.collegeInstance + "DeptOf" + deptName + this.deptIndex;
            gen.classAssertion(gen.getClass("Department"),gen.getNamedIndividual(departmentInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isDepartmentOf"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(college.collegeInstance));
            generateStudents(womenStudents,progNum);
        } else {
            this.departmentInstance = college.collegeInstance + "DeptOf" + deptName + this.deptIndex;
            gen.classAssertion(gen.getClass("Department"),gen.getNamedIndividual(departmentInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isDepartmentOf"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(college.collegeInstance));
            generateStudents(womenStudents,progNum);
        }

        generateEmployees();
        generateCourses();
        //department chair
        chair=fullProfessors[GetRandomNo.getRandomFromRange(0,fullProfessorNum-1)].employeeInstance;
        gen.objectPropertyAssertion(gen.getObjectProperty("hasHead"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(chair));


    }

    public void generateStudents(Boolean womenStudents,int progNum) {
        this.womenStudents = womenStudents;


        if (progNum == 1) {
            this.ugProgram= new Program(this,"UG");
            //this.programInstance=this.ugProgram.programInstance;
            this.ugStudentNum = GetRandomNo.getRandomFromRange(gen.ugStudentNum_Min,gen.ugStudentNum_Max); //change numbers later
            this.ugStudents = new Student[this.ugStudentNum];
            for (int i = 0; i < this.ugStudentNum; ++i) {
                this.ugStudents[i] = new Student(this , i,"UG");
            }
        }

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
        for (int i = 0; i < this.assistantProfessorNum; ++i) {
            this.assistantProfessors[i] = new Employee(this,i,"AssistantProfessor");
        }
        for (int i = 0; i < this.associateProfessorNum; ++i) {
            this.associateProfessors[i] = new Employee(this,i,"AssociateProfessor");
        }
        for (int i = 0; i < this.fullProfessorNum; ++i) {
            this.fullProfessors[i] = new Employee(this,i,"FullProfessor");
        }
        for (int i = 0; i < this.visitingProfessorNum; ++i) {
            this.visitingProfessors[i] = new Employee(this,i,"VisitingProfessor");
        }
        for (int i = 0; i < this.lecturerNum; ++i) {
            this.lecturers[i] = new Employee(this,i,"Lecturer");
        }
        for (int i = 0; i < this.postDocNum; ++i) {
            this.postDocs[i] = new Employee(this,i,"PostDoc");
        }
        for (int i = 0; i < this.systemStaffNum; ++i) {
            this.systemStaffs[i] = new Employee(this,i,"SystemStaff");
        }
        for (int i = 0; i < this.clericalStaffNum; ++i) {
            this.clericalStaffs[i] = new Employee(this,i,"ClericalStaff");
        }
        for (int i = 0; i < this.otherStaffNum; ++i) {
            this.otherStaffs[i] = new Employee(this,i,"OtherStaff");
        }

    }

    public void generateCourses() {
        this.ugCourses= new Course[this.ugCourseNum];
        this.electiveCourses= new Course[this.electiveCourseNum];
        for (int i = 0; i < this.ugCourseNum; ++i) {
            this.ugCourses[i] = new Course(this,i,"UGCourse_");
        }
        for (int i = 0; i < this.electiveCourseNum; ++i) {
            this.electiveCourses[i] = new Course(this, i,"ElectiveCourse_");
        }

    }

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
