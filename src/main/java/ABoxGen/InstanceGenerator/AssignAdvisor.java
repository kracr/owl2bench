package ABoxGen.InstanceGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class AssignAdvisor {
    University university;
    GetRandomPerson getRandomPerson;
    University universities[];
    Generator gen;
    String advisor;
    College clg;
    Department dept;
    int internalAdvisorNum, externalAdvisorNum;
    HashSet<String> hash1, hash2;
    ConfigFile configFile;

    public AssignAdvisor(Generator gen,University universities[]) {

        this.gen = gen;
        this.universities = universities;
        int i;
        getRandomPerson = new GetRandomPerson();
        configFile=new ConfigFile();
        for (i = 0; i < gen.univNum; ++i) {
            this.university = universities[i];
            for (int j = 0; j < university.coEdCollegeNum; ++j) {
                this.clg = university.coEdColleges[j];
                for (int k = 0; k < clg.deptNum; ++k) {
                    this.dept = clg.depts[k];
                    for (int l = 0; l < dept.ugStudentNum; ++l) {
                        hash1 = new HashSet();
                        hash2 = new HashSet();
                        internalAdvisorNum = GetRandomNo.getRandomFromRange(gen.internalAdvisorNum_Min,gen.internalAdvisorNum_Max);
                        externalAdvisorNum = GetRandomNo.getRandomFromRange(gen.externalAdvisorNum_Min,gen.externalAdvisorNum_Max);
                        for (int m = 0; m < internalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomInternalProfessor(dept);
                            if (advisor != null) {
                                hash1.add(advisor);
                            }
                        }
                        Iterator<String> n = hash1.iterator();
                        while (n.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.ugStudents[l].studentInstance),gen.getNamedIndividual(n.next()));
                        }

                        for (int m = 0; m < externalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomExternalProfessor(universities,i);
                            if (advisor != null) {
                                hash2.add(advisor);
                            }
                        }
                        Iterator<String> o = hash2.iterator();
                        while (o.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.ugStudents[l].studentInstance),gen.getNamedIndividual(o.next()));
                        }
                    }
                    for (int l = 0; l < dept.pgStudentNum; ++l) {
                        hash1 = new HashSet();
                        hash2 = new HashSet();
                        internalAdvisorNum = GetRandomNo.getRandomFromRange(gen.internalAdvisorNum_Min,gen.internalAdvisorNum_Max);
                        externalAdvisorNum = GetRandomNo.getRandomFromRange(gen.externalAdvisorNum_Min,gen.externalAdvisorNum_Max);
                        for (int m = 0; m < internalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomInternalProfessor(dept);
                            if (advisor != null) {
                                hash1.add(advisor);
                            }
                        }
                        Iterator<String> n = hash1.iterator();
                        while (n.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.pgStudents[l].studentInstance),gen.getNamedIndividual(n.next()));
                        }

                        for (int m = 0; m < externalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomExternalProfessor(universities,i);
                            if (advisor != null) {
                                hash2.add(advisor);
                            }
                        }
                        Iterator<String> o = hash2.iterator();
                        while (o.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.pgStudents[l].studentInstance),gen.getNamedIndividual(o.next()));
                        }

                    }
                    for (int l = 0; l < dept.phdStudentNum; ++l) {
                        hash1 = new HashSet();
                        hash2 = new HashSet();
                        internalAdvisorNum = GetRandomNo.getRandomFromRange(gen.internalAdvisorNum_Min,gen.internalAdvisorNum_Max);
                        externalAdvisorNum = GetRandomNo.getRandomFromRange(gen.externalAdvisorNum_Min,gen.externalAdvisorNum_Max);
                        for (int m = 0; m < internalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomInternalProfessor(dept);
                            if (advisor != null) {
                                hash1.add(advisor);
                            }
                        }
                        Iterator<String> n = hash1.iterator();
                        while (n.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(n.next()));
                        }

                        for (int m = 0; m < externalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomExternalProfessor(universities,i);
                            if (advisor != null) {
                                hash2.add(advisor);
                            }
                        }
                        Iterator<String> o = hash2.iterator();
                        while (o.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(o.next()));
                        }

                    }
                }
            }
            for (int j = 0; j < university.womenCollegeNum; ++j) {
                this.clg = university.womenColleges[j];
                for (int k = 0; k < clg.deptNum; ++k) {
                    this.dept = clg.depts[k];
                    for (int l = 0; l < dept.ugStudentNum; ++l) {
                        hash1 = new HashSet();
                        hash2 = new HashSet();
                        internalAdvisorNum = GetRandomNo.getRandomFromRange(gen.internalAdvisorNum_Min,gen.internalAdvisorNum_Max);
                        externalAdvisorNum = GetRandomNo.getRandomFromRange(gen.externalAdvisorNum_Min,gen.externalAdvisorNum_Max);
                        for (int m = 0; m < internalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomInternalProfessor(dept);
                            if (advisor != null) {
                                hash1.add(advisor);
                            }
                        }
                        Iterator<String> n = hash1.iterator();
                        while (n.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.ugStudents[l].studentInstance),gen.getNamedIndividual(n.next()));
                        }

                        for (int m = 0; m < externalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomExternalProfessor(universities,i);
                            if (advisor != null) {
                                hash2.add(advisor);
                            }
                        }
                        Iterator<String> o = hash2.iterator();
                        while (o.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.ugStudents[l].studentInstance),gen.getNamedIndividual(o.next()));
                        }

                    }
                    for (int l = 0; l < dept.pgStudentNum; ++l) {
                        hash1 = new HashSet();
                        hash2 = new HashSet();
                        internalAdvisorNum = GetRandomNo.getRandomFromRange(gen.internalAdvisorNum_Min,gen.internalAdvisorNum_Max);
                        externalAdvisorNum = GetRandomNo.getRandomFromRange(gen.externalAdvisorNum_Min,gen.externalAdvisorNum_Max);
                        for (int m = 0; m < internalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomInternalProfessor(dept);
                            if (advisor != null) {
                                hash1.add(advisor);
                            }
                        }
                        Iterator<String> n = hash1.iterator();
                        while (n.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.pgStudents[l].studentInstance),gen.getNamedIndividual(n.next()));
                        }

                        for (int m = 0; m < externalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomExternalProfessor(universities,i);
                            if (advisor != null) {
                                hash2.add(advisor);
                            }
                        }
                        Iterator<String> o = hash2.iterator();
                        while (o.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.pgStudents[l].studentInstance),gen.getNamedIndividual(o.next()));
                        }

                    }
                    for (int l = 0; l < dept.phdStudentNum; ++l) {
                        hash1 = new HashSet();
                        hash2 = new HashSet();
                        internalAdvisorNum = GetRandomNo.getRandomFromRange(gen.internalAdvisorNum_Min,gen.internalAdvisorNum_Max);
                        externalAdvisorNum = GetRandomNo.getRandomFromRange(gen.externalAdvisorNum_Min,gen.externalAdvisorNum_Max);
                        for (int m = 0; m < internalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomInternalProfessor(dept);
                            if (advisor != null) {
                                hash1.add(advisor);
                            }
                        }
                        Iterator<String> n = hash1.iterator();
                        while (n.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(n.next()));
                        }

                        for (int m = 0; m < externalAdvisorNum; ++m) {
                            advisor = getRandomPerson.getRandomExternalProfessor(universities,i);
                            if (advisor != null) {
                                hash2.add(advisor);
                            }
                        }
                        Iterator<String> o = hash2.iterator();
                        while (o.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("isAdvisedBy"),gen.getNamedIndividual(dept.phdStudents[l].studentInstance),gen.getNamedIndividual(o.next()));
                        }

                    }
                }

            }
        }

    }
}