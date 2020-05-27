/** For each College, Department instances are generated.
 * Also generates Object Property Assertion Axioms that link a College Instance to University Instance.
 * Once Department instances (and its related axioms), then one of the random professor from all the departments is selected as the dean.
* In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class College {

    int univIndex;
    int collegeIndex, deptNum;
    String collegeCode,dean;
    Department[] depts;
    Generator gen;
    boolean isWomenCollege;     
    String  collegeInstance,profile,collegeName;
    String collegeDiscipline;
    HashSet<String> personPerUniversity;
    AssignCourse assignCourse;
    GetRandomPerson getRandomPerson;
    

    public College(University university, int collegeIndex, Boolean isWomenCollege) {
        this.personPerUniversity=university.personPerUniversity;
        this.profile=university.profile;
      
        this.univIndex = university.univIndex;
        this.collegeIndex = collegeIndex;
        this.gen = university.gen;
        this.isWomenCollege = isWomenCollege;
        this.collegeDiscipline= gen.TOKEN_CollegeDiscipline[GetRandomNo.getRandomFromRange(0, 4)];
        getRandomPerson=new GetRandomPerson();
        // if college is women's college
        if (this.isWomenCollege) {
        	//For College Names such as Women's College of Engineering (Based on College Discipline)
            this.collegeName = "Women's College of " + collegeDiscipline;
            this.collegeInstance = "U" + this.univIndex + "WC" + this.collegeIndex;
            this.collegeCode = "U" + this.univIndex + "WC" + this.collegeIndex;
            gen.classAssertion(gen.getClass("College"), gen.getNamedIndividual(collegeInstance));
            gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(collegeInstance),gen.getLiteral(collegeName));
            gen.objectPropertyAssertion(gen.getObjectProperty("isAffiliatedOrganizationOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isPartOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasCollegeDiscipline"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(collegeDiscipline));
            this.deptNum = GetRandomNo.getRandomFromRange(gen.deptNum_Min, gen.deptNum_Max);
            this.depts = new Department[this.deptNum];

            for (int i = 0; i < this.deptNum; ++i) {
                this.depts[i] = new Department(this, i, true);
            }

            //Assign Courses
            this.assignCourse=new AssignCourse(this);
           
            dean = getRandomPerson.getRandomInternalProfessor(depts[GetRandomNo.getRandomFromRange(0,deptNum-1)]);
            gen.objectPropertyAssertion(gen.getObjectProperty("hasDean"),gen.getNamedIndividual(collegeInstance),gen.getNamedIndividual(dean));
        
        }

        else {
        	//College of Management
        	this.collegeName = "College of " + collegeDiscipline;
        	this.collegeInstance = "U" + this.univIndex + "C" + this.collegeIndex;
            this.collegeCode = "U" + this.univIndex + "C" + this.collegeIndex;
            gen.classAssertion(gen.getClass("College"), gen.getNamedIndividual(collegeInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isAffiliatedOrganizationOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("isPartOf"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(university.univInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasCollegeDiscipline"), gen.getNamedIndividual(collegeInstance), gen.getNamedIndividual(collegeDiscipline));
      
            this.deptNum = GetRandomNo.getRandomFromRange(gen.deptNum_Min, gen.deptNum_Max);
            this.depts = new Department[this.deptNum];

            for (int i = 0; i < this.deptNum; ++i) {
                this.depts[i] = new Department(this, i, false);
            }
            //Assign Courses
            this.assignCourse=new AssignCourse(this);
           
            dean = getRandomPerson.getRandomInternalProfessor(depts[GetRandomNo.getRandomFromRange(0,deptNum-1)]);
            gen.objectPropertyAssertion(gen.getObjectProperty("hasDean"),gen.getNamedIndividual(collegeInstance),gen.getNamedIndividual(dean));
          
        }
    }
}
