//Adds Object Property Assertion axioms of type hasDegreeFrom for each person(faculty,staff and students) 
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class AssignDegree {
    University university;
    //GetRandom getRandomPerson;
    University universities[];
    Generator gen;
    //String advisor;
    College clg;
    Department dept;
    int univNum;
    String randomUniv1,randomUniv2,randomUniv3,profile;

    public AssignDegree(Generator gen,University universities[]) {

        this.gen = gen;
        this.universities = universities;
        this.univNum=gen.univNum;
        this.profile=gen.profile;
        int i;
       
        //getRandomPerson = new GetRandomPerson();
        
        for (i = 0; i < gen.univNum; ++i) {
            this.university = universities[i];
           // if ((profile.matches("DL")) || (profile.matches("RL"))) {
            for (int j = 0; j < university.coEdCollegeNum; ++j) {
                this.clg = university.coEdColleges[j];
                for (int k = 0; k < clg.deptNum; ++k) {
                    this.dept = clg.depts[k];
                    for (int l = 0; l < dept.pgStudentNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.pgStudents[l].studentInstance),gen.getNamedIndividual(randomUniv1));
                    }
                    for (int l = 0; l < dept.phdStudentNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(randomUniv2));
                    }
                    for (int l = 0; l < dept.assistantProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.assistantProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.assistantProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.assistantProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.associateProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.associateProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.associateProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.associateProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.fullProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.fullProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.fullProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.fullProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.visitingProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.visitingProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.visitingProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.visitingProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.postDocNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.postDocs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.postDocs[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.postDocs[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.lecturerNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.lecturers[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.lecturers[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        }
                    for (int l = 0; l < dept.systemStaffNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.systemStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.systemStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                    }
                    for (int l = 0; l < dept.clericalStaffNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.clericalStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                         }
                    for (int l = 0; l < dept.otherStaffNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.otherStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                    }
                }
            }
            for (int j = 0; j < university.womenCollegeNum; ++j) {
                this.clg = university.womenColleges[j];
                for (int k = 0; k < clg.deptNum; ++k) {
                    this.dept = clg.depts[k];
                    for (int l = 0; l < dept.pgStudentNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.pgStudents[l].studentInstance),gen.getNamedIndividual(randomUniv1));
                    }
                    for (int l = 0; l < dept.phdStudentNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(randomUniv2));
                    }
                    for (int l = 0; l < dept.assistantProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.assistantProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.assistantProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.assistantProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.associateProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.associateProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.associateProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.associateProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.fullProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.fullProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.fullProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.fullProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.visitingProfessorNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.visitingProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.visitingProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.visitingProfessors[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.postDocNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv3 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.postDocs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.postDocs[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasDoctoralDegreeFrom"),gen.getNamedIndividual(dept.postDocs[l].employeeInstance),gen.getNamedIndividual(randomUniv3));
                    }
                    for (int l = 0; l < dept.lecturerNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.lecturers[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.lecturers[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                    }
                    for (int l = 0; l < dept.systemStaffNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        randomUniv2 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.systemStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMasterDegreeFrom"),gen.getNamedIndividual(dept.systemStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv2));
                    }
                    for (int l = 0; l < dept.clericalStaffNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.clericalStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                    }
                    for (int l = 0; l < dept.otherStaffNum; ++l) {
                        randomUniv1 = universities[GetRandomNo.getRandomFromRange(0,univNum - 1)].univInstance;
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasUndergraduateDegreeFrom"),gen.getNamedIndividual(dept.otherStaffs[l].employeeInstance),gen.getNamedIndividual(randomUniv1));
                    }
                }
            
        }
            
        }
    }
}