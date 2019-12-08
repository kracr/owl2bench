package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class College {

    int univIndex;
    int collegeIndex, deptNum;
    String collegeCode,dean;
    Department[] depts;
    Generator gen;
    boolean isWomanCollege;
    String  collegeInstance,profile;
    String collegeDiscipline;
    HashSet<String> personPerUniversity;
    AssignCourse assignCourse;
    GetRandomPerson getRandomPerson;
    ConfigFile configFile;

    public College(University university, int collegeIndex, Boolean isWomanCollege) {
        this.personPerUniversity=university.personPerUniversity;
        this.profile=university.profile;
        this.configFile=new ConfigFile();
        this.univIndex = university.univIndex;
        this.collegeIndex = collegeIndex;
        this.gen = university.gen;
        this.isWomanCollege = isWomanCollege;
        this.collegeDiscipline= gen.TOKEN_CollegeDiscipline[GetRandomNo.getRandomFromRange(0, 4)];
        getRandomPerson=new GetRandomPerson();

        if (this.isWomanCollege) {
            this.collegeInstance = university.univInstance + "WomenClgOf" + collegeDiscipline + this.collegeIndex;
            this.collegeCode = "U" + this.univIndex + "WC" + this.collegeIndex;
            gen.classAssertion(gen.getClass("College"), gen.getNamedIndividual(collegeInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isAffiliatedOrganizationOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isWomenCollegeOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasCollegeDiscipline"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(collegeDiscipline));
            this.deptNum = GetRandomNo.getRandomFromRange(gen.deptNum_Min, gen.deptNum_Max);
            this.depts = new Department[this.deptNum];

            for (int i = 0; i < this.deptNum; ++i) {
                this.depts[i] = new Department(this, i, true);
            }

            //Assign Courses
            this.assignCourse=new AssignCourse(this);
            if ((profile.matches("DL")) || (profile.matches("RL")) || (profile.matches("EL"))) {
            dean = getRandomPerson.getRandomInternalProfessor(depts[GetRandomNo.getRandomFromRange(0,deptNum-1)]);
            gen.objectPropertyAssertion(gen.getObjectProperty("hasDean"),gen.getNamedIndividual(collegeInstance),gen.getNamedIndividual(dean));
            }
        }

        else {
            this.collegeInstance = university.univInstance + "ClgOf" + collegeDiscipline + this.collegeIndex;
            this.collegeCode = "U" + this.univIndex + "C" + this.collegeIndex;
            gen.classAssertion(gen.getClass("College"), gen.getNamedIndividual(collegeInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isAffiliatedOrganizationOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isCollegeOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasCollegeDiscipline"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(collegeDiscipline));
      
            this.deptNum = GetRandomNo.getRandomFromRange(gen.deptNum_Min, gen.deptNum_Max);
            this.depts = new Department[this.deptNum];

            for (int i = 0; i < this.deptNum; ++i) {
                this.depts[i] = new Department(this, i, false);
            }
            //Assign Courses
            this.assignCourse=new AssignCourse(this);
            if ((profile.matches("DL")) || (profile.matches("RL")) || (profile.matches("EL"))) {
            dean = getRandomPerson.getRandomInternalProfessor(depts[GetRandomNo.getRandomFromRange(0,deptNum-1)]);
            gen.objectPropertyAssertion(gen.getObjectProperty("hasDean"),gen.getNamedIndividual(collegeInstance),gen.getNamedIndividual(dean));
            }
        }
    }
}
//college has discipline and deptt of that clg will have deptt from sublasses of college discpline
//For eg colg is managmnt and engineering . so deptt will be dept of cse dept of ece dpett of marketing etc
//and for those deptt students can have major course that only